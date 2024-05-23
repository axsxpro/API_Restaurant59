package com.api.restaurant59.ControllerTest;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Service.EntityService.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    private CityDTO cityDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cityDto = new CityDTO();
        cityDto.setCityName("Paris");
        cityDto.setPostalCode("75000");
        cityDto.setInseeCode("12345");
    }

    @Test
    public void testCreateCity() throws Exception {
        given(cityService.create(any(CityDTO.class))).willReturn(cityDto);

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cityName\":\"Paris\",\"postalCode\":\"75000\",\"inseeCode\":\"12345\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cityName").value("Paris"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        List<CityDTO> cities = Arrays.asList(cityDto);
        given(cityService.readAll()).willReturn(cities);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("Paris"));
    }

    @Test
    public void testGetCityById() throws Exception {
        given(cityService.getById(anyInt())).willReturn(cityDto);

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").value("Paris"));
    }

    @Test
    public void testUpdateCity() throws Exception {
        given(cityService.update(anyInt(), any(CityDTO.class))).willReturn(cityDto);

        mockMvc.perform(put("/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cityName\":\"Paris\",\"postalCode\":\"75000\",\"inseeCode\":\"12345\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.cityName").value("Paris"));
    }

    @Test
    public void testDeleteCity() throws Exception {
        mockMvc.perform(delete("/delete/{id}", 1))
                .andExpect(status().isNoContent());
    }


}