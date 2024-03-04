package com.rbxu.market.application;

import com.alibaba.cola.dto.Response;
import com.rbxu.market.api.CustomerApi;
import com.rbxu.market.dto.CustomerCreateDTO;
import com.rbxu.market.dto.customer.CustomerAddRequest;
import com.rbxu.market.enums.ErrorCodeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationServiceTest {

    @Autowired
    private CustomerApplicationService customerApplicationService;


    @Before
    public void setUp() {

    }

    @Test
    public void testCustomerAddSuccess(){
        //1.prepare
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO();
        customerCreateDTO.setCustomerName("NormalName");

        //2.execute
        Response response = customerApplicationService.create(customerCreateDTO);

        //3.assert
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testCustomerAddCompanyNameConflict(){
        //1.prepare
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO();
        customerCreateDTO.setCustomerName("NormalName");

        //2.execute
        Response response = customerApplicationService.create(customerCreateDTO);

        //3.assert error
        Assert.assertEquals(ErrorCodeEnum.PARAM_IS_NULL.getErrCode(), response.getErrCode());
    }
}

