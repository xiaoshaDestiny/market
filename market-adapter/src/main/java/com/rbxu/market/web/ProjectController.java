package com.rbxu.market.web;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.dto.ProjectModifyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private final ProjectApplicationService projectApplicationService;

    @Autowired
    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }


    @GetMapping(value = "/helloworld")
    public String helloWorld(){
        return "Hello, welcome to COLA world!";
    }


    @PostMapping(value = "/project")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO){
        return projectApplicationService.createProject(projectModifyDTO);
    }
}
