package com.rbxu.market.api;

import com.alibaba.cola.dto.MultiResponse;
import com.rbxu.market.dto.order.OrderQueryByIdRequest;
import com.rbxu.market.dto.order.OrderResponse;

public interface OrderApi {
    MultiResponse<OrderResponse> queryById(OrderQueryByIdRequest orderQueryByIdRequest);

}
