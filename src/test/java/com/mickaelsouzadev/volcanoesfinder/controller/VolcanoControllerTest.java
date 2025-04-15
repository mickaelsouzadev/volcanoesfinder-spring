package com.mickaelsouzadev.volcanoesfinder.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mickaelsouzadev.volcanoesfinder.model.Volcano;
import com.mickaelsouzadev.volcanoesfinder.service.VolcanoService;

@WebMvcTest(VolcanoController.class)
public class VolcanoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VolcanoService volcanoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListVolcanoes() throws Exception {
        List<Volcano> volcanoes = new ArrayList<>();
        volcanoes.add(new Volcano("Krakatoa", "Sunda Strait", "Stratovolcano"));
        volcanoes.add(new Volcano("Vesuvius", "Italy", "Stratovolcano"));

        when(volcanoService.listVolcanoes()).thenReturn(volcanoes);

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/api/volcanoes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Volcano> responseVolcanoes = objectMapper.readValue(result,
                objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Volcano.class));

        // assertThat(responseVolcanoes).containsExactlyElementsOf(volcanoes);
    }
}
