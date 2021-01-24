package com.example.demo.controller;

import com.example.demo.controller.Math.MathController;
import com.example.demo.service.math.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MathService mathService;

    @Test
    public void shouldDoOperation() throws Exception
    {
        mockMvc
                .perform(
                        get("/math/sum")
                                .accept(MediaType.ALL)
                                .queryParam("one", "5.1")
                                .queryParam("two", "5.0")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDoOperationExpectedError() throws Exception
    {
        mockMvc
                .perform(
                        get("/math/sum")
                                .accept(MediaType.ALL)
                                .queryParam("one", "5.1")
                )
                .andExpect(status().is4xxClientError());
    }
}
