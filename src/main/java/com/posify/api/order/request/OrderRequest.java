package com.posify.api.order.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {
   private Long tableId;
   private List<OrderItemRequest> items;
}
