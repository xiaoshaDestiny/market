package com.rbxu.market.application;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.rbxu.market.dto.CustomerCreateDTO;
import com.rbxu.market.dto.customer.CustomerResponse;

public interface CustomerApplicationService {
    Response create(CustomerCreateDTO customerCreateDTO);

    MultiResponse<CustomerResponse> listByName(String name);

}
