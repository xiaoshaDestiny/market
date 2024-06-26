package com.rbxu.market.domain.service.machine;

import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class TargetDomainService {


    public boolean save(TargetModel model) {
        if (Objects.nonNull(model.getStartTime()) && Objects.nonNull(model.getEndTime())) {
            log.info("model will save : {}", JSON.toJSONString(model));
        } else {
            throw new BizException("日期不符合预期，不能保存");
        }
        return true;
    }

    public boolean newSubmit(TargetModel targetModel) {
        log.info("newSubmit invoke");
        return true;
    }

    public boolean editSubmit(TargetModel targetModel) {
        log.info("editSubmit invoke");
        return true;
    }

    public boolean start(TargetModel targetModel) {
        log.info("start invoke");
        return true;
    }

    public boolean stop(TargetModel targetModel) {
        log.info("stop invoke");
        return true;
    }

    public boolean copy(TargetModel targetModel) {
        log.info("copy invoke");
        return true;
    }

    public boolean schedule(TargetModel targetModel) {
        log.info("schedule invoke");
        return true;
    }

    public boolean del(TargetModel targetModel) {
        log.info("del invoke");
        return true;
    }
}
