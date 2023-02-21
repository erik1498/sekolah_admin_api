package com.erickhene.administrator.dao.service;

import com.erickhene.administrator.dao.mapper.SiswaTabMapper;
import com.erickhene.administrator.entity.impl.Siswa;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class SiswaDao {
    private final SiswaTabMapper mapper;

    public SiswaDao(SiswaTabMapper mapper) {
        this.mapper = mapper;
    }
    public List<Siswa> selectAll(Map<String, Object> parameterMap){
        return mapper.selectAll(parameterMap);
    }
    public Optional<Siswa> selectByUuid(String uuid){
        return mapper.selectByUuid(uuid);
    }
}
