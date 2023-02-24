package com.erickhene.administrator.dto.request;

import com.erickhene.administrator.entity.impl.Guru;
import com.erickhene.administrator.entity.impl.Siswa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GuruReq {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "birthPlace is required")
    private String birthPlace;
    @NotNull(message = "birthDate is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthDate;
    @NotNull(message = "religion is required")
    private String religion;
    @NotNull(message = "nip is required")
    private String nip;
    @NotNull(message = "nik is required")
    @Size(max = 20, message = "nik length max 20")
    private String nik;
    @NotNull(message = "phone is required")
    private String phone;
    @NotNull(message = "gender is required")
    private Boolean gender;

    public Guru convertToEntity(){
        Guru guru = new Guru();
        guru.setReligion(getReligion());
        guru.setNik(getNik());
        guru.setPhone(getPhone());
        guru.setName(getName());
        guru.setNip(getNip());
        guru.setGender(getGender());
        guru.setBirthPlace(getBirthPlace());
        guru.setBirthDate(getBirthDate());
        return guru;
    }
}
