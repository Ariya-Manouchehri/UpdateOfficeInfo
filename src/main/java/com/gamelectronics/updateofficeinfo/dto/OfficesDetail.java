package com.gamelectronics.updateofficeinfo.dto;

import com.gamelectronics.updateofficeinfo.utils.OfficeTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class OfficesDetail {

    @NotNull
    private String officeCode;

    @NotNull
    private String officeName;

    @NotNull
    private OfficeTypeEnum officeType;

    @NotNull
    private String managerName;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String managerNationalId;

    @NotNull
    private String managerMobileNumber;

    @NotNull
    private String officeMobileNumber;

    @NotNull
    private String provinceName;

    @NotNull
    private String provinceId;

    @NotNull
    private String regionName;

    @NotNull
    private String regionId;

    @NotNull
    private String officeAddress;

    @NotNull
    @Pattern(regexp = "^(\\d{10}|\\d{11})$")
    private String officePostalCode;

    @NotNull
    private String officeStatus;

    @NotNull
    private String officeLevel;

    @NotNull
    private String officeLevelType;
}
