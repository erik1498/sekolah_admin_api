package com.erickhene.administrator.dao.mapper;

import com.erickhene.administrator.entity.impl.Siswa;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface SiswaTabMapper {
    List<Siswa> selectAll(Map<String, Object> parameterMap);
    Optional<Siswa> selectByUuid(String uuid);
}
