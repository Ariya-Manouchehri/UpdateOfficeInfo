package com.gamelectronics.updateofficeinfo.service;

import com.gam.phoenix.spring.commons.service.NonPersistenceServiceException;
import com.gamelectronics.updateofficeinfo.model.Office;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OfficeService {
    List<Office> registerOffice(List<Office> offices) throws NonPersistenceServiceException;

    void updateAllOfficeFiled(Office office) throws NonPersistenceServiceException;

    void updateNotNullOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException, NonPersistenceServiceException;

    Office getOffice(String officeCode) throws NonPersistenceServiceException;
}
