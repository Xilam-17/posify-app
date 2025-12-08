package com.posify.api.order.service;

import com.posify.api.order.entity.Order;
import com.posify.api.order.request.OrderRequest;
import com.posify.api.order.response.OrderResponse;

public interface IOrderService {

    OrderResponse saveOrder(Long id, OrderRequest request);
}