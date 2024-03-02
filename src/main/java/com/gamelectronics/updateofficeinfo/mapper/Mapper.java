package com.gamelectronics.updateofficeinfo.mapper;

import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeResponse;
import com.gamelectronics.updateofficeinfo.model.Office;

public class Mapper {

    public static RegisterOfficeResponse convertOfficeCountToRegisterOfficeResponse(long officeCount) {
        RegisterOfficeResponse registerOfficeResponse = new RegisterOfficeResponse();
        registerOfficeResponse.setOfficesCount(officeCount);
        return registerOfficeResponse;
    }

    public static Office setOfficeCodeForOffice(Office office,String officeCode){
        office.setOfficeCode(officeCode);
        return office;
    }
}
