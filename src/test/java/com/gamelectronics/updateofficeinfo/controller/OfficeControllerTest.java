package com.gamelectronics.updateofficeinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamelectronics.updateofficeinfo.MotherObject;
import com.gamelectronics.updateofficeinfo.mapper.RegisterOfficeMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateAllOfficeFiledMapper;
import com.gamelectronics.updateofficeinfo.mapper.UpdateFilledOfficeFiledMapper;
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
    private UpdateFilledOfficeFiledMapper updateFilledOfficeFiledMapper;

    @Test
    void registerOffice() throws Exception {

        Mockito.when(officeService.registerOffice(any())).thenReturn(MotherObject.createOfficeListObject());
        Mockito.when(registerOfficeMapper.convertOfficeDetailToOffice(any())).thenReturn(MotherObject.createOfficeListObject());

        mockMvc.perform(post("/officeService")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createObjectForRegister())))
                .andExpect(jsonPath("$.officesCount", equalTo(1)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateAllOfficeFiled() throws Exception {
        Mockito.when(updateAllOfficeFiledMapper.convertUpdateAllOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(put("/officeService/{officeCode}","113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createUpdateAllOfficeFiledRequestObject())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateFilledOfficeFiled() throws Exception {
        Mockito.when(updateFilledOfficeFiledMapper.convertUpdateFilledOfficeFiledToOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(patch("/officeService/{officeCode}","113")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MotherObject.createUpdateFilledOfficeFiledObject())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getOffice() throws Exception {
        Mockito.when(officeService.getOffice(any())).thenReturn(MotherObject.createOfficeObject());

        mockMvc.perform(get("/officeService/{officeCode}","113")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

}