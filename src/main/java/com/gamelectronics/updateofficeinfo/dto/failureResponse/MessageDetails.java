package com.gamelectronics.updateofficeinfo.dto.failureResponse;

import lombok.Data;

@Data
public class MessageDetails {
    private String text;
    private String code;
    private String arguments;
    private String type;
}
