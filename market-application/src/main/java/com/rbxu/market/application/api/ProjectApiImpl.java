package com.rbxu.market.application.api;

import com.rbxu.market.api.ProjectApi;
import com.rbxu.market.application.convert.ProjectConvert;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.service.ProjectDomainService;
import com.rbxu.market.dto.RpcResponse;
import com.rbxu.market.dto.project.ProjectCreateRequest;
import com.rbxu.market.dto.project.ProjectInfoDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RPC 形式 API
 * 返回就结果类型可能和 HTTP 形式不一样
 */
@Service
public class ProjectApiImpl implements ProjectApi {

    @Resource
    private ProjectDomainService projectDomainService;

    @Override
    public RpcResponse<ProjectInfoDTO> create(ProjectCreateRequest request) {
        ProjectModel projectModel = ProjectConvert.toProjectModel(request);
        Boolean b = projectDomainService.create(projectModel);
        // createAndReturnID
        return null;
    }

    @Override
    public RpcResponse<ProjectInfoDTO> getById(Long id) {
        ProjectModel byId = projectDomainService.getById(id);
        ProjectInfoDTO projectInfoDTO = ProjectConvert.toProjectInfoDTO(byId);
        return RpcResponse.of(projectInfoDTO);
    }
}
