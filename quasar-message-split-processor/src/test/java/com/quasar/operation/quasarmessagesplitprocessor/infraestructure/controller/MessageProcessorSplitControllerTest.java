package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.google.gson.Gson;
import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;
import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageProcessorSplitControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    MessageRepository messageRepository;

    @Test
    void testPostTopSecretSuccess() throws Exception {
        mvc.perform(
                        post("/topsecret_split/sato")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(TestUtils.getValidMessageRequest())))
                .andExpect(status().isNoContent());

    }


    @Test
    void testGetTopSecretSuccess() throws Exception {
        String uuid = UUID.randomUUID().toString();
        messageRepository.createMessage(uuid,uuid, null, "FAIL");

        mvc.perform(get("/topsecret_split/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(uuid)));

    }
}
