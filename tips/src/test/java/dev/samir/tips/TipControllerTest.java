package dev.samir.tips;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit test for the TipController class.
 * This test class uses MockMvc to perform HTTP requests and verify responses.
 * It tests the endpoints for getting a random tip, getting all tips,
 * getting a tip by ID, inserting a new tip, and updating an existing tip.
 */
@WebMvcTest(TipController.class)
class TipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TipService tipService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Retrieves a random tip from the service and verifies the response.
     * @throws Exception if an error occurs during the request
     */
    @Test
    void testGetRandomTip() throws Exception  {
        Tip tip = new Tip(1L, "Random Tip");

        when(tipService.findRandomTip()).thenReturn(tip);

        mockMvc.perform(get("/api/tip/random"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(tip.getId()))
	        .andExpect(jsonPath("$.message").value(tip.getMessage()));
    }

    /**
     * Get all tips from the service and verifies the response.
     * @throws Exception if an error occurs during the request
     */
    @Test
    void testGetAllTips() throws Exception {
        List<Tip> tips = Arrays.asList(new Tip(1L, "Tip 1"), new Tip(2L, "Tip 2"));

        when(tipService.getAllTips()).thenReturn(tips);

        mockMvc.perform(get("/api/tip"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(tips.size()));
    }

    /**
     * Find a tip by ID and verifies the response.
     * @throws Exception if an error occurs during the request
     */
    @Test
    void testGetTipById() throws Exception {
        Tip tip = new Tip(1L, "Find by ID");

        when(tipService.findById(1L)).thenReturn(tip);

        mockMvc.perform(get("/api/tip/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(tip.getId()))
            .andExpect(jsonPath("$.message").value(tip.getMessage()));
    }

    /**
     * Insert a new tip and verifies the response.
     * @throws Exception if an error occurs during the request
     */
    @Test
    void testInsertTip() throws Exception {
        Tip tip = new Tip(null, "New Tip");
        Tip savedTip = new Tip(1L, "New Tip");

        when(tipService.insert(Mockito.any())).thenReturn(savedTip);

        mockMvc.perform(post("/api/tip")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(tip)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(savedTip.getId()))
	        .andExpect(jsonPath("$.message").value(savedTip.getMessage()));
    }

    /**
     * Update an existing tip and verifies the response.
     * @throws Exception if an error occurs during the request
     */
    @Test
    void testUpdateTipSuccess() throws Exception {
        Tip tip = new Tip(1L, "Updated Tip");

        when(tipService.update(Mockito.any())).thenReturn(tip);

        mockMvc.perform(patch("/api/tip")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(tip)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(tip.getId()))
            .andExpect(jsonPath("$.message").value(tip.getMessage()));
    }

    /**
	 * Attempt to update a non-existent tip and verifies the response.
	 * @throws Exception if an error occurs during the request
	 */
    @Test
    void testUpdateTipNotFound() throws Exception {
        Tip tip = new Tip(99L, "Non-existent Tip");

        when(tipService.update(Mockito.any())).thenThrow(TipNotFoundException.class);

        mockMvc.perform(patch("/api/tip")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content(objectMapper.writeValueAsString(tip)))
	        .andExpect(status().isNotFound());
    }
    
}
