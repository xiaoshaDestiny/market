package com.rbxu.market.web;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.aspect.digest.TimeCost;
import com.rbxu.market.dto.ProjectModifyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@TimeCost
public class ProjectController {

    private final ProjectApplicationService projectApplicationService;

    @Autowired
    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }

    @GetMapping(value = "/helloworld")
    public String helloWorld(){
        log.info("test hello world!");
        log.error("error test !");
        return "Hello, welcome to COLA world!";
    }


    @PostMapping(value = "/project")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO){
        return projectApplicationService.createProject(projectModifyDTO);
    }
}
