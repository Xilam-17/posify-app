package com.posify.api.table.entity;

import com.posify.api.order.entity.Order;
import com.posify.api.table.request.TableRequest;
import com.posify.api.table.response.TableResponse;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "posify_tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TableStatus status = TableStatus.FREE;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public static Table mapToEntity(TableRequest request) {
        Table table = new Table();
        if(request.getId() != null) {
            table.setId(request.getId());
        }
        table.setTableNumber(request.getTableNumber());
        return table;
    }
}