package com.posify.api.mappers;

import com.posify.api.dto.OrderItemDto;
import com.posify.api.entity.OrderItem;
import com.posify.api.entity.Product;

public class OrderItemMappers {

    public static OrderItemDto mapToDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtOrder(item.getPriceAtOrder());

        if (item.getProduct() != null) {
            dto.setProductId(item.getProduct().getProductId());
            dto.setProductName(item.getProduct().getProductName());
        }

        return dto;
    }

    public static OrderItem mapToEntity(OrderItemDto itemDto) {
        OrderItem item = new OrderItem();
        item.setId(itemDto.getId());
        item.setQuantity(itemDto.getQuantity());
        item.setPriceAtOrder(itemDto.getPriceAtOrder());

        if (itemDto.getProductId() != null) {
            Product product = new Product();
            product.setProductId(itemDto.getProductId());
            item.setProduct(product);
        }
        return item;
    }
}