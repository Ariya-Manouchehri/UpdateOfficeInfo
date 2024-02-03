package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.UpdateAllOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.dto.UpdateFilledOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.model.Office;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateFilledOfficeFiledMapper {
    Office convertUpdateFilledOfficeFiledToOffice(UpdateFilledOfficeFiledRequest updateFilledOfficeFiledRequest);
}
