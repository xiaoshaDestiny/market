package com.rbxu.market.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.rbxu.market.dto.customer.CustomerAddRequest;
import com.rbxu.market.dto.customer.CustomerListByNameRequest;
import com.rbxu.market.dto.customer.CustomerResponse;

/**
 * client层：对外提供的API，是Http、RPC形式
 * 定义接口：入参、返回值
 */
public interface CustomerApi {

    Response addCustomer(CustomerAddRequest customerAddRequest);

    MultiResponse<CustomerResponse> listByName(CustomerListByNameRequest customerListByNameRequest);
}
