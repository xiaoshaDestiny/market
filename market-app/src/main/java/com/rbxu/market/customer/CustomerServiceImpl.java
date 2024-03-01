package com.rbxu.market.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.rbxu.market.api.CustomerServiceI;
import com.rbxu.market.dto.CustomerAddCmd;
import com.rbxu.market.dto.CustomerListByNameQry;
import com.rbxu.market.dto.data.CustomerDTO;
import com.rbxu.market.customer.executor.CustomerAddCmdExe;
import com.rbxu.market.customer.executor.query.CustomerListByNameQryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}