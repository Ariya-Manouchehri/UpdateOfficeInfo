package com.gamelectronics.updateofficeinfo.dto;

import com.gamelectronics.updateofficeinfo.utils.OfficeTypeEnum;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNotNullOfficeFiledRequest {
    @Pattern(regexp = "modirsan")
    private String provider;

    private String officeName;

    private OfficeTypeEnum officeType;

    private String managerName;

    @Pattern(regexp = "\\d{10}")
    private String managerNationalId;

    private String managerMobileNumber;

    private String officeMobileNumber;

    private String provinceName;

    private String provinceId;

    private String regionName;

    private String regionId;

    private String officeAddress;

    @Pattern(regexp = "^(\\d{10}|\\d{11})$")
    private String officePostalCode;

    private String officeStatus;

    @Size(max = 10)
    private String officeLevel;

    @Size(max = 10)
    private String officeLevelType;
}
