package com.rbxu.market.domain.service;

import com.rbxu.market.domain.manager.BaseSystemAbilityManager;
import com.rbxu.market.domain.model.BaseSystemAbilityModel;
import com.rbxu.market.lock.AbilityType;
import com.rbxu.market.lock.CommonRecordSupport;
import com.rbxu.market.lock.LockAbility;
import com.rbxu.market.lock.LockRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LockDemoDomain {
    @Resource
    private BaseSystemAbilityManager baseSystemAbilityManager;

    private List<LockRecordDTO> get(String key) {
        List<BaseSystemAbilityModel> models = baseSystemAbilityManager.getByKey(key);
        if (Objects.isNull(models)) {
            return null;
        }
        return models.stream().map(model -> {
            LockRecordDTO dto = new LockRecordDTO();
            dto.setId(model.getId());
            dto.setValue(model.getValue());
            dto.setKey(model.getKey());
            dto.setType(model.getType());
            return dto;
        }).collect(Collectors.toList());
    }

    private Boolean insert(LockRecordDTO dto) {
        BaseSystemAbilityModel model = new BaseSystemAbilityModel();
        model.setKey(dto.getKey());
        model.setValue(dto.getValue());
        model.setOperator("rbxu_TEST");
        model.setCreator("rbxu_TEST");
        model.setType(AbilityType.LOCK.name());
        return baseSystemAbilityManager.create(model);
    }

    private Boolean modify(LockRecordDTO dto) {
        BaseSystemAbilityModel model = new BaseSystemAbilityModel();
        model.setKey(dto.getKey());
        model.setValue(dto.getValue());
        model.setOperator("rbxu_TEST");
        model.setType(AbilityType.LOCK.name());
        return baseSystemAbilityManager.update(model);
    }

    private Boolean del(LockRecordDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getKey())) {
            return false;
        }
        BaseSystemAbilityModel model = new BaseSystemAbilityModel();
        model.setId(dto.getId());
        model.setKey(dto.getKey());
        model.setValue(dto.getValue());
        model.setOperator("rbxu_TEST");
        model.setType(AbilityType.LOCK.name());
        return baseSystemAbilityManager.delete(model);
    }


    public String dbLock(String business) {
        CommonRecordSupport<LockRecordDTO> recordSupport = new CommonRecordSupport<>(this::get,  this::modify, this::insert,this::del);
        LockAbility lockAbility = new LockAbility(recordSupport);

        LockAbility.Lock lock = lockAbility.lock(business);
        if (lock.getLocked()) {
            log.info("成功加锁, lock is : {}, 将执行业务动作停10s.", lock);
            try {Thread.sleep(1000 * 10);} catch (InterruptedException e) {throw new RuntimeException(e);}
            Boolean unlock = lockAbility.unlock(lock);
            if (unlock) {
                String res = "加锁并成功解锁，lock is :" + lock.toString();
                log.info(res);
                return res;
            } else {
                String res = "加锁成功，解锁失败，lock is :" + lock.toString();
                log.info(res);
                return res;
            }
        } else {
            String res = "加锁失败, business is " + business;
            log.info(res);
            return res;
        }
    }
}
