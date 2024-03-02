package com.gamelectronics.updateofficeinfo.service;

import com.gam.phoenix.spring.commons.service.NonPersistenceServiceException;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.utils.ImportDataToSaman;
import com.gamelectronics.updateofficeinfo.utils.OfficeBeanCopy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;
    private final ImportDataToSaman importDataToSaman;

    public OfficeService(OfficeRepository officeRepository, ImportDataToSaman importDataToSaman) {
        this.officeRepository = officeRepository;
        this.importDataToSaman = importDataToSaman;
    }

    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public List<Office> registerOffice(List<Office> offices) throws NonPersistenceServiceException {
        for (Office item : offices) {
            importDataToSaman.setOfficeDataForSamanSystem(item);
        }
        return officeRepository.saveAll(offices);
    }

    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public void updateAllOfficeFiled(Office office) throws NonPersistenceServiceException {
        Optional<Office> foundOffice = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (foundOffice.isPresent()) {
            officeRepository.save(office);
            importDataToSaman.setOfficeDataForSamanSystem(foundOffice.get());
        } else {
            throw new NonPersistenceServiceException("404", "office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Transactional(rollbackOn = NonPersistenceServiceException.class)
    public void updateNotNullOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException, NonPersistenceServiceException {
        Optional<Office> foundOffice = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (foundOffice.isPresent()) {
            //todo : use mapstruct

            OfficeBeanCopy officeBeanCopy = new OfficeBeanCopy();
            officeBeanCopy.copyProperties(foundOffice.get(), office);
            officeRepository.save(foundOffice.get());
            importDataToSaman.setOfficeDataForSamanSystem(foundOffice.get());
        } else {
            throw new NonPersistenceServiceException("404", "office " + office.getOfficeCode() + " notFound.");
        }
    }

    public Office getOffice(String officeCode) throws NonPersistenceServiceException {
        Optional<Office> office = officeRepository.findByOfficeCode(officeCode);
        if (office.isPresent()) {
            return office.get();
        } else {
            throw new NonPersistenceServiceException("404", "office " + officeCode + " notFound.");
        }
    }
}
