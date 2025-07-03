package org.rhydo.microecom.services;

import org.rhydo.microecom.dtos.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(String userId);
}
