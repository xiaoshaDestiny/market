package com.rbxu.market.dto.customer;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CustomerResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String customerId;

    private String memberId;

    private String customerName;

    private String customerType;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String source;
}
