package com.rbxu.market.domain.customer.gateway;

import com.rbxu.market.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
