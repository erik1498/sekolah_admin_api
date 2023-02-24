package com.erickhene.administrator.dao.service;

import com.erickhene.administrator.dao.mapper.GuruTabMapper;
import com.erickhene.administrator.entity.impl.Guru;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class GuruDao {
    private final GuruTabMapper mapper;

    public GuruDao(GuruTabMapper mapper) {
        this.mapper = mapper;
    }
    public List<Guru> selectAll(Map<String, Object> parameterMap){
        return mapper.selectAll(parameterMap);
    }
    public Optional<Guru> selectByUuid(String uuid){
        return mapper.selectByUuid(uuid);
    }
}
