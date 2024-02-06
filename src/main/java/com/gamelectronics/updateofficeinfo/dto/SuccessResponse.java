package com.gamelectronics.updateofficeinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    T result;
}
