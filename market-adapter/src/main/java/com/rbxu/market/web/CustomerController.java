package com.rbxu.market.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.rbxu.market.application.CustomerApplicationService;
import com.rbxu.market.dto.CustomerCreateDTO;
import com.rbxu.market.dto.customer.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerApplicationService customerApplicationService;

    @Autowired
    public CustomerController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }

    @GetMapping(value = "/helloworld")
    public String helloWorld(){
        return "Hello, welcome to COLA world!";
    }

    @GetMapping(value = "/customer")
    public MultiResponse<CustomerResponse> listCustomerByName(@RequestParam(required = true) String name){
        return customerApplicationService.listByName(name);
    }

    @PostMapping(value = "/customer")
    public Response addCustomer(@RequestBody CustomerCreateDTO customerCreateDTO){
        return customerApplicationService.create(customerCreateDTO);
    }
}
