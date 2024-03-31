package com.rbxu.market.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseSystemAbilityModel {

    private Long id;

    private String key;

    private String value;

    private String type;

    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String operator;

}
