package com.gamelectronics.updateofficeinfo.service;

import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeRequest;
import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeResponse;
import com.gamelectronics.updateofficeinfo.model.Office;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OfficeService {
    List<Office> registerOffice(List<Office> offices);

    void updateAllOfficeFiled(Office office);

    void updateFilledOfficeFiled(Office office) throws InvocationTargetException, IllegalAccessException;

    Office getOffice(String officeCode);
}
