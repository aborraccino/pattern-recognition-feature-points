package com.aborraccino.patternrecognition.featurepoints.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LineDTO {
	
	private Set<PointDTO> points;

	public Set<PointDTO> getPoints() {
		return points;
	}

	public void setPoints(Set<PointDTO> points) {
		this.points = points;
	}

}
