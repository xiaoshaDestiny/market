package com.rbxu.market.mapper;

import com.rbxu.market.manager.entity.BaseSystemAbilityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseSystemAbilityMapper {

    List<BaseSystemAbilityDO> getByKey(@Param("key") String key, @Param("projectId") Long projectId);

    int create(BaseSystemAbilityDO abilityDO);

    int update(BaseSystemAbilityDO abilityDO);

    int delete(BaseSystemAbilityDO abilityDO);

    int deleteByKey(@Param("key") String key, @Param("projectId") Long projectId);
}
