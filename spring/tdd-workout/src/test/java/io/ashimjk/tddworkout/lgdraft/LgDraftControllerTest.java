package io.ashimjk.tddworkout.lgdraft;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LgDraftController.class)
public class LgDraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LgDraftService lgDraftService;

    @Test
    @SneakyThrows
    public void getLgDraftByRef_ShouldReturnLgDraft() {
        when(lgDraftService.getLgDraft(anyString())).thenReturn(new LgDraft("123", "lgdraft"));

        mockMvc.perform(get("/api/v1/lg-drafts/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("reference").value("123"))
                .andExpect(jsonPath("data").value("lgdraft"));
    }

    @Test
    @SneakyThrows
    public void getLgDraftByRef_notFound() {
        when(lgDraftService.getLgDraft(anyString())).thenThrow(new LgDraftNotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/lg-drafts/123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}