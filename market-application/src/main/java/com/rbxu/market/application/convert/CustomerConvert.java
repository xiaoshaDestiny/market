package com.rbxu.market.application.convert;

import com.rbxu.market.enums.SourceType;
import com.rbxu.market.domain.model.CustomerModel;
import com.rbxu.market.dto.CustomerCreateDTO;
import com.rbxu.market.dto.customer.CustomerAddRequest;
import com.rbxu.market.enums.CompanyType;

/**
 * convert代码：
 * 将 接入层（HTTP、暴露出去的二方API）的入参或返回值，转换为 Domain 层的入参或返回值
 */
public class CustomerConvert {
    public static CustomerModel toCustomerModel(CustomerCreateDTO customerDTO) {
        CustomerModel model = new CustomerModel();
        model.setCustomerId(customerDTO.getCustomerId());
        model.setCompanyType(CompanyType.valueOf(customerDTO.getCustomerType()));
        model.setCompanyName(customerDTO.getCompanyName());
        model.setMemberId(customerDTO.getMemberId());
        model.setSourceType(SourceType.valueOf(customerDTO.getSource()));
        return model;
    }


    public static CustomerModel toCustomerModel(CustomerAddRequest request) {
        CustomerModel model = new CustomerModel();
        model.setCustomerId(request.getCustomerId());
        model.setCompanyType(CompanyType.valueOf(request.getCompanyType()));
        model.setMemberId(request.getMemberId());
        model.setSourceType(SourceType.valueOf(request.getSource()));
        return model;
    }
}
