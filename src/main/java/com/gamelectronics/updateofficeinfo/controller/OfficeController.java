package com.gamelectronics.updateofficeinfo.controller;

import com.gamelectronics.updateofficeinfo.dto.*;
import com.gamelectronics.updateofficeinfo.exception.AuthorizationException;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateNotNullOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@RestController
@RequestMapping(OfficeController.SERVICE_URL)
public class OfficeController {
    public static final String SERVICE_URL = "/officeService";

    @Value(value = "${X-SystemName}")
    private String X_SYSTEM_NAME;

    @Value(value = "${X-SystemPassword}")
    private String X_SYSTEM_PASSWORD;

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
    public SuccessResponse<RegisterOfficeResponse> registerOffice(@Valid @RequestBody RegisterOfficeRequest registerOfficeRequest, @RequestHeader HttpHeaders headers) {
        if (X_SYSTEM_NAME.equals(headers.getFirst("X-SystemName")) && X_SYSTEM_PASSWORD.equals(headers.getFirst("X-SystemPassword"))) {
            RegisterOfficeResponse registerOfficeResponse = new RegisterOfficeResponse();
            ArrayList<Office> offices = new ArrayList<>(registerOfficeRequest.getOfficesDetails().size());

            offices.addAll(registerOfficeMapper.convertOfficeDetailsToOffices(registerOfficeRequest.getOfficesDetails()));
            offices.forEach(office -> office.setProvider(registerOfficeRequest.getProvider()));

            registerOfficeResponse.setOfficesCount(officeService.registerOffice(offices).size());
            return new SuccessResponse<>(registerOfficeResponse);
        } else {
            throw new AuthorizationException(headers.getFirst("X-SystemName") + " " + headers.getFirst("X-SystemPassword") + " unAuthorized.");
        }
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
    public SuccessResponse<Office> getOffice(@PathVariable String officeCode) {
        return new SuccessResponse<>(officeService.getOffice(officeCode));
    }
}
