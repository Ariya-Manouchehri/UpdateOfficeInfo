package com.gamelectronics.updateofficeinfo.dto;

import com.gam.phoenix.spring.commons.rest.model.response.RESTResponse;
import com.gamelectronics.updateofficeinfo.utils.OfficeTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Data
public class OfficeDto implements RESTResponse {

    private String officeCode;

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
