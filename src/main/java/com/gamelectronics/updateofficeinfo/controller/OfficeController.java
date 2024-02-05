package com.gamelectronics.updateofficeinfo.controller;

import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeRequest;
import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeResponse;
import com.gamelectronics.updateofficeinfo.dto.UpdateAllOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.dto.UpdateNotNullOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateNotNullOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@RestController
@RequestMapping(OfficeController.SERVICE_URL)
public class OfficeController {
    public static final String SERVICE_URL = "/officeService";

    OfficeService officeService;
    RegisterOfficeMapper registerOfficeMapper;
    UpdateAllOfficeFiledMapper updateAllOfficeFiledMapper;
    UpdateNotNullOfficeFiledMapper updateNotNullOfficeFiledMapper;

    public OfficeController(OfficeService officeService, RegisterOfficeMapper registerOfficeMapper,
                            UpdateAllOfficeFiledMapper updateAllOfficeFiledMapper,
                            UpdateNotNullOfficeFiledMapper updateNotNullOfficeFiledMapper) {
        this.officeService = officeService;
        this.registerOfficeMapper = registerOfficeMapper;
        this.updateAllOfficeFiledMapper = updateAllOfficeFiledMapper;
        this.updateNotNullOfficeFiledMapper = updateNotNullOfficeFiledMapper;
    }

    @PostMapping
    public RegisterOfficeResponse registerOffice(@Valid @RequestBody RegisterOfficeRequest registerOfficeRequest) {
        RegisterOfficeResponse registerOfficeResponse = new RegisterOfficeResponse();
        ArrayList<Office> offices = new ArrayList<>(registerOfficeRequest.getOfficesDetails().size());

        offices.addAll(registerOfficeMapper.convertOfficeDetailsToOffices(registerOfficeRequest.getOfficesDetails()));
        offices.forEach(office -> office.setProvider(registerOfficeRequest.getProvider()));

        registerOfficeResponse.setOfficesCount(officeService.registerOffice(offices).size());
        return registerOfficeResponse;
    }

    @PutMapping("/{officeCode}")
    public void updateAllOfficeFiled(@PathVariable String officeCode, @Valid @RequestBody UpdateAllOfficeFiledRequest updateAllOfficeFiledRequest) {
        Office office = updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(updateAllOfficeFiledRequest);
        office.setOfficeCode(officeCode);

        officeService.updateAllOfficeFiled(office);
    }

    @PatchMapping("/{officeCode}")
    public void updateNotNullOfficeFiled(@PathVariable String officeCode, @Valid @RequestBody UpdateNotNullOfficeFiledRequest updateNotNullOfficeFiledRequest) throws InvocationTargetException, IllegalAccessException {
        Office office = updateNotNullOfficeFiledMapper.convertUpdateNotNullOfficeFiledToOffice(updateNotNullOfficeFiledRequest);
        office.setOfficeCode(officeCode);

        officeService.updateNotNullOfficeFiled(office);
    }

    @GetMapping("/{officeCode}")
    public Office getOffice(@PathVariable String officeCode) {
        return officeService.getOffice(officeCode);
    }
}
