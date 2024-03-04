package com.rbxu.market.domain.spi.dto.gooddoctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 病种 类型
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DiseaseTypeDTO {

    /**
     * 科室ID
     */
    private String departmentId;

    /**
     * 科室名称
     */
    private String departmentName;

    /**
     * 病种ID
     */
    private Integer diseaseId;

    /**
     * 病种名称
     */
    private String diseaseName;
}
