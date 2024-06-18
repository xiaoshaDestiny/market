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
public class ProjectController {

    private final ProjectApplicationService projectApplicationService;

    @Autowired
    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }

    @GetMapping(value = "/helloworld")
    public SingleResponse<Boolean> helloWorld(){
        log.info("test hello world!");
        return projectApplicationService.mockBusiness(1L,"1");
    }

    @PostMapping(value = "/post")
    public SingleResponse<Boolean> post(){
        log.info("test post!");
        return projectApplicationService.mockExecutorBusiness();
    }

    @PutMapping(value = "/put")
    public SingleResponse<Boolean> put(){
        log.info("test put!");
        return projectApplicationService.mockExecutorBusiness();
    }

    @DeleteMapping(value = "/delete")
    public SingleResponse<Boolean> delete(){
        log.info("test delete!");
        return projectApplicationService.mockBusiness(2L,"2");
    }

    @RequestMapping(value = "/exception")
    public SingleResponse<Boolean> exception(){
        log.error("test exception!");
        throw new IllegalArgumentException("方法未实现");
    }

    @PostMapping(value = "/project")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO){
        return projectApplicationService.createProject(projectModifyDTO);
    }
}
