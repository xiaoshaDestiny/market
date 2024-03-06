package com.rbxu.market.facade.client;

import com.rbxu.market.domain.enums.ErrorCodeEnum;
import com.rbxu.market.domain.exception.ExceptionBuilder;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class GoodDoctorClient {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final String TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9..-Xql88wZYZQ8AmW9NK3vgkiSVEfgoCTcd1g7iEWeybg9eT5vCUY3dfIaENgIOun6yw5WTGOzjFHb65E1KrZggZiafoDkWYMmVI5GSaZZzEStlug_-svSEdTaLuynQ7ZzmpksJPTXLq3TTQT9BTdOk9CywVWkZKbbbxjYH4Iuk9ddfDztnDlCm2ggPUSOucS6g0oHpqzULIacezWGq2VT9ZSMbZs3u23qYPpZ0nMzb39T0ktjv9QdE2qg3MebhWLy6p_nAt0-wKjLDfO1cferImtd1S-fEZQ";
    private static final String AUTHORIZATION = "Authorization";
    private static final String POST = "POST";
    private static final String TARGET_SYSTEM_NAME = "http-云南好医生";

    public String postGoodDoctorRequest(String url, String bodyJson) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, bodyJson);
        Request request = new Request.Builder()
                .addHeader(AUTHORIZATION, TOKEN)
                .url(url)
                .method(POST, body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (Objects.nonNull(response)) {
                return  response.body().string();
            }
            throw ExceptionBuilder.build(ErrorCodeEnum.GET_RESULT_FROM_OTHER_SERVICE_IS_NULL, TARGET_SYSTEM_NAME);
        } catch (Throwable t) {
            log.error("GoodDoctorClient postGoodDoctorRequest error happen message is {}.", t.getMessage(), t);
            throw ExceptionBuilder.build(ErrorCodeEnum.SYSTEM_ERROR, t);
        }
    }

}
