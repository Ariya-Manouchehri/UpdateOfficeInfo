package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    private OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Office> registerOffice(List<Office> offices) {
        return officeRepository.saveAll(offices);
    }

    @Override
    public void updateAllOfficeFiled(Office office) {
        officeRepository.save(office);
    }

    @Override
    public void updateFilledOfficeFiled(Office office) {
        officeRepository.save(office);
    }

    @Override
    public Office getOffice(String officeCode) {
        return officeRepository.findByOfficeCode(officeCode);
    }
}
