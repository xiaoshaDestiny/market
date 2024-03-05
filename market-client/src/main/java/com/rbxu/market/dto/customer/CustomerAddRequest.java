package com.rbxu.market.dto.customer;

import com.rbxu.market.enums.CompanyType;
import com.rbxu.market.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 二方服务进来的请求体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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
