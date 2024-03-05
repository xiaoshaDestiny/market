package com.rbxu.market.domain.service;

import com.rbxu.market.domain.manager.CustomerManager;
import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDomainService {

    @Autowired
    private CustomerManager customerManager;

    public Boolean create(CustomerModel customerModel) {
        return Boolean.TRUE;
    }

    public CustomerModel getById(String customerId) {
        return customerManager.getByById(customerId);
    }

}
