package com.rbxu.market.domain.service;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;
import com.rbxu.market.domain.spi.TenantSpi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ProjectDomainService {
    @Resource
    private ProjectManager projectManager;
    @Resource
    private TenantSpi tenantSpi;

    public Boolean create(ProjectModel projectModel) {
        // check param
        if (Objects.isNull(projectModel)) {
            return false;
        }

        // 补充其他域参数
        if (Objects.nonNull(projectModel.getTenantModel())) {
            Long tenantId = projectModel.getTenantModel().getId();
            TenantDTO tenant = tenantSpi.getById(tenantId);
            projectModel.getTenantModel().setName(tenant.getTenantName());
        }

        return projectManager.create(projectModel);
    }


    public ProjectModel getById(Long projectId) {
        return projectManager.getById(projectId);
    }

}
