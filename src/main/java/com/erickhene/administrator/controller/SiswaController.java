package com.erickhene.administrator.controller;

import com.erickhene.administrator.dto.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/siswa/")
public class SiswaController {
    @GetMapping
    public ResponseEntity<GlobalResponse<String>> getAllSiswa(){
        return ResponseEntity.ok(new GlobalResponse<>(null, null, null));
    }
}
