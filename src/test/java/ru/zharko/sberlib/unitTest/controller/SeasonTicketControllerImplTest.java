package ru.zharko.sberlib.unitTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.zharko.sberlib.controller.impl.SeasonTicketControllerImpl;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.service.SeasonTicketService;
import ru.zharko.sberlib.utils.TestObjects;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SeasonTicketControllerImplTest {

    @Mock
    private SeasonTicketService seasonTicketService;

    @InjectMocks
    private SeasonTicketControllerImpl controller;

    private static MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void mockInitialize() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findByClientName_withoutBooks_shouldReturnOk() throws Exception {
        SeasonTicketDto seasonTicketDto = TestObjects.getSeasonTicketDtoOne();

        when(seasonTicketService.findByClientName("Иванов", false)).thenReturn(seasonTicketDto);

        String jsonSeasonTicketDo = objectMapper.writeValueAsString(seasonTicketDto);

        mockMvc.perform(get("/season-ticket/by-client-name")
                        .param("clientName", "Иванов")
                        .param("withBooks", "false"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonSeasonTicketDo));
    }

    @Test
    void findByClientName_withBooks_shouldReturnOk() throws Exception {
        SeasonTicketDto seasonTicketDtoWithBooks = TestObjects.getSeasonTicketDtoWithBooks();

        when(seasonTicketService.findByClientName("Иванов", true)).thenReturn(seasonTicketDtoWithBooks);

        String jsonSeasonTicketDo = objectMapper.writeValueAsString(seasonTicketDtoWithBooks);

        mockMvc.perform(get("/season-ticket/by-client-name")
                        .param("clientName", "Иванов")
                        .param("withBooks", "true"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonSeasonTicketDo));
    }
}
