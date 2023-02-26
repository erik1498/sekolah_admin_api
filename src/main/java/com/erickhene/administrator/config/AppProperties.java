package com.erickhene.administrator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {
    @Value("${erickhene.kurikulum.api}")
    public String kurikulumUrl;
}
