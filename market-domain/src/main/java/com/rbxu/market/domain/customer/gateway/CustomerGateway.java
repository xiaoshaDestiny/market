package com.rbxu.market.domain.customer.gateway;

import com.rbxu.market.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
