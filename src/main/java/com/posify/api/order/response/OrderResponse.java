package com.posify.api.order.response;

import com.posify.api.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Double totalAmount;
    private String status;
    private Long tableId;
    private List<OrderItemResponse> items;

    public static OrderResponse mapToDto(Order order) {
        OrderResponse response = new OrderResponse();
        response.setTableId(order.getTable().getId());
        response.setOrderId(order.getId());
        response.setTotalAmount(order.getFinalTotalAmount());
        response.setStatus(order.getStatus().name());

        List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
                .map(OrderItemResponse::mapToDto)
                        .toList();

        response.setItems(itemResponses);
        return response;
    }
}