package com.rbxu.market.pushdata;

import com.alibaba.fastjson.JSON;
import com.rbxu.market.pushdata.dto.DiseaseTypeDTO;
import com.rbxu.market.pushdata.dto.PersonDTO;
import com.rbxu.market.pushdata.dto.ShouShuDTO;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class GoodDoctorFacade {
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static String PUSH_DISEASE_TYPE_URL = "http://www.ynhaoyisheng.com:8088/api/rotaterecord/";
    private static String TOKEN = "";


    public Boolean pushZhudaoShoushuData(String keshiId, String shoushuId, String shoushuName, String userId, String userName, String date) {
        String createParamJSON = "{\"specIn\":{\"id\":"
                + keshiId +
                "},\"status\":true,\"isManager\":false,\"isRescue\":false," +
                "\"rotateIds\":["
                + shoushuId +
                "],\"isSuccess\":\"1\",\"kind\":\"术者\",\"optDate\":\""
                + date +
                "\"," +
                "\"optName\":\""
                + userName +
                "\",\"optCard\":\""
                +userId+
                "\",\"name\":\""
                +shoushuName+
                "\",\"rotates\":[{\"id\":"
                + shoushuId +
                "}]}";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, createParamJSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", TOKEN)
                .url("http://www.ynhaoyisheng.com:8088/api/operationrecord/")
                .method("POST", body)
                .build();

        String responseJson = "";
        try {
            Response response = client.newCall(request).execute();
            if (Objects.nonNull(response)) {
                responseJson = response.body().string();
                log.info("result is {}, createParamJSON is {}", responseJson, createParamJSON);
            }
        } catch (Throwable t) {
            log.error("error happen! message is {}.", t.getMessage(), t);
        }
        return Boolean.TRUE;
    }

    public Boolean pushZhushouShoushuData(String keshiId, String shoushuId, String shoushuName, String userId, String userName, String date) {

        String createParamJSON = "{\"specIn\":{\"id\":"
                + keshiId +
                "},\"status\":true,\"isManager\":false,\"isRescue\":false," +
                "\"rotateIds\":["
                + shoushuId +
                "],\"isSuccess\":\"1\",\"kind\":\"助手\",\"optDate\":\""
                + date +
                "\"," +
                "\"optName\":\""
                + userName +
                "\",\"optCard\":\""
                +userId+
                "\",\"name\":\""
                +shoushuName+
                "\",\"rotates\":[{\"id\":"
                + shoushuId +
                "}]}";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, createParamJSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", TOKEN)
                .url("http://www.ynhaoyisheng.com:8088/api/operationrecord/")
                .method("POST", body)
                .build();

        String responseJson = "";
        try {
            Response response = client.newCall(request).execute();
            if (Objects.nonNull(response)) {
                responseJson = response.body().string();
                log.info("result is {}, createParamJSON is {}", responseJson, createParamJSON);
            }
        } catch (Throwable t) {
            log.error("error happen! message is {}.", t.getMessage(), t);
        }
        return Boolean.TRUE;
    }




    public Boolean pushDiseaseType(PersonDTO personDTO, DiseaseTypeDTO diseaseTypeDTO) {
        String createParamJSON = "{\"specIn\":{\"id\":"
                + diseaseTypeDTO.getKsId() +
                "},\"status\":true,\"isManager\":true,\"isRescue\":false,\"rotateIds\":["
                + diseaseTypeDTO.getBzId() +
                "],\"cardNum\":\""
                + personDTO.getId() +
                "\",\"name\":\""
                +  personDTO.getName() +
                "\",\"mainContent\":\""
                + diseaseTypeDTO.getBzName() +
                "\",\"rotates\":[{\"id\":"
                + diseaseTypeDTO.getBzId() +
                "}]}";


        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, createParamJSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", TOKEN)
                .url(PUSH_DISEASE_TYPE_URL)
                .method("POST", body)
                .build();

        String responseJson = "";
        try {
            Response response = client.newCall(request).execute();
            if (Objects.nonNull(response)) {
                responseJson = response.body().string();
                log.info("result is {}, disease is {}, parson is {}", responseJson, JSON.toJSON(diseaseTypeDTO), JSON.toJSON(personDTO));
            }
        } catch (Throwable t) {
            log.error("error happen! message is {}.", t.getMessage(), t);
        }
        return Boolean.TRUE;
    }
}


