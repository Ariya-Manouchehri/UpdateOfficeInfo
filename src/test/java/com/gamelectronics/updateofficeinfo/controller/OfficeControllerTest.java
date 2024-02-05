package com.gamelectronics.updateofficeinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamelectronics.updateofficeinfo.MotherObject;
import com.gamelectronics.updateofficeinfo.exception.NotFoundOfficeException;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateNotNullOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.service.OfficeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfficeController.class)
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OfficeService officeService;

    @MockBean
    private RegisterOfficeMapper registerOfficeMapper;

    @MockBean
    private UpdateAllOfficeFiledMapper updateAllOfficeFiledMapper;

    @MockBean
    private UpdateNotNullOfficeFiledMapper updateNotNullOfficeFiledMapper;

    @Test
    void given_valid_registerOfficeRequest_when_officeService_call_registerOffice_then_return_response_200() throws Exception {
        Mockito.when(registerOfficeMapper.convertOfficeDetailsToOffices(any())).thenReturn(MotherObject.createOfficesObject());
        Mockito.when(officeService.registerOffice(any())).thenReturn(MotherObject.createOfficesObject());

        mockMvc.perform(post("/officeService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createRegisterOfficeRequestObject())))
                .andExpect(jsonPath("$.officesCount", equalTo(1)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void given_invalid_registerOfficeRequest_when_officeService_call_registerOffice_then_return_response_400() throws Exception {
        Mockito.when(registerOfficeMapper.convertOfficeDetailsToOffices(any())).thenReturn(MotherObject.createOfficesObject());
        Mockito.when(officeService.registerOffice(any())).thenReturn(MotherObject.createOfficesObject());

        mockMvc.perform(post("/officeService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createInvalidRegisterOfficeRequestObject())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void given_valid_officeCode_and_updateAllOfficeFiledRequest_when_officeService_call_updateAllOfficeFiled_then_return_response_200() throws Exception {
        Mockito.when(updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(put("/officeService/{officeCode}", "113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createUpdateAllOfficeFiledRequestObject())))
                .andExpect(status().is2xxSuccessful());

        Mockito.verify(officeService, Mockito.times(1)).updateAllOfficeFiled(any());
    }

    @Test
    void given_invalid_officeCode_and_updateAllOfficeFiledRequest_when_officeService_call_updateAllOfficeFiled_then_return_response_400() throws Exception {
        Mockito.when(updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(put("/officeService/{officeCode}", "113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createInvalidUpdateAllOfficeFiledRequestObject())))
                .andExpect(status().is4xxClientError());

        Mockito.verify(officeService, Mockito.never()).updateAllOfficeFiled(any());
    }

    @Test
    void given_valid_officeCode_and_updateNotNullOfficeFiledRequest_when_officeService_call_updateNotNullOfficeFiled_then_return_response_200() throws Exception {
        Mockito.when(updateNotNullOfficeFiledMapper.convertUpdateNotNullOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(patch("/officeService/{officeCode}", "113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createUpdateNotNullOfficeFiledObject())))
                .andExpect(status().is2xxSuccessful());

        Mockito.verify(officeService, Mockito.times(1)).updateNotNullOfficeFiled(any());
    }

    @Test
    void given_invalid_officeCode_and_updateNotNullOfficeFiledRequest_when_officeService_call_updateNotNullOfficeFiled_then_return_response_400() throws Exception {
        Mockito.when(updateNotNullOfficeFiledMapper.convertUpdateNotNullOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(patch("/officeService/{officeCode}", "113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createInvalidUpdateNotNullOfficeFiledObject())))
                .andExpect(status().is4xxClientError());

        Mockito.verify(officeService, Mockito.never()).updateNotNullOfficeFiled(any());
    }

    @Test
    void given_valid_officeCode_when_officeService_call_getOffice_then_return_response_200() throws Exception {
        Mockito.when(officeService.getOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(get("/officeService/{officeCode}", "113")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

}