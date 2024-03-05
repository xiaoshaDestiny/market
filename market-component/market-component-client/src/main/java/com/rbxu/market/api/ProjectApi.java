package com.rbxu.market.api;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.dto.RpcRequest;
import com.rbxu.market.dto.RpcResponse;
import com.rbxu.market.dto.project.ProjectCreateRequest;
import com.rbxu.market.dto.project.ProjectInfoDTO;

public interface ProjectApi {

    RpcResponse<ProjectInfoDTO> create(ProjectCreateRequest request);

    RpcResponse<ProjectInfoDTO> getById(Long id);
}
