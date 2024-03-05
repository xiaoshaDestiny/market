package com.rbxu.market.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListByNameRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
}
