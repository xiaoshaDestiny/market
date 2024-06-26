package com.rbxu.market.domain.service.machine;

import lombok.Getter;

@Getter
public enum TargetAction {
    SAVE(1, "保存"),
    NEW_SUBMIT(2, "新建提交"),
    EDIT_SUBMIT(3, "编辑提交"),
    START(4, "启用"),
    STOP(5, "停用"),
    COPY(6, "复制"),
    AUTO_SCHEDULE(7, "自动调度"),
    DELETE(8, "删除"),
    ;

    private final Integer status;

    private final String msg;

    TargetAction(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}