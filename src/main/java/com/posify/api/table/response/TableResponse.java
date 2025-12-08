package com.posify.api.table.response;

import com.posify.api.order.response.OrderDto;
import com.posify.api.table.entity.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TableResponse {
    private Long id;
    private String tableNumber;
    private String status;

    private List<OrderDto> orders;

    public static TableResponse mapToDto(Table table) {
        TableResponse response = new TableResponse();
        response.setId(table.getId());
        response.setTableNumber(table.getTableNumber());
        return response;
    }
}