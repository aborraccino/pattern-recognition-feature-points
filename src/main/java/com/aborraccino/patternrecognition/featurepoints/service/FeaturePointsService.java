package com.aborraccino.patternrecognition.featurepoints.service;

import java.util.List;
import java.util.Set;

import com.aborraccino.patternrecognition.featurepoints.dto.LineDTO;
import com.aborraccino.patternrecognition.featurepoints.dto.PointDTO;

public interface FeaturePointsService {	
	
    public void addPoint(PointDTO pointDTO);
	
    public Set<PointDTO> getSpace();
	
    public List<LineDTO> getLines(int n);
	
    public void cleanSpace();

}
