package com.gamelectronics.updateofficeinfo.serviceImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OfficeServiceImplTest {
//
//    @InjectMocks
//    OfficeServiceImpl officeService;
//
//    @Mock
//    OfficeRepository officeRepository;
//
//    @Test
//    void given_offices_when_officeRepository_call_saveAll_then_return_officeList() {
//        Mockito.when(officeRepository.saveAll(any())).thenReturn(MotherObject.createOfficesObject());
//
//        List<Office> officeList = officeService.registerOffice(MotherObject.createOfficesObject());
//        Assertions.assertNotNull(officeList);
//        Assertions.assertEquals(1, officeList.size());
//    }
//
//    @Test
//    void given_valid_OfficeCode_when_officeRepository_call_save_then_return_changeDataBaseData() throws InvocationTargetException, IllegalAccessException {
//        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());
//
//        officeService.updateAllOfficeFiled(MotherObject.createOfficeObject());
//        officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject());
//
//        Mockito.verify(officeRepository, Mockito.times(2)).save(any());
//    }
//
//    @Test
//    void given_invalid_OfficeCode_when_officeRepository_notCall_save_then_return_notFoundException() throws InvocationTargetException, IllegalAccessException {
//        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);
//        Assertions.assertThrows(NotFoundOfficeException.class, () -> officeService.updateAllOfficeFiled(MotherObject.createOfficeObject()));
//        Assertions.assertThrows(NotFoundOfficeException.class, () -> officeService.updateNotNullOfficeFiled(MotherObject.createOfficeObject()));
//
//        Mockito.verify(officeRepository, Mockito.never()).save(any());
//    }
//
//    @Test
//    void given_valid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_office() {
//        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(MotherObject.createOfficeObject());
//
//        Office office = officeService.getOffice("123");
//
//        Assertions.assertEquals(MotherObject.createOfficeObject(), office);
//    }
//
//    @Test
//    void given_invalid_officeCode_when_officeRepository_call_findByOfficeCode_then_return_notFoundException() {
//        Mockito.when(officeRepository.findByOfficeCode(anyString())).thenReturn(null);
//
//        Assertions.assertThrows(NotFoundOfficeException.class, () -> officeService.getOffice("123"));
//    }
}