package com.erickhene.administrator.repository;

import com.erickhene.administrator.entity.impl.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, String> {
}
