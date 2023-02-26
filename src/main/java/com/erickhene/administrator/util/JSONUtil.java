package com.erickhene.administrator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONUtil {
    private JSONUtil(){}
    public static String getJsonFromObject(Object object){
        ObjectMapper objectMapper = ObjectMapperUtil.generateObjectMapper();
        try {
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error [{}]", e.getMessage());
            return null;
        }
    }
}
