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
        return "Hello, welcome to COLA world!";
    }

    @PostMapping(value = "/post")
    public String post(){
        log.info("test post!");
        return "Hello, post";
    }

    @PutMapping(value = "/put")
    public String put(){
        log.info("test put!");
        return "Hello, put";
    }

    @DeleteMapping(value = "/delete")
    public String delete(){
        log.info("test delete!");
        return "Hello, delete";
    }

    @RequestMapping(value = "/exception")
    public String exception(){
        log.error("test exception!");
        throw new IllegalArgumentException("方法未实现");
    }

    @PostMapping(value = "/project")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO){
        return projectApplicationService.createProject(projectModifyDTO);
    }
}
