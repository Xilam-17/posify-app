package com.posify.api.table.request;

import com.posify.api.table.entity.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TableRequest {
    private Long id;
    private String tableNumber;
}
