package com.rbxu.market.application.convert;

import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.model.TenantModel;
import com.rbxu.market.dto.ProjectModifyDTO;
import com.rbxu.market.dto.project.ProjectCreateRequest;
import com.rbxu.market.dto.project.ProjectInfoDTO;

public class ProjectConvert {

    public static ProjectModel toProjectModel(ProjectCreateRequest request) {
        // ...
        return new ProjectModel();
    }

    public static ProjectModel toProjectModel(ProjectModifyDTO modifyDTO) {
        ProjectModel projectModel = new ProjectModel();
        TenantModel tenantModel = new TenantModel();
        tenantModel.setId(projectModel.getId());

        projectModel.setName(modifyDTO.getName());
        projectModel.setCreator(modifyDTO.getOperatorId());
        projectModel.setDesc("create by product");
        projectModel.setTenantModel(tenantModel);
        return projectModel;
    }

    public static ProjectInfoDTO toProjectInfoDTO(ProjectModel model) {
        return new ProjectInfoDTO();
    }

}
