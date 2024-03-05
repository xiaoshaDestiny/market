package com.rbxu.market.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProjectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long tenantId;

    private String creator;

    private Date createTime;
}
