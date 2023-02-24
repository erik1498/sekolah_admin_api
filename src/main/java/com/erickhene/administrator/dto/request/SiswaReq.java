package com.erickhene.administrator.dto.request;

import com.erickhene.administrator.entity.impl.Siswa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SiswaReq {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "birthPlace is required")
    private String birthPlace;
    @NotNull(message = "birthDate is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthDate;
    @NotNull(message = "religion is required")
    private String religion;
    @NotNull(message = "nisn is required")
    private String nisn;
    @NotNull(message = "nipd is required")
    private String nipd;
    @NotNull(message = "nik is required")
    @Size(max = 20, message = "nik length max 20")
    private String nik;
    @NotNull(message = "phone is required")
    private String phone;
    @NotNull(message = "gender is required")
    private Boolean gender;

    public Siswa convertToEntity(){
        Siswa siswa = new Siswa();
        siswa.setReligion(getReligion());
        siswa.setNik(getNik());
        siswa.setPhone(getPhone());
        siswa.setName(getName());
        siswa.setNipd(getNipd());
        siswa.setNisn(getNisn());
        siswa.setGender(getGender());
        siswa.setBirthPlace(getBirthPlace());
        siswa.setBirthDate(getBirthDate());
        return siswa;
    }
}
