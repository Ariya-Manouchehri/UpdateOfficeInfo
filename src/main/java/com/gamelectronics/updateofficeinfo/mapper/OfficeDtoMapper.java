package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.OfficeDto;
import com.gamelectronics.updateofficeinfo.dto.OfficesDetail;
import com.gamelectronics.updateofficeinfo.model.Office;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfficeDtoMapper {

    OfficeDto convertOfficeToOfficeDto(Office office);
}
