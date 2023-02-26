package com.erickhene.administrator.thirdparty.kurikulum.services;

import com.erickhene.administrator.config.AppProperties;
import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.thirdparty.kurikulum.dto.request.DataTableReq;
import com.erickhene.administrator.thirdparty.kurikulum.dto.response.KelasResponse;
import com.erickhene.administrator.util.JSONUtil;
import com.erickhene.administrator.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KelasService {
    private final AppProperties appProperties;

    public KelasService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public GlobalResponse<List<KelasResponse>> getAllKelas(DataTableReq dataTableReq){
        try{
            log.info("Begin [{}]", "getAllKelas");
            log.info("DataTable = {}", dataTableReq);
            log.info("Url = {}", appProperties.getKurikulumUrl());
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, JSONUtil.getJsonFromObject(dataTableReq));
            Request request = new Request.Builder()
                    .url(appProperties.getKurikulumUrl().concat("kurikulum/api/kelas/"))
                    .method(HttpMethod.POST.name(), body)
                    .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB0ZXN0LmNvbSIsImV4cCI6MTY3NzQwOTM1Nn0.MMOqU2YGCt4W_6lJnOwIJxcxWZIlwQ3r64yTHt4vyns")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            log.info("Response = {}", responseBody);
            TypeReference<GlobalResponse<List<KelasResponse>>> typeReference = new TypeReference<>() {
            };
            GlobalResponse<List<KelasResponse>> listGlobalResponse = ObjectMapperUtil.generateObjectMapper().readValue(responseBody, typeReference);
            log.info("Kelas Response Success = {}", listGlobalResponse);
            return listGlobalResponse;
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        }
    }
}
