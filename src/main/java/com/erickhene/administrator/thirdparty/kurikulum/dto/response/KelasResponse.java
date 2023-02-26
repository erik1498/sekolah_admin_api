package com.erickhene.administrator.thirdparty.kurikulum.dto.response;

import com.erickhene.administrator.thirdparty.kurikulum.dto.BaseResponse;
import lombok.Data;

@Data
public class KelasResponse extends BaseResponse {
    private String name;
    private String tingkatanUuid;
    private String tahunAkademikUuid;
}
