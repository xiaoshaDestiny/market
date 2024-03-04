package com.rbxu.market.facade.convert;

import com.rbxu.market.domain.spi.dto.gooddoctor.DiseaseTypeDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.OperationDTO;
import com.rbxu.market.domain.spi.dto.gooddoctor.PersonDTO;


public class GoodsDoctorConvert {

    public static String toOperationParam(OperationDTO operationDTO, PersonDTO personDTO, Boolean isMainSurgical) {
        String type = "助手";
        if (isMainSurgical) {
            type = "术者";
        }
        return  "{\"specIn\":{\"id\":"
                + operationDTO.getDepartmentId() +
                "},\"status\":true,\"isManager\":false,\"isRescue\":false," +
                "\"rotateIds\":["
                + operationDTO.getOperationId() +
                "],\"isSuccess\":\"1\",\"kind\":\"" + type + "\",\"optDate\":\""
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
    }

    public static String toDiseaseTypeParam(DiseaseTypeDTO diseaseTypeDTO, PersonDTO personDTO) {
        return  "{\"specIn\":{\"id\":"
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
    }

    public static String toMainSurgicalOperationParam(OperationDTO operationDTO, PersonDTO personDTO) {
        return toOperationParam(operationDTO, personDTO, true);
    }

    public static String toHelperOperationParam(OperationDTO operationDTO, PersonDTO personDTO) {
        return toOperationParam(operationDTO, personDTO, false);
    }
}
