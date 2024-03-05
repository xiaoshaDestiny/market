package com.rbxu.market.domain.service;

import com.rbxu.market.domain.manager.CustomerManager;
import com.rbxu.market.domain.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDomainService {

    private final CustomerManager customerManager;

    @Autowired
    public CustomerDomainService(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public Boolean create(CustomerModel customerModel) {
        return Boolean.TRUE;
    }

    public CustomerModel getById(String customerId) {
        return customerManager.getByById(customerId);
    }

}
