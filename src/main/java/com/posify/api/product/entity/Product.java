package com.posify.api.product.entity;

import com.posify.api.category.entity.Category;
import com.posify.api.order.entity.OrderItem;
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

    public static Product mapToEntity(ProductResponse response) {
        Product product = new Product();
        product.setProductName(response.getProductName());
        product.setPrice(response.getPrice());
        product.setDescription(response.getDescription());
        product.setImgId(response.getImgId());
        return product;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}