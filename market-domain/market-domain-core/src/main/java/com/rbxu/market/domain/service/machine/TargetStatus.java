package com.rbxu.market.domain.service.machine;

import lombok.Getter;

@Getter
public enum TargetStatus {
    NEW(1, "新建"),

    NOT_ACTIVATED(2, "草稿"),

    WILL_ACTIVATED(3, "待生效"),

    IN_EXECUTION(4, "执行中"),

    FINISHED(5, "已结束"),

    TERMINATED(6, "已停用"),

    VIRTUAL(0, "启用中间态，需要根据时间确定具体值"),
    ;

    private Integer code;

    private String msg;

    TargetStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
