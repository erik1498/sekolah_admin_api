package com.erickhene.administrator.controller;

import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.dto.request.DataTableReq;
import com.erickhene.administrator.dto.request.GuruReq;
import com.erickhene.administrator.entity.impl.Guru;
import com.erickhene.administrator.service.impl.GuruService;
import com.erickhene.administrator.util.ValidationUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guru/")
public class GuruController {
    private final GuruService guruService;

    public GuruController(GuruService guruService) {
        this.guruService = guruService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Guru>>> getAll(@RequestBody(required = false) DataTableReq dataTableReq){
        GlobalResponse<List<Guru>> response = guruService.getAll(dataTableReq);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Guru>> getByUuid(@PathVariable("uuid") String uuid){
        GlobalResponse<Guru> response = guruService.getByUuid(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<?>> create(@Valid @RequestBody GuruReq guruReq, Errors errors){
        if (errors.hasErrors()){
            return ValidationUtil.generateError(errors);
        }
        GlobalResponse<Guru> response = guruService.create(guruReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Guru>> update(@RequestBody GuruReq guruReq, @PathVariable("uuid") String uuid){
        GlobalResponse<Guru> response = guruService.update(uuid, guruReq.convertToEntity());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<GlobalResponse<Boolean>> delete(@PathVariable("uuid") String uuid){
        GlobalResponse<Boolean> response = guruService.delete(uuid);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
