package com.gamelectronics.updateofficeinfo.controller;

import com.gamelectronics.updateofficeinfo.dto.*;
import com.gamelectronics.updateofficeinfo.exception.AuthorizationException;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateNotNullOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "create", description = "register new office or if it dose exist update that")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create"),
            @ApiResponse(responseCode = "401", description = "authorization exception"),
            @ApiResponse(responseCode = "500", description = "saman exception")
    })
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemName", content = @Content(schema = @Schema(type = "string")))
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemPassword", content = @Content(schema = @Schema(type = "string")))
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

    @Operation(summary = "update", description = "update all office filed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update"),
            @ApiResponse(responseCode = "401", description = "authorization exception"),
            @ApiResponse(responseCode = "404", description = "not found exception"),
            @ApiResponse(responseCode = "500", description = "saman exception")
    })
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemName", content = @Content(schema = @Schema(type = "string")))
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemPassword", content = @Content(schema = @Schema(type = "string")))
    @PutMapping("/{officeCode}")
    public void updateAllOfficeFiled(@PathVariable String officeCode, @Valid @RequestBody UpdateAllOfficeFiledRequest updateAllOfficeFiledRequest, @RequestHeader HttpHeaders headers) {
        if (X_SYSTEM_NAME.equals(headers.getFirst("X-SystemName")) && X_SYSTEM_PASSWORD.equals(headers.getFirst("X-SystemPassword"))) {
            Office office = updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(updateAllOfficeFiledRequest);
            office.setOfficeCode(officeCode);

            officeService.updateAllOfficeFiled(office);
        } else {
            throw new AuthorizationException(headers.getFirst("X-SystemName") + " " + headers.getFirst("X-SystemPassword") + " unAuthorized.");
        }
    }

    @Operation(summary = "update", description = "update not null office filed.(you can send parameters that you want update that and not need to send all office fields).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update"),
            @ApiResponse(responseCode = "401", description = "authorization exception"),
            @ApiResponse(responseCode = "404", description = "not found exception"),
            @ApiResponse(responseCode = "500", description = "saman exception")
    })
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemName", content = @Content(schema = @Schema(type = "string")))
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemPassword", content = @Content(schema = @Schema(type = "string")))
    @PatchMapping("/{officeCode}")
    public void updateNotNullOfficeFiled(@PathVariable String officeCode, @Valid @RequestBody UpdateNotNullOfficeFiledRequest updateNotNullOfficeFiledRequest, @RequestHeader HttpHeaders headers) throws InvocationTargetException, IllegalAccessException {
        if (X_SYSTEM_NAME.equals(headers.getFirst("X-SystemName")) && X_SYSTEM_PASSWORD.equals(headers.getFirst("X-SystemPassword"))) {
            Office office = updateNotNullOfficeFiledMapper.convertUpdateNotNullOfficeFiledToOffice(updateNotNullOfficeFiledRequest);
            office.setOfficeCode(officeCode);

            officeService.updateNotNullOfficeFiled(office);
        } else {
            throw new AuthorizationException(headers.getFirst("X-SystemName") + " " + headers.getFirst("X-SystemPassword") + " unAuthorized.");
        }
    }

    @Operation(summary = "get office", description = "get office details with send office code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get"),
            @ApiResponse(responseCode = "401", description = "authorization exception"),
            @ApiResponse(responseCode = "404", description = "not found exception"),
            @ApiResponse(responseCode = "500", description = "saman exception")
    })
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemName", content = @Content(schema = @Schema(type = "string")))
    @Parameter(in = ParameterIn.HEADER, name = "X-SystemPassword", content = @Content(schema = @Schema(type = "string")))
    @GetMapping("/{officeCode}")
    public SuccessResponse<Office> getOffice(@PathVariable String officeCode, @RequestHeader HttpHeaders headers) {
        if (X_SYSTEM_NAME.equals(headers.getFirst("X-SystemName")) && X_SYSTEM_PASSWORD.equals(headers.getFirst("X-SystemPassword"))) {
            return new SuccessResponse<>(officeService.getOffice(officeCode));
        } else {
            throw new AuthorizationException(headers.getFirst("X-SystemName") + " " + headers.getFirst("X-SystemPassword") + " unAuthorized.");
        }
    }
}
