package com.rbxu.market.domain.service.machine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


@Slf4j
public class TargetMachineHelper {

    private static final TargetDomainService targetDomainService;

    static {
        targetDomainService = new TargetDomainService();
    }

    private Action<TargetStatus, TargetAction, TargetModel> executeAction(Function<TargetModel, Boolean> executeFunction) {
        return (from, to, event, model) -> {
            model.setStatus(to);
            if (TargetStatus.VIRTUAL.equals(to)) {
                if (LocalDateTime.now().isBefore(model.getStartTime())) {
                    to = TargetStatus.WILL_ACTIVATED;
                    model.setStatus(TargetStatus.WILL_ACTIVATED);
                } else if (LocalDateTime.now().isAfter(model.getEndTime())) {
                    to = TargetStatus.FINISHED;
                    model.setStatus(TargetStatus.FINISHED);
                } else {
                    to = TargetStatus.IN_EXECUTION;
                    model.setStatus(TargetStatus.IN_EXECUTION);
                }
            }
            executeFunction.apply(model);
            log.info("execute finish from {} to {} on {}, model is {}", from, to, event, JSON.toJSONString(model));
        };
    }

    public StateMachine<TargetStatus, TargetAction, TargetModel> buildMachine() {
        StateMachineBuilder<TargetStatus, TargetAction, TargetModel> builder = StateMachineBuilderFactory.create();

        // 保存: 新建 to 草稿
        builder.externalTransition()
                .from(TargetStatus.NEW)
                .to(TargetStatus.NOT_ACTIVATED)
                .on(TargetAction.SAVE)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NEW))
                .perform(executeAction(targetDomainService::save));

        // 新建提交: 新建 to	待生效、执行中、已结束
        builder.externalTransition()
                .from(TargetStatus.NEW)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.NEW_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NEW))
                .perform(executeAction(targetDomainService::newSubmit));

        // 编辑提交: 草稿 to 待生效、执行中、已结束
        builder.externalTransition()
                .from(TargetStatus.NOT_ACTIVATED)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.EDIT_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NOT_ACTIVATED))
                .perform(executeAction(targetDomainService::editSubmit));

        // 编辑提交: 待生效 to 待生效
        builder.externalTransition()
                .from(TargetStatus.NOT_ACTIVATED)
                .to(TargetStatus.NOT_ACTIVATED)
                .on(TargetAction.EDIT_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NOT_ACTIVATED))
                .perform(executeAction(targetDomainService::editSubmit));

        // 编辑提交: 执行中 to 执行中
        builder.externalTransition()
                .from(TargetStatus.IN_EXECUTION)
                .to(TargetStatus.IN_EXECUTION)
                .on(TargetAction.EDIT_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.IN_EXECUTION))
                .perform(executeAction(targetDomainService::editSubmit));

        // 编辑提交: 已结束 to 已结束
        builder.externalTransition()
                .from(TargetStatus.FINISHED)
                .to(TargetStatus.FINISHED)
                .on(TargetAction.EDIT_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.FINISHED))
                .perform(executeAction(targetDomainService::editSubmit));

        // 编辑提交: 已停用 to 已停用
        builder.externalTransition()
                .from(TargetStatus.TERMINATED)
                .to(TargetStatus.TERMINATED)
                .on(TargetAction.EDIT_SUBMIT)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.TERMINATED))
                .perform(executeAction(targetDomainService::editSubmit));

        // 启用: 草稿 to 待生效、执行中、已结束
        builder.externalTransition()
                .from(TargetStatus.NOT_ACTIVATED)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.START)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NOT_ACTIVATED))
                .perform(executeAction(targetDomainService::start));

        // 停用: 待生效、执行中、已结束 to 已停用
        builder.externalTransitions()
                .fromAmong(TargetStatus.WILL_ACTIVATED, TargetStatus.IN_EXECUTION, TargetStatus.FINISHED)
                .to(TargetStatus.TERMINATED)
                .on(TargetAction.STOP)
                .when(targetModel -> Lists.newArrayList(TargetStatus.WILL_ACTIVATED, TargetStatus.IN_EXECUTION, TargetStatus.FINISHED).contains(targetModel.getStatus()))
                .perform(executeAction(targetDomainService::stop));

        // 复制: 待生效、执行中、已结束 to 已停用
        builder.externalTransitions().fromAmong(TargetStatus.NOT_ACTIVATED, TargetStatus.WILL_ACTIVATED, TargetStatus.IN_EXECUTION, TargetStatus.FINISHED, TargetStatus.TERMINATED)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.STOP)
                .when(targetModel -> Lists.newArrayList(TargetStatus.NOT_ACTIVATED, TargetStatus.WILL_ACTIVATED, TargetStatus.IN_EXECUTION, TargetStatus.FINISHED, TargetStatus.TERMINATED).contains(targetModel.getStatus()))
                .perform(executeAction(targetDomainService::copy));

        // 自动调度: 待生效 to 执行中
        builder.externalTransition()
                .from(TargetStatus.WILL_ACTIVATED)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.AUTO_SCHEDULE)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.WILL_ACTIVATED))
                .perform(executeAction(targetDomainService::schedule));

        // 自动调度: 执行中 to 已结束
        builder.externalTransition()
                .from(TargetStatus.IN_EXECUTION)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.AUTO_SCHEDULE)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.IN_EXECUTION))
                .perform(executeAction(targetDomainService::schedule));

        // 自动调度: 执行中 to 已结束
        builder.externalTransition()
                .from(TargetStatus.NOT_ACTIVATED)
                .to(TargetStatus.VIRTUAL)
                .on(TargetAction.DELETE)
                .when(targetModel -> targetModel.getStatus().equals(TargetStatus.NOT_ACTIVATED))
                .perform(executeAction(targetDomainService::del));

        // 创建状态机
        StateMachine<TargetStatus, TargetAction, TargetModel> orderStateMachine = builder.build("target——state——machine");
        String uml = orderStateMachine.generatePlantUML();
        log.info("uml is : {}", uml);
        return orderStateMachine;
    }

}
