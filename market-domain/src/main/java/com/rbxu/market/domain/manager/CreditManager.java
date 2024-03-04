package com.rbxu.market.domain.manager;

import com.rbxu.market.domain.model.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditManager {
    Credit getCredit(String customerId);
}
