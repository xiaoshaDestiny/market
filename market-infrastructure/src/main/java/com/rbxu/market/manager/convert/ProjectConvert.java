package com.rbxu.market.manager.convert;

import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.model.TenantModel;
import com.rbxu.market.manager.entity.ProjectDO;

import java.util.Date;

public class ProjectConvert {

   public static ProjectDO toCreateDO(ProjectModel projectModel) {
       ProjectDO projectDO = new ProjectDO();
       projectDO.setId(projectModel.getId());
       projectDO.setName(projectModel.getName());
       projectDO.setDesc("");
       projectDO.setCreator(projectModel.getOperatorId());
       projectDO.setCreateTime(new Date());
       projectDO.setTenantId(projectModel.getTenantModel().getId());
       projectDO.setTenantName(projectModel.getTenantModel().getName());
       return projectDO;
   }


    public static ProjectModel toModel(ProjectDO projectDO) {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(projectDO.getId());
        projectModel.setName(projectDO.getName());
        projectModel.setCreator(projectDO.getCreator());
        projectModel.setCreateTime(projectDO.getCreateTime());

        TenantModel tenantModel = new TenantModel();
        tenantModel.setId(projectDO.getTenantId());
        tenantModel.setName(projectDO.getTenantName());

        projectModel.setTenantModel(tenantModel);
        return projectModel;
    }
}
