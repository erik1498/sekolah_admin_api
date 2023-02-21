package com.erickhene.administrator.service;

import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.dto.request.DataTableReq;

import java.util.List;

public interface Base<T> {
    GlobalResponse<List<T>> getAll(DataTableReq dataTableReq);

    GlobalResponse<T> create(T data);

    GlobalResponse<T> getByUuid(String id);

    GlobalResponse<T> update(String id, T data);

    GlobalResponse<Boolean> delete(String id);
}
