package com.rbxu.market.domain.manager;

import com.rbxu.market.domain.model.CustomerModel;

public interface CustomerManager {
    CustomerModel getByById(String customerId);
}
