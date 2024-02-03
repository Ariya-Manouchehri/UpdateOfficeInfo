package com.gamelectronics.updateofficeinfo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue
    @Column(name = "office_code")
    private String officeCode;

    @Column(name = "provider")
    private String provider;

    @Column(name = "office_name")
    private String officeName;

    @Column(name = "office_type")
    private String officeType;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_national_id")
    private String managerNationalId;

    @Column(name = "manager_mobile_number")
    private String managerMobileNumber;

    @Column(name = "office_mobile_number")
    private String officeMobileNumber;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_id")
    private String regionId;

    @Column(name = "office_address")
    private String officeAddress;

    @Column(name = "office_postal_code")
    private String officePostalCode;

    @Column(name = "office_status")
    private String officeStatus;

    @Column(name = "office_level")
    private String officeLevel;

    @Column(name = "office_level_type")
    private String officeLevelType;
}
