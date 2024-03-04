package com.rbxu.market.application.impl;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.google.common.collect.Lists;
import com.rbxu.market.application.CustomerApplicationService;
import com.rbxu.market.application.convert.CustomerConvert;
import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.domain.service.CustomerDomainService;
import com.rbxu.market.dto.CustomerCreateDTO;
import com.rbxu.market.dto.customer.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CustomerDomainService customerDomainService;

    @Autowired
    public CustomerApplicationServiceImpl(CustomerDomainService customerDomainService) {
        this.customerDomainService = customerDomainService;
    }

    public Response create(CustomerCreateDTO customerCreateDTO) {
        // param check
        CustomerModel customerModel = CustomerConvert.toCustomerModel(customerCreateDTO);
        Boolean success = customerDomainService.create(customerModel);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<CustomerResponse> listByName(String name) {
        return MultiResponse.of(Lists.newArrayList());
    }
}
