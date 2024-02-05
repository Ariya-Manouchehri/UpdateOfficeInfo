package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gamelectronics.updateofficeinfo.exception.NotFoundOfficeException;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import com.gamelectronics.updateofficeinfo.utils.OfficeBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
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
    public void updateAllOfficeFiled(Office office) throws NotFoundOfficeException {
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            officeFinder = office;
            officeRepository.save(officeFinder);
        } else {
            throw new NotFoundOfficeException("office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    public void updateNotNullOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException {
        Office officeFinder = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (officeFinder != null) {
            OfficeBeanCopy officeBeanCopy = new OfficeBeanCopy();
            officeBeanCopy.copyProperties(officeFinder, office);
            officeRepository.save(officeFinder);
        }else {
            throw new NotFoundOfficeException("office " + office.getOfficeCode() + " notFound.");
        }
    }

    @Override
    public Office getOffice(String officeCode) {
        Office officeFinder  = officeRepository.findByOfficeCode(officeCode);
        if (officeFinder != null){
            return officeFinder;
        }else {
            throw new NotFoundOfficeException("office " + officeCode + " notFound.");
        }
    }
}
