package com.gamelectronics.updateofficeinfo.controller;

import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeRequest;
import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeResponse;
import com.gamelectronics.updateofficeinfo.dto.UpdateAllOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.dto.UpdateFilledOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateFilledOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(OfficeController.SERVICE_URL)
public class OfficeController {
    public static final String SERVICE_URL = "/officeService";

    OfficeService officeService;
    RegisterOfficeMapper registerOfficeMapper;
    UpdateAllOfficeFiledMapper updateAllOfficeFiledMapper;
    UpdateFilledOfficeFiledMapper updateFilledOfficeFiledMapper;

    public OfficeController(OfficeService officeService, RegisterOfficeMapper registerOfficeMapper,
                            UpdateAllOfficeFiledMapper updateAllOfficeFiledMapper,
                            UpdateFilledOfficeFiledMapper updateFilledOfficeFiledMapper) {
        this.officeService = officeService;
        this.registerOfficeMapper = registerOfficeMapper;
        this.updateAllOfficeFiledMapper = updateAllOfficeFiledMapper;
        this.updateFilledOfficeFiledMapper = updateFilledOfficeFiledMapper;
    }

    @PostMapping
    public RegisterOfficeResponse registerOffice(@RequestBody RegisterOfficeRequest registerOfficeRequest) {
        RegisterOfficeResponse registerOfficeResponse = new RegisterOfficeResponse();
        ArrayList<Office> offices = new ArrayList<>(registerOfficeRequest.getOfficesDetail().size());
        offices.addAll(registerOfficeMapper.convertOfficeDetailToOffice(registerOfficeRequest.getOfficesDetail()));
        offices.forEach(office -> office.setProvider(registerOfficeRequest.getProvider()));
        registerOfficeResponse.setOfficesCount(officeService.registerOffice(offices).size());
        return registerOfficeResponse;
    }

    @PutMapping("/{officeCode}")
    public void updateAllOfficeFiled(@PathVariable String officeCode, @RequestBody UpdateAllOfficeFiledRequest updateAllOfficeFiledRequest) {
        Office office;
        office = updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(updateAllOfficeFiledRequest);
        office.setOfficeCode(officeCode);
        officeService.updateAllOfficeFiled(office);
    }

    @PatchMapping("/{officeCode}")
    public void updateFilledOfficeFiled(@PathVariable String officeCode, @RequestBody UpdateFilledOfficeFiledRequest updateFilledOfficeFiledRequest) {
        Office office;
        office = updateFilledOfficeFiledMapper.convertUpdateFilledOfficeFiledToOffice(updateFilledOfficeFiledRequest);
        office.setOfficeCode(officeCode);
        officeService.updateAllOfficeFiled(office);
    }

    @GetMapping("/{officeCode}")
    public Office getOffice(@PathVariable String officeCode) {
        return officeService.getOffice(officeCode);
    }
}
