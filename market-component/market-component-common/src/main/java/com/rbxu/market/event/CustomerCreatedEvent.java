package com.rbxu.market.event;

import java.io.Serializable;

public class CustomerCreatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
