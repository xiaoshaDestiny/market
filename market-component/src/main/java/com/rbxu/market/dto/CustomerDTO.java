package com.rbxu.market.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {

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
