package com.erickhene.administrator.controller;

import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.dto.request.DataTableReq;
import com.erickhene.administrator.dto.request.SiswaReq;
import com.erickhene.administrator.entity.impl.Siswa;
import com.erickhene.administrator.service.impl.SiswaService;
import com.erickhene.administrator.util.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/siswa/")
public class SiswaController {
    private final SiswaService siswaService;

    public SiswaController(SiswaService siswaService) {
        this.siswaService = siswaService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Siswa>>> getAll(@RequestBody(required = false) DataTableReq dataTableReq){
        GlobalResponse<List<Siswa>> response = siswaService.getAll(dataTableReq);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Siswa>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<Siswa> response = siswaService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody SiswaReq siswaReq, Errors errors){
        if (errors.hasErrors()){
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<Siswa> response = siswaService.create(siswaReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Siswa>> update(@RequestBody SiswaReq siswaReq, @PathVariable("uuid") String uuid){
        GlobalResponse<Siswa> response = siswaService.update(uuid, siswaReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid){
        GlobalResponse<Boolean> response = siswaService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
