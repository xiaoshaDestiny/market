package com.rbxu.market.facade.impl;

import com.google.common.collect.Lists;
import com.rbxu.market.domain.enums.ErrorCodeEnum;
import com.rbxu.market.domain.exception.ExceptionBuilder;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PrecessTest {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String POST = "POST";
    private static final String GET = "GET";

    @Test
    public void precessTest() {
        List<String> addressList = Lists.newArrayList(
                "http://localhost:8080/lock/hello",
                "http://localhost:8081/lock/hello",
                "http://localhost:8082/lock/hello",
                "http://localhost:8083/lock/hello");

        List<Thread> threadGroups = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    addressList.forEach(url -> {
                        sendGetReq(url);
                    });
                }
            });
            threadGroups.add(thread);
        }

        threadGroups.forEach(Thread::start);

        try {Thread.sleep(20 * 1000);} catch (InterruptedException e) {throw new RuntimeException(e);}
    }



    public static String sendGetReq(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .method(GET, null)
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    log.info("压测 failure...");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    log.info("压测 response is {}", response.body().string());
                }
            });
            return "触发成功";
        } catch (Throwable t) {
            log.error("PrecessTest getRequest error happen message is {}.", t.getMessage(), t);
            throw ExceptionBuilder.build(ErrorCodeEnum.SYSTEM_ERROR, t);
        }
    }

    public static String postRequest(String url, String bodyJson) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, bodyJson);
        Request request = new Request.Builder()
                .url(url)
                .method(POST, body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (Objects.nonNull(response)) {
                return  response.body().string();
            }
            throw ExceptionBuilder.build(ErrorCodeEnum.GET_RESULT_FROM_OTHER_SERVICE_IS_NULL, "压测");
        } catch (Throwable t) {
            log.error("PrecessTest postRequest error happen message is {}.", t.getMessage(), t);
            throw ExceptionBuilder.build(ErrorCodeEnum.SYSTEM_ERROR, t);
        }
    }
}
