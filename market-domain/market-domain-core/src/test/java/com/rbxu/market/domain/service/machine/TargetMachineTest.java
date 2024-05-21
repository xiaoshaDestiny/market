package com.rbxu.market.domain.service.machine;


import com.alibaba.cola.statemachine.StateMachine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class TargetMachineTest {

    @Test
    public void test() {
        TargetMachineHelper targetMachine = new TargetMachineHelper();
        StateMachine<TargetStatus, TargetAction, TargetModel> machine = targetMachine.buildMachine();

        TargetModel model = new TargetModel();
        model.setStartTime(LocalDateTime.of(2024, 5,1, 0,0,0));
        model.setEndTime(LocalDateTime.of(2024, 6,1, 0,0,0));

        for (TargetStatus beforeStatus : TargetStatus.values()) {
            for (TargetAction action : TargetAction.values()) {
                model.setStatus(beforeStatus);
                TargetStatus targetStatus = machine.fireEvent(beforeStatus, action, model);

                log.info("在状态 [{}] 下，进行 [{}] 操作，结果是 [{}] \n\n", beforeStatus.getMsg(), action.getMsg(), Objects.isNull(targetStatus) ? "空" : targetStatus.getMsg());
            }
        }
    }

}