package com.gamelectronics.updateofficeinfo.dto;

import com.gam.phoenix.spring.commons.rest.model.response.RESTResponse;
import lombok.Data;

@Data
public class RegisterOfficeResponse implements RESTResponse {
    private long officesCount;
}
