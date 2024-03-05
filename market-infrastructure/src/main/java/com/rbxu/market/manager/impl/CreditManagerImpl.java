package com.rbxu.market.manager.impl;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.Credit;
import com.rbxu.market.domain.manager.CreditManager;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.mapper.ProjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CreditManagerImpl implements CreditManager {

    public Credit getCredit(String customerId){
      return null;
    }

}
