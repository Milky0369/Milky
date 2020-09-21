package com.milky.candy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milky.candy.domain.request.ReqLoginDto;
import com.milky.candy.domain.request.ReqVacationDto;

@RunWith(SpringRunner.class)
@WebMvcTest(UserApiController.class)
public class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void userLoginTest() throws Exception {
    	
//        String content = objectMapper.writeValueAsString(new ReqLoginDto("userId", "milky"));
//        
//        mockMvc.perform(post("/request/setter")
//               .content(content)
//               .contentType(MediaType.APPLICATION_JSON_UTF8))
//               .andExpect(status().isOk())
//               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//               .andExpect(content().json(content));
    	
    	RestTemplate tmp = new RestTemplate();
    	
    	String url = "http://192.168.0.4:18090/milky/vacation";
    	
    	ReqVacationDto reqVacation = new ReqVacationDto();
    	reqVacation.setUserId("milky");
    	reqVacation.setVacationType("DAY_OFF");
    	reqVacation.setStartDate(LocalDateTime.now());
    	reqVacation.setEndDate(LocalDateTime.of(2020, 3, 4, 0, 0, 0, 0));
    	reqVacation.setComment("test");
    	
    	String result =  tmp.postForObject(url, reqVacation, String.class);
    	System.out.println(result);
    	
    }
    
    public static void main(String[] args) {
    	RestTemplate tmp = new RestTemplate();
    	
    	String url = "http://192.168.0.4:18090/milky/vacation";
    	
    	ReqVacationDto reqVacation = new ReqVacationDto();
    	reqVacation.setUserId("milky");
    	reqVacation.setVacationType("DAY_OFF");
    	reqVacation.setStartDate(LocalDateTime.now());
    	reqVacation.setEndDate(LocalDateTime.of(2020, 3, 4, 0, 0, 0, 0));
    	reqVacation.setComment("test");
    	
    	String result =  tmp.postForObject(url, reqVacation, String.class);
    	System.out.println(result);
    	
	}
}
