package com.erickhene.administrator.controller;

import com.erickhene.administrator.dto.GlobalResponse;
import com.erickhene.administrator.thirdparty.kurikulum.dto.request.DataTableReq;
import com.erickhene.administrator.thirdparty.kurikulum.dto.response.KelasResponse;
import com.erickhene.administrator.thirdparty.kurikulum.services.KelasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kelas/")
public class KelasController {
    private final KelasService kelasServices;

    public KelasController(KelasService kelasServices) {
        this.kelasServices = kelasServices;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<List<KelasResponse>>> getAllKelas(@RequestBody DataTableReq dataTableReq){
        GlobalResponse<List<KelasResponse>> allKelas = kelasServices.getAllKelas(dataTableReq);
        return ResponseEntity.status(allKelas.getCode()).body(allKelas);
    }
}
