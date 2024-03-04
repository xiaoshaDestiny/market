package com.rbxu.market.manager.convert;

import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.manager.entity.CustomerDO;

import java.util.Objects;

public class CustomerConvert {

    public static CustomerModel toCustomer(CustomerDO customerDO) {
        if (Objects.isNull(customerDO)) {
            return null;
        }

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(customerDO.getCustomerId());
        return customerModel;
    }

}
