package com.rbxu.market.lock;

public enum AbilityType {

    LOCK("分布式锁"),
    DIAMOND("diamond"),
    MQ("消息监听"),
    ;

    private final String desc;

    AbilityType(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

}
