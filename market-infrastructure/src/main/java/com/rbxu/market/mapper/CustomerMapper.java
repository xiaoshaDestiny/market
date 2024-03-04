package com.rbxu.market.mapper;

import com.rbxu.market.manager.entity.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDO getById(String customerId);

}
