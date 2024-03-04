package com.rbxu.market.facade.impl;

import com.rbxu.market.domain.spi.GoodDoctorSpi;
import com.rbxu.market.domain.spi.dto.gooddoctor.DiseaseTypeDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.OperationDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.PersonDTO;
import com.rbxu.market.facade.client.GoodDoctorClient;
import com.rbxu.market.facade.convert.GoodsDoctorConvert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodDoctorSpiImpl implements GoodDoctorSpi {
    private static final String PUSH_DISEASE_TYPE_URL = "http://www.ynhaoyisheng.com:8088/api/rotaterecord/";
    private static final String PUSH_OPERATION_DATA_URL = "http://www.ynhaoyisheng.com:8088/api/operationrecord/";
    private static final GoodDoctorClient GOOD_DOCTOR_CLIENT = new GoodDoctorClient();

    @Override
    public Boolean pushMainSurgicalOperationData(OperationDTO operationDTO, PersonDTO personDTO) {
        String param = GoodsDoctorConvert.toMainSurgicalOperationParam(operationDTO, personDTO);
        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_OPERATION_DATA_URL, param);
        log.info("pushMainSurgicalOperationData result is {}, param is {}.", result, param);
        return  Boolean.TRUE;
    }

    @Override
    public Boolean pushHelperOperationData(OperationDTO operationDTO, PersonDTO personDTO) {
        String param = GoodsDoctorConvert.toHelperOperationParam(operationDTO, personDTO);
        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_OPERATION_DATA_URL, param);
        log.info("pushHelperOperationData result is {}, param is {}.", result, param);
        return  Boolean.TRUE;
    }

    public Boolean pushDiseaseType(PersonDTO personDTO, DiseaseTypeDTO diseaseTypeDTO) {
        String param = GoodsDoctorConvert.toDiseaseTypeParam(diseaseTypeDTO, personDTO);
        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_DISEASE_TYPE_URL, param);
        log.info("pushDiseaseType result is {}, param is {}.", result, param);
        return  Boolean.TRUE;
    }

}
