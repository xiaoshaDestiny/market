package com.rbxu.market.facade.impl;

import com.rbxu.market.domain.spi.GoodDoctorSpi;
import com.rbxu.market.domain.spi.dto.gooddoctor.DiseaseTypeDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.OperationDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.PersonDTO;
import com.rbxu.market.facade.client.GoodDoctorClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodDoctorSpiImpl implements GoodDoctorSpi {
    private static final String PUSH_DISEASE_TYPE_URL = "http://www.ynhaoyisheng.com:8088/api/rotaterecord/";
    private static final String PUSH_OPERATION_DATA_URL = "http://www.ynhaoyisheng.com:8088/api/operationrecord/";
    private static final GoodDoctorClient GOOD_DOCTOR_CLIENT = new GoodDoctorClient();

    @Override
    public Boolean pushMainSurgicalOperationData(OperationDTO operationDTO, PersonDTO personDTO) {
        String createParamJSON = "{\"specIn\":{\"id\":"
                + operationDTO.getDepartmentId() +
                "},\"status\":true,\"isManager\":false,\"isRescue\":false," +
                "\"rotateIds\":["
                + operationDTO.getOperationId() +
                "],\"isSuccess\":\"1\",\"kind\":\"术者\",\"optDate\":\""
                + operationDTO.getDate() +
                "\"," +
                "\"optName\":\""
                + personDTO.getName() +
                "\",\"optCard\":\""
                + personDTO.getId() +
                "\",\"name\":\""
                + operationDTO.getOperationName() +
                "\",\"rotates\":[{\"id\":"
                + operationDTO.getOperationId() +
                "}]}";

        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_OPERATION_DATA_URL, createParamJSON);
        log.info("pushMainSurgicalOperationData result is {}, param is {}.", result, createParamJSON);
        return  Boolean.TRUE;
    }

    @Override
    public Boolean pushHelperOperationData(OperationDTO operationDTO, PersonDTO personDTO) {
        String createParamJSON = "{\"specIn\":{\"id\":"
                + operationDTO.getDepartmentId() +
                "},\"status\":true,\"isManager\":false,\"isRescue\":false," +
                "\"rotateIds\":["
                + operationDTO.getOperationId() +
                "],\"isSuccess\":\"1\",\"kind\":\"助手\",\"optDate\":\""
                + operationDTO.getDate() +
                "\"," +
                "\"optName\":\""
                + personDTO.getName() +
                "\",\"optCard\":\""
                +personDTO.getId()+
                "\",\"name\":\""
                +operationDTO.getOperationName()+
                "\",\"rotates\":[{\"id\":"
                + operationDTO.getOperationId() +
                "}]}";

        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_OPERATION_DATA_URL, createParamJSON);
        log.info("pushHelperOperationData result is {}, param is {}.", result, createParamJSON);
        return  Boolean.TRUE;
    }

    public Boolean pushDiseaseType(PersonDTO personDTO, DiseaseTypeDTO diseaseTypeDTO) {
        String createParamJSON = "{\"specIn\":{\"id\":"
                + diseaseTypeDTO.getDepartmentId() +
                "},\"status\":true,\"isManager\":true,\"isRescue\":false,\"rotateIds\":["
                + diseaseTypeDTO.getDiseaseId() +
                "],\"cardNum\":\""
                + personDTO.getId() +
                "\",\"name\":\""
                +  personDTO.getName() +
                "\",\"mainContent\":\""
                + diseaseTypeDTO.getDiseaseName() +
                "\",\"rotates\":[{\"id\":"
                + diseaseTypeDTO.getDiseaseId() +
                "}]}";
        String result = GOOD_DOCTOR_CLIENT.postGoodDoctorRequest(PUSH_DISEASE_TYPE_URL, createParamJSON);
        log.info("pushDiseaseType result is {}, param is {}.", result, createParamJSON);
        return  Boolean.TRUE;
    }

}
