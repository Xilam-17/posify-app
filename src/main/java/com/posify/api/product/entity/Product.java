package com.posify.api.product.entity;

import com.posify.api.category.entity.Category;
import com.posify.api.order.entity.OrderItem;
import com.posify.api.product.request.ProductRequest;
import com.posify.api.product.response.ProductResponse;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productId;
    private String productName;
    private Double price;
    private String description;
    private Long imgId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public static Product mapToEntity(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImgId(request.getImgId());
        return product;
    }
}