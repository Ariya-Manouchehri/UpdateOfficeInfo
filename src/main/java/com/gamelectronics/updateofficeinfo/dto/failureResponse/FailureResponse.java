package com.gamelectronics.updateofficeinfo.dto.failureResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailureResponse {
    String result;
    Messages metadata;
}
