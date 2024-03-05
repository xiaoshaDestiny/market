package com.rbxu.market.facade.impl;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.dto.ProjectModifyDTO;
import com.rbxu.market.facade.client.TenantClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TenantSpiImpl implements TenantSpi {

    // 依赖某个 feign调用、Rpc服务方法，将该 Service 注入
    @Resource
    private TenantClient tenantClient;

    @Override
    public TenantDTO getById(Long tenantId) {
        String nameByID = tenantClient.getNameByID(tenantId);

        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setTenantId(tenantId);
        tenantDTO.setTenantName(nameByID);
        return tenantDTO;
    }

}
