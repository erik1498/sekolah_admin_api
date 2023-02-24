package com.erickhene.administrator.dao.mapper;

import com.erickhene.administrator.entity.impl.Guru;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface GuruTabMapper {
    List<Guru> selectAll(Map<String, Object> parameterMap);
    Optional<Guru> selectByUuid(String uuid);
}
