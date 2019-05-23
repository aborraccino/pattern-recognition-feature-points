package com.aborraccino.patternrecognition.featurepoints.api.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aborraccino.patternrecognition.featurepoints.dto.LineDTO;
import com.aborraccino.patternrecognition.featurepoints.dto.PointDTO;
import com.aborraccino.patternrecognition.featurepoints.service.FeaturePointsService;

@RestController
@RequestMapping("/feature-points/api/v1")
public class FeaturePointsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeaturePointsController.class);
	
	private final FeaturePointsService featurePointsService;
	
	@Autowired
	public FeaturePointsController(FeaturePointsService featurePointsServiceImpl) {
		this.featurePointsService = featurePointsServiceImpl;
	}
	
	@PostMapping("/point")
    public ResponseEntity<HttpStatus> addPoint(@Valid @RequestBody PointDTO pointDTO) {
		LOGGER.info("addPoint() = {}", pointDTO);
		
		featurePointsService.addPoint(pointDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }
	
	@GetMapping("/space")
    public ResponseEntity<Set<PointDTO>> getSpace() {
		LOGGER.info("getSpace()");
		
		Set<PointDTO> allPoints = featurePointsService.getSpace();
		return new ResponseEntity<Set<PointDTO>>(allPoints, HttpStatus.OK);
    }
	
	@GetMapping("/lines/{n}")
    public ResponseEntity<List<LineDTO>> getLines(@PathVariable int n) {
		LOGGER.info("getLine() = {}", n);
		
		List<LineDTO> lines = featurePointsService.getLines(n);
		return new ResponseEntity<List<LineDTO>>(lines, HttpStatus.OK);
    }
	
	@DeleteMapping("/space")
    public ResponseEntity<HttpStatus> cleanSpace() {
		LOGGER.info("cleanSpace()");
		
		featurePointsService.cleanSpace();
		return new ResponseEntity<HttpStatus>(HttpStatus.RESET_CONTENT);
    }

}
