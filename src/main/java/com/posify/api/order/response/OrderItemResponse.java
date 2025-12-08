package com.posify.api.order.response;

import com.posify.api.order.entity.OrderItem; // <-- NEW Import needed for mapping
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Added for completeness
import lombok.AllArgsConstructor; // Added for completeness

// Added NoArgsConstructor and AllArgsConstructor for completeness
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private Long orderItemId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceAtOrder;
    private Double subtotal;

    public static OrderItemResponse mapToDto(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();

        response.setOrderItemId(item.getId());
        response.setProductId(item.getProduct().getProductId());
        response.setProductName(item.getProduct().getProductName());
        response.setQuantity(item.getQuantity());
        response.setPriceAtOrder(item.getPriceAtOrder());
        response.setSubtotal(item.getTotalAmount());

        return response;
    }
}