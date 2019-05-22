package com.aborraccino.patternrecognition.featurepoints.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plane {
	
	private Set<Point> points = new HashSet<>();
	private List<Line> lines = new ArrayList<>();
	
	public Set<Point> getPoints() {
		return points;
	}
	public void setPoints(Set<Point> points) {
		this.points = points;
	}
	public List<Line> getLines() {
		return lines;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	public void addPoint(Point point) {
		points.add(point);
	}
	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void clear() {
		points = new HashSet<>();
		lines = new ArrayList<>();
	}

}
