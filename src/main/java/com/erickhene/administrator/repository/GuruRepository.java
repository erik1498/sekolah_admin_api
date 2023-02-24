package com.erickhene.administrator.repository;

import com.erickhene.administrator.entity.impl.Guru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuruRepository extends JpaRepository<Guru, String> {
    Optional<Guru> findByUuidAndEnabledTrue(String uuid);
}
