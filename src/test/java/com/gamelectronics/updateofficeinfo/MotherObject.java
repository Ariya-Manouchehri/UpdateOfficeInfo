package com.gamelectronics.updateofficeinfo;

import com.gamelectronics.updateofficeinfo.dto.OfficesDetail;
import com.gamelectronics.updateofficeinfo.dto.RegisterOfficeRequest;
import com.gamelectronics.updateofficeinfo.dto.UpdateAllOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.dto.UpdateFilledOfficeFiledRequest;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.utils.OfficeTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class MotherObject {

    public static RegisterOfficeRequest createObjectForRegister() {
        RegisterOfficeRequest registerOfficeRequest = new RegisterOfficeRequest();
        List<OfficesDetail> officesDetailList = new ArrayList<>();
        OfficesDetail officesDetail = new OfficesDetail();

        registerOfficeRequest.setProvider("modirsan");
        officesDetail.setOfficeCode("123");
        officesDetail.setOfficeName("tehran");
        officesDetail.setOfficeType(OfficeTypeEnum.CORPORATION);
        officesDetail.setManagerName("amir");
        officesDetail.setManagerNationalId("0023654787");
        officesDetail.setManagerMobileNumber("09215960799");
        officesDetail.setOfficeMobileNumber("09215960799");
        officesDetail.setProvinceName("modirsan");
        officesDetail.setProvinceId("123456");
        officesDetail.setRegionName("tehran");
        officesDetail.setRegionId("221");
        officesDetail.setOfficeAddress("tehran");
        officesDetail.setOfficePostalCode("12457");
        officesDetail.setOfficeStatus("okay");
        officesDetail.setOfficeLevel("1");
        officesDetail.setOfficeLevelType("height");

        officesDetailList.add(officesDetail);

        registerOfficeRequest.setOfficesDetail(officesDetailList);

        return registerOfficeRequest;
    }

    public static List<Office> createOfficeListObject() {
        List<Office> officeList = new ArrayList<>();
        Office office = new Office();

        office.setProvider("modirsan");
        office.setOfficeCode("113");
        office.setOfficeType(OfficeTypeEnum.CORPORATION);
        office.setManagerName("maryam");
        office.setManagerNationalId("0023654787");
        office.setManagerMobileNumber("09215960799");
        office.setOfficeMobileNumber("09215960799");
        office.setProvinceName("modirsan");
        office.setProvinceId("123456");
        office.setRegionName("tehran");
        office.setRegionId("221");
        office.setOfficeAddress("tehran");
        office.setOfficePostalCode("12457");
        office.setOfficeStatus("okay");
        office.setOfficeLevel("1");
        office.setOfficeLevelType("height");

        officeList.add(office);

        return officeList;
    }

    public static Office createOfficeObject() {
        Office office = new Office();

        office.setProvider("modirsan");
        office.setOfficeCode("113");
        office.setOfficeType(OfficeTypeEnum.CORPORATION);
        office.setManagerName("maryam");
        office.setManagerNationalId("0023654787");
        office.setManagerMobileNumber("09215960799");
        office.setOfficeMobileNumber("09215960799");
        office.setProvinceName("modirsan");
        office.setProvinceId("123456");
        office.setRegionName("tehran");
        office.setRegionId("221");
        office.setOfficeAddress("tehran");
        office.setOfficePostalCode("12457");
        office.setOfficeStatus("okay");
        office.setOfficeLevel("1");
        office.setOfficeLevelType("height");

        return office;
    }

    public static Office createForPutOfficeObject() {
        Office office = new Office();

        office.setProvider("modirsan");
        office.setOfficeCode("113");
        office.setOfficeType(OfficeTypeEnum.CORPORATION);
        office.setManagerName("maryam");
        office.setManagerNationalId("0023654787");
        office.setManagerMobileNumber("09215960799");
        office.setOfficeMobileNumber("09215960799");

        return office;
    }

    public static UpdateAllOfficeFiledRequest createUpdateAllOfficeFiledRequestObject() {
       UpdateAllOfficeFiledRequest updateAllOfficeFiledRequest = new UpdateAllOfficeFiledRequest();

        updateAllOfficeFiledRequest.setProvider("modirsan");
        updateAllOfficeFiledRequest.setOfficeName("tehran");
        updateAllOfficeFiledRequest.setOfficeType(OfficeTypeEnum.CORPORATION);
        updateAllOfficeFiledRequest.setManagerName("sara");
        updateAllOfficeFiledRequest.setManagerNationalId("4570124487");
        updateAllOfficeFiledRequest.setManagerMobileNumber("09215960799");
        updateAllOfficeFiledRequest.setOfficeMobileNumber("09215960799");
        updateAllOfficeFiledRequest.setProvinceName("modirsan");
        updateAllOfficeFiledRequest.setProvinceId("123456");
        updateAllOfficeFiledRequest.setRegionName("tehran");
        updateAllOfficeFiledRequest.setRegionId("221");
        updateAllOfficeFiledRequest.setOfficeAddress("tehran");
        updateAllOfficeFiledRequest.setOfficePostalCode("1254878650");
        updateAllOfficeFiledRequest.setOfficeStatus("okay");
        updateAllOfficeFiledRequest.setOfficeLevel("1");
        updateAllOfficeFiledRequest.setOfficeLevelType("height");

        return updateAllOfficeFiledRequest;
    }

    public static UpdateFilledOfficeFiledRequest createUpdateFilledOfficeFiledObject() {
        UpdateFilledOfficeFiledRequest updateFilledOfficeFiledRequest = new UpdateFilledOfficeFiledRequest();

        updateFilledOfficeFiledRequest.setProvider("modirsan");
        updateFilledOfficeFiledRequest.setOfficeName("tehran");
        updateFilledOfficeFiledRequest.setOfficeType(OfficeTypeEnum.CORPORATION);
        updateFilledOfficeFiledRequest.setManagerName("sara");
        updateFilledOfficeFiledRequest.setManagerNationalId("4570124487");
        updateFilledOfficeFiledRequest.setManagerMobileNumber("09215960799");
        updateFilledOfficeFiledRequest.setOfficeMobileNumber("09215960799");

        return updateFilledOfficeFiledRequest;
    }
}
