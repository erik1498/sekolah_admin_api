package com.erickhene.administrator.thirdparty.kurikulum.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BaseResponse {
    private String uuid = UUID.randomUUID().toString();
    private Date createdDate;
    private Date updatedDate;
    private Boolean enabled = Boolean.TRUE;
}
