package com.rbxu.market.domain.spi;

import com.rbxu.market.domain.spi.dto.gooddoctor.DiseaseTypeDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.OperationDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.PersonDTO;

/**
 * 好医生二方服务接口定义
 */
public interface GoodDoctorSpi {

    /**
     * 推送主刀手术数据
     */
    Boolean pushMainSurgicalOperationData(OperationDTO operationDTO, PersonDTO personDTO);

    /**
     * 推送助手手术数据
     */
    Boolean pushHelperOperationData(OperationDTO operationDTO, PersonDTO personDTO);


    /**
     * 推送病种数据
     */
    Boolean pushDiseaseType(PersonDTO personDTO, DiseaseTypeDTO diseaseTypeDTO);

}
