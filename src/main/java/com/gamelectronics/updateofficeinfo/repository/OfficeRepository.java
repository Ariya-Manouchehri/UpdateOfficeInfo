package com.gamelectronics.updateofficeinfo.repository;

import com.gamelectronics.updateofficeinfo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends RevisionRepository<Office,String,Long>,JpaRepository<Office,String> {
    Optional<Office> findByOfficeCode(String officeCode);
}
