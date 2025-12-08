    package com.posify.api.order.response;

    import com.posify.api.product.request.ProductRequest;
    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class OrderItemsResponseDto {
        private Long id;
        private Integer quantity;
        private Double unitPrice;
        private Double subtotal;
        private ProductRequest productDetails;
    }