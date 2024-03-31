package com.rbxu.market.manager.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseSystemAbilityDO {

    private Long id;

    private String key;

    private String value;

    private String type;

    private Long projectId;

    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String operator;

}
