package com.rbxu.market.manager.impl;

import com.rbxu.market.domain.manager.BaseSystemAbilityManager;
import com.rbxu.market.domain.model.BaseSystemAbilityModel;
import com.rbxu.market.manager.entity.BaseSystemAbilityDO;
import com.rbxu.market.mapper.BaseSystemAbilityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class BaseSystemAbilityManageImpl implements BaseSystemAbilityManager {

    @Resource
    private BaseSystemAbilityMapper baseSystemAbilityMapper;

    private static final Long DEFAULT_PROJECT_ID = 1L;

    @Override
    public List<BaseSystemAbilityModel> getByKey(String key) {
        List<BaseSystemAbilityDO> abilityDOList = baseSystemAbilityMapper.getByKey(key, DEFAULT_PROJECT_ID);
        if (!CollectionUtils.isEmpty(abilityDOList)) {
            return abilityDOList.stream().map(abilityDO -> {
                BaseSystemAbilityModel result = new BaseSystemAbilityModel();
                BeanUtils.copyProperties(abilityDO, result);
                return result;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Boolean create(BaseSystemAbilityModel model) {
        BaseSystemAbilityDO abilityDO = new BaseSystemAbilityDO();
        BeanUtils.copyProperties(model, abilityDO);
        abilityDO.setProjectId(DEFAULT_PROJECT_ID);
        int i = baseSystemAbilityMapper.create(abilityDO);
        log.info("create effect {} row", i);
        return true;
    }

    @Override
    public Boolean update(BaseSystemAbilityModel model) {
        BaseSystemAbilityDO abilityDO = new BaseSystemAbilityDO();
        BeanUtils.copyProperties(model, abilityDO);
        abilityDO.setProjectId(DEFAULT_PROJECT_ID);
        int i = baseSystemAbilityMapper.update(abilityDO);
        log.info("update effect {} row", i);
        return true;
    }

    @Override
    public Boolean deleteByKey(String key) {
        int i = baseSystemAbilityMapper.deleteByKey(key, DEFAULT_PROJECT_ID);
        log.info("deleteByKey effect {} row", i);
        return true;
    }

    @Override
    public Boolean delete(BaseSystemAbilityModel model) {
        BaseSystemAbilityDO abilityDO = new BaseSystemAbilityDO();
        BeanUtils.copyProperties(model, abilityDO);
        abilityDO.setProjectId(DEFAULT_PROJECT_ID);
        int i = baseSystemAbilityMapper.delete(abilityDO);
        log.info("delete effect {} row", i);
        return true;
    }
}
