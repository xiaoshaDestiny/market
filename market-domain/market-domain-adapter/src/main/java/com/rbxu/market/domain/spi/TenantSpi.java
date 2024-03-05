package com.rbxu.market.domain.spi;

import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;

public interface TenantSpi {

    TenantDTO getById(Long tenantId);
}
