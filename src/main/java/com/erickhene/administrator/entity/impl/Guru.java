package com.erickhene.administrator.entity.impl;

import com.erickhene.administrator.entity.Base;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "guru_tab")
@Setter
@Getter
public class Guru extends Base {
    @Column(name = "g_name", nullable = false)
    private String name;
    @Column(name = "g_bithplace", nullable = false)
    private String birthPlace;
    @Column(name = "g_bithdate", nullable = false)
    private String birthDate;
    @Column(name = "g_religion", nullable = false)
    private String religion;
    @Column(name = "g_nip", nullable = false)
    private String nip;
    @Column(name = "g_nik", nullable = false)
    private String nik;
    @Column(name = "g_phone", nullable = false)
    private String phone;
    @Column(name = "g_gender", nullable = false)
    private Boolean gender;
}
