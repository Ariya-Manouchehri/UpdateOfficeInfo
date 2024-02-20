package com.gamelectronics.updateofficeinfo.serviceImpl;

import com.gam.phoenix.spring.commons.service.NonPersistenceServiceException;
import com.gamelectronics.updateofficeinfo.model.MotherObject;
import com.gamelectronics.updateofficeinfo.model.Office;
import com.gamelectronics.updateofficeinfo.repository.OfficeRepository;
import com.gamelectronics.updateofficeinfo.utils.ImportDataToSaman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class OfficeServiceImplTest {

    @InjectMocks
    OfficeServiceImpl officeService;

    @Mock
    OfficeRepository officeRepository;

    @Mock
    ImportDataToSaman importDataToSaman;

    @Test
    void given_offices_when_officeRepository_call_saveAll_then_return_officeList() throws NonPersistenceServiceException {
        Mockito.when(officeRepository.saveAll(any())).thenReturn(MotherObject.createOfficesObject());
        Mockito.doNothing().when(importDataToSaman).setOfficeDataForSamanSystem(any());

        List<Office> officeList = officeService.registerOffice(MotherObject.createOfficesObject());
        Assertions.assertNotNull(officeList);
        Assertions.assertEquals(1, officeList.size());
        Assertions.assertDoesNotThrow(() -> officeService.registerOffice(MotherObject.createOfficesObject()));
    }

    @Test
    void given_valid_OfficeCode_when_officeRepository_call_save_then_return_changeDataBaseData() throws InvocationTargetException, IllegalAccessException, NonPersistenceServiceException {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(Optional.of(MotherObject.createOfficeObject()));
        Mockito.doNothing().when(importDataToSaman).setOfficeDataForSamanSystem(any());

        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
        officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject());

        Mockito.verify(officeRepository, Mockito.times(2)).save(any());
    }

    @Test
    void given_invalid_OfficeCode_when_officeRepository_notCall_save_then_return_notFoundException() throws NonPersistenceServiceException {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NonPersistenceServiceException.class, () -> officeService.updateAllOfficeFiled(MotherObject.createOfficeObject()));
        Assertions.assertThrows(NonPersistenceServiceException.class, () -> officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject()));

        Mockito.verify(officeRepository, Mockito.never()).save(any());
    }

    @Test
    void given_valid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_office() throws NonPersistenceServiceException {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(Optional.of(MotherObject.createOfficeObject()));

        Office office = officeService.getOffice("123");

        Assertions.assertEquals(MotherObject.createOfficeObject(), office);
    }

    @Test
    void given_invalid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_notFoundException() {
        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NonPersistenceServiceException.class, () -> officeService.getOffice("123"));
    }
}