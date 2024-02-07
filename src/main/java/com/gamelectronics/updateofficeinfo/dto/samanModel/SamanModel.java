package com.gamelectronics.updateofficeinfo.dto.samanModel;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
public class SamanModel {
    private String nodeType;
    private String name;
    private Map<String, Object> properties;
}
