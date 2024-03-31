package com.rbxu.market.lock;

import lombok.Data;

@Data
public class LockRecordDTO {

    private Long id;

    private String key;

    private String type;

    private String value;

    private String status;
}
