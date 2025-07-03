package org.rhydo.microecom.services;

import org.rhydo.microecom.dtos.CartItemRequest;
import org.rhydo.microecom.dtos.CartItemResponse;

import java.util.List;

public interface CartService {
    void addToCart(String userId, CartItemRequest cartItemRequest);

    void deleteCartItem(String userId, Long productId);

    List<CartItemResponse> getCartItems(String userId);

    void clearCart(Long userId);
}
