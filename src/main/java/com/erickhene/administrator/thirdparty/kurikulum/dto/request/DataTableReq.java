package com.erickhene.administrator.thirdparty.kurikulum.dto.request;

import lombok.Data;

@Data
public class DataTableReq {
    private String search;
    private Integer pageNumber;
    private Integer pageSize;
}
