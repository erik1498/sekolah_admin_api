package com.erickhene.administrator.service.impl;

import com.erickhene.administrator.config.AppConstant;
import com.erickhene.administrator.dao.service.SiswaDao;
import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.dto.request.DataTableReq;
import com.erickhene.administrator.entity.impl.Siswa;
import com.erickhene.administrator.repository.SiswaRepository;
import com.erickhene.administrator.service.Base;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SiswaService implements Base<Siswa> {
    private final SiswaRepository repository;
    private final SiswaDao siswaDao;

    public SiswaService(SiswaRepository repository, SiswaDao siswaDao) {
        this.repository = repository;
        this.siswaDao = siswaDao;
    }

    @Override
    public GlobalResponse<List<Siswa>> getAll(DataTableReq dataTableReq) {
        log.info("Begin [{}]", "getAllSiswa");
        List<Siswa> findAll = siswaDao.selectAll(DataTableReq.generateHashMap(dataTableReq));
        log.info("Siswa Length = {}", findAll.size());
        if (findAll.isEmpty()) {
            return new GlobalResponse<>(AppConstant.DATA_IS_EMPTY, HttpStatus.NOT_FOUND.value());
        }
        return new GlobalResponse<>(null, HttpStatus.OK.value(), findAll);
    }

    @Override
    public GlobalResponse<Siswa> create(Siswa siswa) {
        log.info("Begin [{}]", "CreateSiswa");
        log.info("Siswa = {}", siswa);
        try {
            return new GlobalResponse<>(null, HttpStatus.CREATED.value(), repository.save(siswa));
        }catch(Exception e){
            log.error("Error [{}]", e.getCause().getCause().getLocalizedMessage());
            return new GlobalResponse<>(e.getCause().getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public GlobalResponse<Siswa> getByUuid(String uuid) {
        log.info("Begin [{}]", "getByUuidSiswa");
        log.info("Uuid = {}", uuid);
        Optional<Siswa> findById = repository.findByUuidAndEnabledTrue(uuid);
        log.info("Siswa Present = {}", findById.isPresent());
        if (findById.isPresent()) {
            log.info("Siswa = {}", findById.get().toString());
            return new GlobalResponse<>(null, HttpStatus.OK.value(), findById.get());
        }
        return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }

    @Override
    public GlobalResponse<Siswa> update(String uuid, Siswa data) {
        log.info("Begin [{}]", "UpdateSiswa");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Siswa> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Siswa Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Siswa = {}", findById.get().toString());
                Siswa getByUuid = findById.get();
                getByUuid.setUpdatedDate(new Date());
                getByUuid.setBirthDate(data.getBirthDate());
                getByUuid.setNipd(data.getNipd());
                getByUuid.setNisn(data.getNisn());
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
        log.info("Begin [{}]", "DeleteSiswa");
        log.info("Uuid = {}", uuid);
        try{
            Optional<Siswa> findById = repository.findByUuidAndEnabledTrue(uuid);
            log.info("Siswa Present = {}", findById.isPresent());
            if (findById.isPresent()){
                log.info("Siswa = {}", findById.get().toString());
                Siswa getByUuid = findById.get();
                getByUuid.setEnabled(false);
                repository.save(getByUuid);
                log.info("Siswa Enabled = {}", getByUuid.toString());
                return new GlobalResponse<>(null, HttpStatus.NO_CONTENT.value(), true);
            }
            return new GlobalResponse<>(AppConstant.DATA_NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }catch (Exception e){
            log.error("Error [{}]", e.getMessage());
            return new GlobalResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
