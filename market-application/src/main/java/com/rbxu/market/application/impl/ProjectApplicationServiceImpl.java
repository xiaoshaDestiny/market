package com.rbxu.market.application.impl;

import com.alibaba.cola.dto.SingleResponse;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rbxu.market.application.ProjectApplicationService;
import com.rbxu.market.aspect.digest.TimeCost;
import com.rbxu.market.domain.model.ProjectModel;
import com.rbxu.market.domain.model.TenantModel;
import com.rbxu.market.domain.service.ProjectDomainService;
import com.rbxu.market.dto.ProjectModifyDTO;
import com.rbxu.market.domain.enums.ErrorCodeEnum;
import com.rbxu.market.domain.exception.ExceptionBuilder;
import com.yomahub.tlog.core.annotation.TLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@TimeCost(businessIdentify = "ProjectApplicationService")
@Service
@Slf4j
public class ProjectApplicationServiceImpl implements ProjectApplicationService {

    public static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("MARKET_EXECUTE_TEST_POOL_%d").build();
    public static final ThreadPoolExecutor MARKET_EXECUTE_TEST_POOL = new ThreadPoolExecutor(
            2,
            5,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            THREAD_FACTORY);

    @Resource
    private ProjectDomainService projectDomainService;

    @Override
    public SingleResponse<Boolean> createProject(ProjectModifyDTO projectModifyDTO) {
        // 参数处理校验
        if (Objects.isNull(projectModifyDTO.getTenantId())) {
            throw ExceptionBuilder.build(ErrorCodeEnum.PARAM_IS_NULL);
        }

        // 参数转换映射
        ProjectModel projectModel = new ProjectModel();

        TenantModel tenantModel = new TenantModel();
        tenantModel.setId(projectModel.getId());

        projectModel.setName(projectModifyDTO.getName());
        projectModel.setCreator(projectModifyDTO.getOperatorId());
        projectModel.setDesc("create by product");
        projectModel.setTenantModel(tenantModel);

        // 调用领域服务
        Boolean result = projectDomainService.create(projectModel);

        if (Boolean.TRUE.equals(result)) {
            return SingleResponse.of(true);
        }
        return SingleResponse.of(false);
    }

    @TimeCost(businessIdentify = "MOCK")
    @TLogAspect(value = {"id","name"},pattern = "<-{}->",joint = "_")
    @Override
    public SingleResponse<Boolean> mockBusiness(Long id, String name) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return SingleResponse.of(true);
    }

    @Override
    public SingleResponse<Boolean> mockExecutorBusiness() {
        MARKET_EXECUTE_TEST_POOL.execute(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("mockExecutorBusiness execute");
        });
        return SingleResponse.of(true);
    }

}
