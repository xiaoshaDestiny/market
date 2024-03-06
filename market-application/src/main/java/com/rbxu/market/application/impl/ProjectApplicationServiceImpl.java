package com.rbxu.market.application.impl;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.api.ProjectApi;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.model.TenantModel;
import com.rbxu.market.domain.service.ProjectDomainService;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;
import com.rbxu.market.dto.ProjectModifyDTO;
import com.rbxu.market.domain.enums.ErrorCodeEnum;
import com.rbxu.market.domain.exception.ExceptionBuilder;
import com.rbxu.market.dto.project.ProjectCreateRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ProjectApplicationServiceImpl implements ProjectApplicationService {

    @Resource
    private ProjectDomainService projectDomainService;



    @Override
    public SingleResponse<Boolean> createProject(ProjectModifyDTO projectModifyDTO) {
        // 参数处理校验
        if (Objects.isNull(projectModifyDTO.getTenantId())) {
            throw ExceptionBuilder.build(ErrorCodeEnum.PARAM_IS_NULL);
        }

        // 参数转换映射
        ProjectModel projectModel = new ProjectModel();

        TenantModel tenantModel = new TenantModel();
        tenantModel.setId(projectModel.getId());

        projectModel.setName(projectModifyDTO.getName());
        projectModel.setCreator(projectModifyDTO.getOperatorId());
        projectModel.setDesc("create by product");
        projectModel.setTenantModel(tenantModel);

        // 调用领域服务
        Boolean result = projectDomainService.create(projectModel);

        if (Boolean.TRUE.equals(result)) {
            return SingleResponse.of(true);
        }
        return SingleResponse.of(false);
    }
}
