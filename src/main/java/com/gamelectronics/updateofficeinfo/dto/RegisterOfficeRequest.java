package com.gamelectronics.updateofficeinfo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class RegisterOfficeRequest {

    @NotNull
    @Pattern(regexp = "modirsan")
    private String provider;

    @NotNull
    @NotEmpty
    private List<OfficesDetail> officesDetail;
}
