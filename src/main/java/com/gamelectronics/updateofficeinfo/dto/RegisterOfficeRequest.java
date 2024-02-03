package com.gamelectronics.updateofficeinfo.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterOfficeRequest {

    private String provider;

    private List<OfficesDetail> officesDetail;
}
