package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.OfficesDetail;
import com.gamelectronics.updateofficeinfo.model.Office;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegisterOfficeMapper {

    List<Office> convertOfficeDetailsToOffices(List<OfficesDetail> officesDetail);
}
