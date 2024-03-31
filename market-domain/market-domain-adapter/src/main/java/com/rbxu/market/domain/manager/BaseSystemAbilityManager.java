package com.rbxu.market.domain.manager;

import com.rbxu.market.domain.model.BaseSystemAbilityModel;

import java.util.List;

public interface BaseSystemAbilityManager {

    List<BaseSystemAbilityModel> getByKey(String key);

    Boolean create(BaseSystemAbilityModel model);

    Boolean update(BaseSystemAbilityModel model);

    Boolean deleteByKey(String key);

    Boolean delete(BaseSystemAbilityModel model);


}
