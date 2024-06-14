package com.rbxu.market.application;

import com.alibaba.cola.dto.SingleResponse;
import com.rbxu.market.dto.ProjectModifyDTO;

public interface ProjectApplicationService {

    /**
     * 初始化项目
     */
    SingleResponse<Boolean> createProject(ProjectModifyDTO projectModifyDTO);



    SingleResponse<Boolean> mockBusiness();


    SingleResponse<Boolean> mockExecutorBusiness();

}
