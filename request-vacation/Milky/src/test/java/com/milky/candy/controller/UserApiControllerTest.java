package com.milky.candy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milky.candy.domain.request.ReqLoginDto;

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
    }
}
