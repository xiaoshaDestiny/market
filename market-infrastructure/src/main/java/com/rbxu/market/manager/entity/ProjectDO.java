package com.rbxu.market.manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDO {

    private Long id;

    private String name;

    private String desc;

    private Long tenantId;

    private String tenantName;

    private Date createTime;

    private Date modifyTime;

    private Long creator;

    private Long operator;
}
