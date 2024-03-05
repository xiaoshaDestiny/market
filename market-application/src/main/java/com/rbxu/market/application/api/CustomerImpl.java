package com.rbxu.market.application.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.google.common.collect.Lists;
import com.rbxu.market.api.CustomerApi;
import com.rbxu.market.application.convert.CustomerConvert;
import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.domain.service.CustomerDomainService;
import com.rbxu.market.dto.customer.CustomerAddRequest;
import com.rbxu.market.dto.customer.CustomerListByNameRequest;
import com.rbxu.market.dto.customer.CustomerResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * RPC 形式 API
 * 返回就结果类型可能和 HTTP 形式不一样
 */
@Service
@CatchAndLog
public class CustomerImpl implements CustomerApi {

    @Resource
    private CustomerDomainService customerDomainService;



    public Response addCustomer(CustomerAddRequest customerAddRequest) {

        CustomerModel customerModel = CustomerConvert.toCustomerModel(customerAddRequest);
        Boolean b = customerDomainService.create(customerModel);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<CustomerResponse> listByName(CustomerListByNameRequest customerListByNameRequest) {
        return MultiResponse.of(Lists.newArrayList());
    }

}