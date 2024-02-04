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
    void registerOffice() {
        Mockito.when(officeRepository.saveAll(any())).thenReturn(MotherObject.createOfficeListObject());
        long officeCount = officeRepository.findAll().size();

        List<Office> officeList = officeService.registerOffice(MotherObject.createOfficeListObject());
        Assertions.assertEquals(1,officeCount + officeList.size());
    }

    @Test
    void updateAllOfficeFiled() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());

        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
        Mockito.verify(officeRepository,Mockito.times(1)).save(MotherObject.createOfficeObject());
    }

    @Test
    void notUpdateAllOfficeFiled() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);

        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
        Mockito.verify(officeRepository,Mockito.never()).save(MotherObject.createOfficeObject());
    }

    @Test
    void updateFilledOfficeFiled() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());

        officeService.updateFilledOfficeFiled(MotherObject.createForPutOfficeObject());
        Mockito.verify(officeRepository,Mockito.times(1)).save(MotherObject.createOfficeObject());
    }

    @Test
    void notUpdateFilledOfficeFiled() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);

        officeService.updateFilledOfficeFiled(MotherObject.createForPutOfficeObject());
        Mockito.verify(officeRepository,Mockito.never()).save(MotherObject.createOfficeObject());
    }

    @Test
    void getOffice() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());

        Office office = officeService.getOffice("123");

        Assertions.assertEquals(MotherObject.createOfficeObject() , office);
    }
}