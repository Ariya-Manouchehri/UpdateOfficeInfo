package com.gamelectronics.updateofficeinfo.service;

import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeRequest;
import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeResponse;
import com.gamelectronics.updateofficeinfo.model.Office;

import java.util.List;

public interface OfficeService {
    List<Office> registerOffice(List<Office> offices);

    void updateAllOfficeFiled(Office office);

    void updateFilledOfficeFiled(Office office);

    Office getOffice(String officeCode);
}
