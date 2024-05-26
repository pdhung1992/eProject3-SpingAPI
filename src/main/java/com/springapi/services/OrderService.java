package com.springapi.services;

import com.springapi.entities.Order;
import com.springapi.payload.response.OrderResponse;
import com.springapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderResponse> getRestaurantOrders(int restaurantId) {
        List<Order> orders = orderRepository.getOrderByRestaurantId(restaurantId);
        return orders.stream().map(order -> new OrderResponse(
                order.getOrder_id(),
                order.getInvoice_no(),
                order.getOrder_date(),
                order.getEvent_name(),
                order.getDelivery_address(),
                order.getDelivery_date(),
                order.getTotal_amount(),
                order.getStatus().getStatus_id(),
                order.getStatus().getStatus()
        )).collect(Collectors.toList());
    }

    public List<OrderResponse> getUserOrders(int userId) {
        List<Order> orders = orderRepository.getOrderByUserId(userId);
        return orders.stream().map(order -> new OrderResponse(
                order.getOrder_id(),
                order.getInvoice_no(),
                order.getOrder_date(),
                order.getEvent_name(),
                order.getDelivery_address(),
                order.getDelivery_date(),
                order.getTotal_amount(),
                order.getStatus().getStatus_id(),
                order.getStatus().getStatus()
        )).collect(Collectors.toList());
    }
}
