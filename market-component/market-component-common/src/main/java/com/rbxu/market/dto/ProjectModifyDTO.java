package com.rbxu.market.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectModifyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long tenantId;

    private Long operatorId;
}
