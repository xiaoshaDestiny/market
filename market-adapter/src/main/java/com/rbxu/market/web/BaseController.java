package com.rbxu.market.web;

import com.alibaba.cola.dto.SingleResponse;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rbxu.market.aspect.TimeCost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController("/base")
@Api(value = "/base", tags = "基础")
@TimeCost(businessIdentify = "BASE")
public class BaseController {

    public static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("MARKET_EXECUTE_TEST_POOL_%d").build();
    public static final ThreadPoolExecutor MARKET_EXECUTE_TEST_POOL = new ThreadPoolExecutor(
            2,
            5,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            THREAD_FACTORY);


    @ApiOperation("健康检查")
    @GetMapping(value = "/health")
    public SingleResponse<Boolean> helloWorld(){
        return SingleResponse.of(true);
    }

    @ApiOperation("POST测试")
    @PostMapping(value = "/post")
    public SingleResponse<Boolean> post(){
        return this.mockExecutorBusiness();
    }

    @ApiOperation("PUT测试")
    @PutMapping(value = "/put")
    public SingleResponse<Boolean> put(){
        log.info("this log in request pool.");
        return this.mockExecutorBusiness();
    }

    @ApiOperation("DELETE测试")
    @DeleteMapping(value = "/delete")
    public SingleResponse<Boolean> delete(){
        return this.mockBusiness(2L,"2");
    }

    @ApiOperation("异常")
    @GetMapping(value = "/exception")
    public SingleResponse<Boolean> exception(){
        throw new IllegalArgumentException("方法未实现");
    }

    public SingleResponse<Boolean> mockBusiness(Long id, String name) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return SingleResponse.of(true);
    }

    public SingleResponse<Boolean> mockExecutorBusiness() {
        MARKET_EXECUTE_TEST_POOL.execute(() -> {
            log.info("this log in thread pool.");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return SingleResponse.of(true);
    }
}
