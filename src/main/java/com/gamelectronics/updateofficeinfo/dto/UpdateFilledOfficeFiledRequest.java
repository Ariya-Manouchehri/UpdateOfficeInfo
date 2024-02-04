package com.gamelectronics.updateofficeinfo.dto;

import com.gamelectronics.updateofficeinfo.utils.OfficeTypeEnum;
import lombok.Data;

@Data
public class UpdateFilledOfficeFiledRequest {
    private String provider;

    private String officeName;

    private OfficeTypeEnum officeType;

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
