package com.gamelectronics.updateofficeinfo.dto;

import lombok.Data;

@Data
public class UpdateAllOfficeFiledRequest {
    private String provider;

    private String officeName;

    private String officeType;

    private String managerName;

    private String managerNationalId;

    private String managerMobileNumber;

    private String officeMobileNumber;

    private String provinceName;

    private String provinceId;

    private String regionName;

    private String regionId;

    private String officeAddress;

    private String officePostalCode;

    private String officeStatus;

    private String officeLevel;

    private String officeLevelType;
}
