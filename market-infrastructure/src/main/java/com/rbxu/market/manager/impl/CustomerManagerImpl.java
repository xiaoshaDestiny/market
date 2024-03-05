package com.rbxu.market.manager.impl;

import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.domain.manager.CustomerManager;

import com.rbxu.market.manager.convert.CustomerConvert;
import com.rbxu.market.manager.entity.CustomerDO;
import com.rbxu.market.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("customerManager")
public class CustomerManagerImpl implements CustomerManager {

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerModel getByById(String customerId) {
        return null;
//        CustomerDO customerDO = customerMapper.getById(customerId);
//        return CustomerConvert.toCustomer(customerDO);
    }
}
