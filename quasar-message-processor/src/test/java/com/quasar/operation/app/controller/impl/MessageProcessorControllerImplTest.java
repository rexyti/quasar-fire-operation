package com.quasar.operation.app.controller.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.quasar.operation.TestUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class MessageProcessorControllerImplTest {

    @Autowired
	private MockMvc mvc;

    @Test
    void testPostTopSecretSucces() throws Exception{
        mvc.perform(
				post("/topsecret/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(TestUtil.getValidMessageRequest()))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message", is("este es un mensaje secreto")));
    }

	@Test
    void testPostTopSecretFail() throws Exception{
        mvc.perform(
			post("/topsecret/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.getInvalidMessageRequest()))
			.andExpect(status().isNotFound())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
