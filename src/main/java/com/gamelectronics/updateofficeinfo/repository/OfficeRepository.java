package com.gamelectronics.updateofficeinfo.repository;

import com.gamelectronics.updateofficeinfo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office,Long> {
    Office findByOfficeCode(String officeCode);
}
