package com.posify.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class TableDto {
    private Long id;
    private String tableNumber;
    private String status;

    private List<OrderResponseDto> orders;
}