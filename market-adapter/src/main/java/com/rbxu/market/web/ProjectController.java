package com.rbxu.market.web;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.dto.ProjectModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class ProjectController {

    @Resource
    private ProjectApplicationService projectApplicationService;


    @GetMapping(value = "/helloworld")
    public String helloWorld(){
        return "Hello, welcome to COLA world!";
    }


    @PostMapping(value = "/project")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO) {

//        // 结构
//        ProjectModifyDTO modifyDTO;
//        SingleResponse response;
//        //ProjectModel projectModel;
//        //ProjectDO projectDO;
//        //TenantDTO tenantDTO;
//
//        //服务
//        ProjectController controller;
//        ProjectApplicationService projectApplicationService;
//        ProjectDomainService projectDomainService;
//        ProjectManager projectManager;
//        ProjectMapper projectMapper;
//        TenantSpi tenantSpi;
//        TenantClient tenantClient;



        return projectApplicationService.createProject(projectModifyDTO);
    }
}
