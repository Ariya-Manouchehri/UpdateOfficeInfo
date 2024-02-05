package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.UpdateNotNullOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.model.Office;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateNotNullOfficeFiledMapper {
    Office convertUpdateNotNullOfficeFiledToOffice(UpdateNotNullOfficeFiledRequest updateNotNullOfficeFiledRequest);
}
