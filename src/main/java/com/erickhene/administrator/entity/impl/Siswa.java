package com.erickhene.administrator.entity.impl;

import com.erickhene.administrator.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "siswa_tab")
@Setter
@Getter
public class Siswa extends Base {
    @Column(name = "s_name", nullable = false)
    private String name;
    @Column(name = "s_bithplace", nullable = false)
    private String birthPlace;
    @Column(name = "s_bithdate", nullable = false)
    private String birthDate;
    @Column(name = "s_religion", nullable = false)
    private String religion;
    @Column(name = "s_nisn", nullable = false)
    private String nisn;
    @Column(name = "s_nipd", nullable = false)
    private String nipd;
    @Column(name = "s_nik", nullable = false)
    private String nik;
    @Column(name = "s_phone", nullable = false)
    private String phone;
    @Column(name = "s_gender", nullable = false)
    private Boolean gender;
}
