package com.rbxu.market.api;

import com.alibaba.cola.dto.Response;
import com.rbxu.market.dto.customer.CustomerAddRequest;
import com.rbxu.market.enums.ErrorCodeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is for integration test.
 *
 * Created by fulan.zjf on 2017/11/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApiTest {

    @Autowired
    private CustomerApi customerService;


    @Before
    public void setUp() {

    }

    @Test
    public void testCustomerAddSuccess(){
        //1.prepare
        CustomerAddRequest customerAddRequest = new CustomerAddRequest();
        customerAddRequest.setCustomerName("NormalName");

        //2.execute
        Response response = customerService.addCustomer(customerAddRequest);

        //3.assert
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testCustomerAddCompanyNameConflict(){
        //1.prepare
        CustomerAddRequest customerAddRequest = new CustomerAddRequest();
        customerAddRequest.setCustomerName("ConflictCompanyName");

        //2.execute
        Response response = customerService.addCustomer(customerAddRequest);

        //3.assert error
        Assert.assertEquals(ErrorCodeEnum.PARAM_IS_NULL.getErrCode(), response.getErrCode());
    }
}
