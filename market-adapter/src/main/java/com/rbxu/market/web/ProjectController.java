package com.rbxu.market.web;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.dto.ProjectModifyDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("/project")
@Api(value = "/project", tags = "项目")
public class ProjectController {

    private final ProjectApplicationService projectApplicationService;

    @Autowired
    public ProjectController(ProjectApplicationService projectApplicationService) {
        this.projectApplicationService = projectApplicationService;
    }

    @PostMapping(value = "/create")
    public SingleResponse<Boolean> createProject(@RequestBody ProjectModifyDTO projectModifyDTO){
        return projectApplicationService.createProject(projectModifyDTO);
    }
}
