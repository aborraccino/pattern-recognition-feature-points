package com.aborraccino.patternrecognition.featurepoints.api.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aborraccino.patternrecognition.featurepoints.dto.LineDTO;
import com.aborraccino.patternrecognition.featurepoints.dto.PointDTO;
import com.aborraccino.patternrecognition.featurepoints.service.FeaturePointsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FeaturePointsController.class)
public class FeaturePointsControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @MockBean
    private FeaturePointsService featurePointsService;
	
    private static final String FEATURE_POINTS_PATH = "/feature-points/api/v1";

    @Test
    public void post_whenPointIsValid_thenResponseIs201() throws Exception {
        // given
        PointDTO dummyPointDTO = new PointDTO(1, 1);
        doNothing().when(featurePointsService).addPoint(dummyPointDTO);

        // when-then
        mockMvc.perform(post(FEATURE_POINTS_PATH+ "/point")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dummyPointDTO)))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void post_whenPointHasInvalidInput_thenResponseIs404() throws Exception {
        // given
        PointDTO dummyPointDTO = new PointDTO();
        doNothing().when(featurePointsService).addPoint(dummyPointDTO);

        // when-then
        mockMvc.perform(post(FEATURE_POINTS_PATH+ "/point")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(null)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void get_whenSpaceExists_thenResponseIs200AndJsonResponseIsCorrect() throws Exception {
        // given
    	Set<PointDTO> dummyAllPoints = Collections.singleton(new PointDTO(1, 2));
        given(featurePointsService.getSpace()).willReturn(dummyAllPoints);

        // when-then
        mockMvc.perform(get(FEATURE_POINTS_PATH + "/space"))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$.[0].x", is(dummyAllPoints.stream().findFirst().get().getX())))
        		.andExpect(jsonPath("$.[0].y", is(dummyAllPoints.stream().findFirst().get().getY())));
    }
    
    @Test
    public void get_whenLinesExists_thenResponseIs200AndJsonResponseIsCorrect() throws Exception {
        // given
    	var n = 3;
    	List<LineDTO> dummyOfferDTO = new ArrayList<LineDTO>();
    	LineDTO dummyLine = new LineDTO();
    	dummyLine.setPoints(Collections.singleton(new PointDTO(1, 2)));
    	dummyOfferDTO.add(dummyLine);
    	
        given(featurePointsService.getLines(n)).willReturn(dummyOfferDTO);

        // when-then
        mockMvc.perform(get(buildUrlWithIdVariable(FEATURE_POINTS_PATH + "/lines"), n)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$.[0].points[0].x", is(dummyOfferDTO.get(0).getPoints().stream().findFirst().get().getX())))
        		.andExpect(jsonPath("$.[0].points[0].y", is(dummyOfferDTO.get(0).getPoints().stream().findFirst().get().getY())));
    }
    
    @Test
    public void delete_space() throws Exception {
        // given
        doNothing().when(featurePointsService).cleanSpace();

        // when-then
        mockMvc.perform(get(FEATURE_POINTS_PATH + "/space")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    private String buildUrlWithIdVariable(String path) {
		return new StringBuilder()
                .append(path)
                .append("/{n}")
                .toString();
	}
    
}
