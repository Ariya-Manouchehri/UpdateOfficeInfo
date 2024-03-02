package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.UpdateAllOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.model.Office;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateAllOfficeFiledMapper {
    Office convertUpdateAllOfficeFiledToOffice(UpdateAllOfficeFiledRequest updateAllOfficeFiledRequest);
}
