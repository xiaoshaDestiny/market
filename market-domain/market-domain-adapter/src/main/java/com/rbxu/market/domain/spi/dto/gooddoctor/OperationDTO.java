package com.rbxu.market.domain.spi.dto.gooddoctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推送手术数据，手术信息
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class OperationDTO {

    /**
     * 科室ID
     */
    private String departmentId;

    /**
     * 手术ID
     */
    private String operationId;

    /**
     * 手术名称
     */
    private String operationName;

    /**
     * 手术日期
     */
    private String date;

    /**
     * 是否是主刀医生
     */
    private Boolean chiefSurgeon;

    /**
     * 手术次数
     */
    private Integer num;
}
