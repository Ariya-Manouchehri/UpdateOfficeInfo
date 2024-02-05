package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gamelectronics.updateofficeinfo.MotherObject;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class OfficeServiceImplTest {

    @InjectMocks
    OfficeServiceImpl officeService;

    @Mock
    OfficeRepository officeRepository;

    @Test
    void given_offices_when_officeRepository_call_saveAll_then_return_officeList() {
        Mockito.when(officeRepository.saveAll(any())).thenReturn(MotherObject.createOfficesObject());

        List<Office> officeList = officeService.registerOffice(MotherObject.createOfficesObject());
        Assertions.assertNotNull(officeList);
        Assertions.assertEquals(1, officeList.size());
    }

    @Test
    void given_valid_OfficeCode_when_officeRepository_call_save_then_return_changeDataBaseData() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());

        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
        officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject());

        Mockito.verify(officeRepository,Mockito.times(1)).save(any());
    }

    @Test
    void given_invalid_OfficeCode_when_officeRepository_notCall_save_then_return_() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);

        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
        officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject());

        Mockito.verify(officeRepository,Mockito.never()).save(any());
    }

    @Test
    void given_valid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_office() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());

        Office office = officeService.getOffice("123");

        Assertions.assertEquals(MotherObject.createOfficeObject() , office);
    }
    @Test
    void given_invalid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);

        Office office = officeService.getOffice("123");
    }
}