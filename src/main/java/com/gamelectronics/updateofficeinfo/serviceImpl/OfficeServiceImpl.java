package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import com.gamelectronics.updateofficeinfo.utils.MyBeanCopy;
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
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            officeFinder = office;
            officeRepository.save(officeFinder);
        }
    }

    @Override
    public void updateFilledOfficeFiled(Office office) {
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            MyBeanCopy myBeanCopy = new MyBeanCopy();
            try {
                myBeanCopy.copyProperties(officeFinder, office);
            } catch (Exception e) {
                System.out.println("error");
            }
            officeRepository.save(officeFinder);
        }
    }

    @Override
    public Office getOffice(String officeCode) {
        return officeRepository.findByOfficeCode(officeCode);
    }
}
