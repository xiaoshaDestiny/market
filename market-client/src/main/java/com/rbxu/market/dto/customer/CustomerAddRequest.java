package com.rbxu.market.dto.customer;

import com.rbxu.market.enums.CompanyType;
import com.rbxu.market.enums.CustomerType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 二方服务进来的请求体
 */
@Data
public class CustomerAddRequest {

    private String customerId;

    private String memberId;

    private String customerName;

    private CustomerType customerType;

    @NotEmpty
    private CompanyType companyType;

    @NotEmpty
    private String source;

    /**
     * 操作人
     */
    private String operator;
}
