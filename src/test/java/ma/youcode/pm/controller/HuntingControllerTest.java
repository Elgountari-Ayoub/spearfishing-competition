package ma.youcode.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.enums.IdentityDocumentType;
import ma.youcode.pm.model.*;
import ma.youcode.pm.service.Implementation.HuntingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HuntingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HuntingService huntingService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private TestRestTemplate restTemplate;
    HuntingDTO huntingDTO = new HuntingDTO(/* Set valid data */);
    @Test
    void save() throws Exception {
        String url = "http://localhost:8080/api/v1/huntings";

        huntingDTO.setNumberOfFish(1);

        Fish fish = new Fish();
        fish.setId(1L);
        huntingDTO.setFish(fish);

        Member member = new Member();
        member.setNum(1L);

        Competition competition = new Competition();
        competition.setCode("ess-20-12-23");
        huntingDTO.setCompetition(competition);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(huntingDTO);
        String jsonResponse = "Member is required";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/huntings")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.member").value(jsonResponse))
                .andReturn();

        //TEST - 2
//
//        huntingDTO.setMember(member);
//        jsonRequest = objectMapper.writeValueAsString(huntingDTO);
//
//        HuntingDTO expectedHuntingResult = huntingService.save(huntingDTO);
//
//        MvcResult result =
//                mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/huntings")
//                                .contentType("application/json")
//                                .content(jsonRequest))
//                        .andExpect(MockMvcResultMatchers.status().isCreated())
//                        .andReturn();
//
//        jsonResponse = result.getResponse().getContentAsString();
//        System.out.println(jsonResponse);
//        HuntingDTO actualHuntingResult = objectMapper.readValue(jsonResponse, HuntingDTO.class);
//
//        assertEquals(expectedHuntingResult, actualHuntingResult);
    }
}