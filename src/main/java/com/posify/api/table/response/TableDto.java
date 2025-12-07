package com.posify.api.table.response;

import com.posify.api.order.response.OrderDto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TableDto {
    private Long id;
    private String tableNumber;
    private String status;

    private List<OrderDto> orders;
}