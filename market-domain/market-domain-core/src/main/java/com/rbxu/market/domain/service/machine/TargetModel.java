package com.rbxu.market.domain.service.machine;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TargetModel {

    private TargetStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
