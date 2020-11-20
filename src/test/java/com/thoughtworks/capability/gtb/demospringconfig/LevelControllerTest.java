package com.thoughtworks.capability.gtb.demospringconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class LevelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Test
    public void should_get_level() throws Exception {
        if ("dev".equals(activeProfile)) {
            mockMvc.perform(get("/level"))
                    .andExpect(content().string("basic"));
        } else if ("test".equals(activeProfile)) {
            mockMvc.perform(get("/level"))
                    .andExpect(content().string("advanced"));
        } else if ("prod".equals(activeProfile)) {
            mockMvc.perform(get("/level"))
                    .andExpect(content().string("advanced"));
        } else {
            throw new RuntimeException("unknown env");
        }
    }
}
