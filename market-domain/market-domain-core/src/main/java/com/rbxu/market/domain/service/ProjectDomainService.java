package com.rbxu.market.domain.service;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.manager.entity.ProjectDO;
import com.rbxu.market.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ProjectDomainService {

    @Autowired
    private ProjectManager projectManager;

    @Autowired
    private TenantSpi tenantSpi;

    public Boolean create(ProjectModel projectModel) {


//                 结构
//        ProjectModifyDTO modifyDTO;
//        SingleResponse response;
//        ProjectModel projectModel;
//        ProjectDO projectDO;
//        TenantDTO tenantDTO;
//
//        //服务
//        ProjectController controller;
//        ProjectApplicationService projectApplicationService;
//        ProjectDomainService projectDomainService;
//        ProjectManager projectManager;
//        ProjectMapper projectMapper;
//        TenantSpi tenantSpi;
//        TenantClient tenantClient;

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
