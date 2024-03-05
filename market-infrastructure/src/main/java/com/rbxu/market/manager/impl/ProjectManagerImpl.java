package com.rbxu.market.manager.impl;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.manager.convert.ProjectConvert;
import com.rbxu.market.manager.entity.ProjectDO;
import com.rbxu.market.mapper.ProjectMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

@Repository
public class ProjectManagerImpl implements ProjectManager {

    @Resource
    private ProjectMapper projectMapper;


    public static ProjectDO projectDO = new ProjectDO();

    static {
        projectDO.setId(1L);
        projectDO.setName("projectA");
        projectDO.setTenantId(2L);
        projectDO.setTenantName("tenantA");
        projectDO.setCreator(100L);
        projectDO.setCreateTime(new Date());
    }

    @Override
    public Boolean create(ProjectModel projectModel) {
        ProjectDO createDO = ProjectConvert.toCreateDO(projectModel);
        // mock
        //return projectMapper.create(createDO);
        return true;
    }

    @Override
    public ProjectModel getById(Long id) {
        if (id.equals(1L)) {
            return ProjectConvert.toModel(projectDO);
        }
        return null;
    }
}
