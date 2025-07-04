package org.rhydo.microecom.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rhydo.microecom.dtos.OrderResponse;
import org.rhydo.microecom.enums.OrderStatus;
import org.rhydo.microecom.exceptions.AppException;
import org.rhydo.microecom.exceptions.ResourceNotFoundException;
import org.rhydo.microecom.models.CartItem;
import org.rhydo.microecom.models.Order;
import org.rhydo.microecom.models.OrderItem;
import org.rhydo.microecom.models.User;
import org.rhydo.microecom.repositories.CartItemRepository;
import org.rhydo.microecom.repositories.OrderRepository;
import org.rhydo.microecom.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(String userId) {
        // Look for user
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", Long.valueOf(userId)));

        // Look for cartItem
        List<CartItem> cartItems = cartItemRepository.findByUserId(user.getId());
        if (cartItems.isEmpty()) {
            throw new AppException("No cartItems found ");
        }

        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalPrice);
        order.setStatus(OrderStatus.CONFIRMED);

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cartService.clearCart(user.getId());

        return modelMapper.map(savedOrder, OrderResponse.class);
    }
}
