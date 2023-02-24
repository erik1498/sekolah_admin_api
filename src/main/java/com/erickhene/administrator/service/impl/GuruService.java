package com.erickhene.administrator.service.impl;

import com.erickhene.administrator.config.AppConstant;
import com.erickhene.administrator.dao.service.GuruDao;
import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.dto.request.DataTableReq;
import com.erickhene.administrator.entity.impl.Guru;
import com.erickhene.administrator.repository.GuruRepository;
import com.erickhene.administrator.service.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GuruService implements Base<Guru> {
    private final GuruRepository repository;
    private final GuruDao guruDao;

    public GuruService(GuruRepository repository, GuruDao guruDao) {
        this.repository = repository;
        this.guruDao = guruDao;
    }

    @Override
    public GlobalResponse<List<Guru>> getAll(DataTableReq dataTableReq) {
        log.info("Begin [{}]", "getAllGuru");
        List<Guru> findAll = guruDao.selectAll(DataTableReq.generateHashMap(dataTableReq));
        log.info("Guru Length = {}", findAll.size());
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Guru> create(Guru guru) {
        log.info("Begin [{}]", "CreateGuru");
        log.info("Guru = {}", guru);
        try {
            return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(guru));
        }catch(Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<Guru> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidGuru");
        log.info("Uuid = {}", uuid);
        Optional<Guru> findById = repository.findByUuidAndEnabledTrue(uuid);
        log.info("Guru Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Guru = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<Guru> update(String uuid, Guru data) {
        log.info("Begin [{}]", "UpdateGuru");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Guru> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Guru Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Guru = {}", findById.get().toString());
                Guru getByUuid = findById.get();
                getByUuid.setUpdatedDate(new Date());
                getByUuid.setBirthDate(data.getBirthDate());
                getByUuid.setNip(data.getNip());
                getByUuid.setName(data.getName());
                getByUuid.setNik(data.getNik());
                getByUuid.setGender(data.getGender());
                getByUuid.setBirthPlace(data.getBirthPlace());
                getByUuid.setPhone(data.getPhone());
                getByUuid.setReligion(data.getReligion());
                repository.save(getByUuid);
                log.info("Updated to = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.OK.value(), getByUuid);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public GlobalResponse<Boolean> delete(String uuid) {
        log.info("Begin [{}]", "DeleteGuru");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Guru> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Guru Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Guru = {}", findById.get().toString());
                Guru getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Guru Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
