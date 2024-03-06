package com.rbxu.market.manager.impl;

import com.rbxu.market.domain.manager.ProjectManager;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.spi.TenantSpi;
import com.rbxu.market.domain.spi.dto.tenant.TenantDTO;
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

//
//    /**
//     * 测试服务、结构，是否可访问
//     */
//    private void testServiceDTO() {
//        // API 入参
//        ProjectCreateRequest request = new ProjectCreateRequest();
//        // Rest 入参
//        ProjectModifyDTO projectModifyDTO = new ProjectModifyDTO();
//        // 领域模型
//        ProjectModel projectModel = new ProjectModel();
//        // 领域支撑模型
//        TenantDTO tenantDTO = new TenantDTO();
//        // DB模型
//        ProjectDO projectDO = new ProjectDO();
//
//
//        //API服务
//        ProjectApi projectApi = new ProjectApi();
//        // 应用服务
//        ProjectApplicationService projectApplicationService = new ProjectApplicationService();
//        // 领域服务
//        ProjectDomainService domainService = new ProjectDomainService();
//        // 基础设施服务
//        ProjectManager projectManager;
//        // MyBatis提供的DB服务
//        ProjectMapper projectMapper;
//        // 防腐接口-SPI服务
//        TenantSpi tenantSpi;
//        // 实际二方服务接口服务
//        TenantClient tenantClient;
//    }
}
