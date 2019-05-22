package com.aborraccino.patternrecognition.featurepoints.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Line {
	
	private Set<Point> points = new HashSet<>();

	public Set<Point> getPoints() {
		return points;
	}

	public void setPoints(Set<Point> points) {
		this.points = points;
	}

	public void addPoints(Point ... points) {
		if(points != null) {
			Arrays.stream(points)
				.forEach(p -> this.points.add(p));
		}
	}
	
	public boolean isEmpty() {
		return points.size() == 0;
	}

	@Override
	public String toString() {
		return "Line [points=" + points + "]";
	}
}
