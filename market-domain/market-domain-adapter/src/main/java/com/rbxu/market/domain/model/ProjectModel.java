package com.rbxu.market.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectModel {

    private Long id;

    private String name;

    private String desc;

    private TenantModel tenantModel;

    private Date createTime;

    private Long operatorId;

    private Long creator;
}
