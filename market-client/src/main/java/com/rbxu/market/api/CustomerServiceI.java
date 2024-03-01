package com.rbxu.market.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.rbxu.market.dto.CustomerAddCmd;
import com.rbxu.market.dto.CustomerListByNameQry;
import com.rbxu.market.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
