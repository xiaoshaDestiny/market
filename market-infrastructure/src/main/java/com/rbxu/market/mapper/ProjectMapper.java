package com.rbxu.market.mapper;

import com.rbxu.market.manager.entity.ProjectDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

    Boolean create(ProjectDO projectDO);
}
