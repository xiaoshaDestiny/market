package com.rbxu.market.facade.client;

import org.springframework.stereotype.Service;

@Service
public class TenantClient {

    public String getNameByID(Long id) {
        return "mockTenantName";
    }
}
