package com.aborraccino.patternrecognition.featurepoints.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aborraccino.patternrecognition.featurepoints.api.service.FeaturePointsService;
import com.aborraccino.patternrecognition.featurepoints.dto.LineDTO;
import com.aborraccino.patternrecognition.featurepoints.dto.PointDTO;
import com.aborraccino.patternrecognition.featurepoints.exception.InvalidInputException;
import com.aborraccino.patternrecognition.featurepoints.model.Line;
import com.aborraccino.patternrecognition.featurepoints.model.Plane;
import com.aborraccino.patternrecognition.featurepoints.model.Point;

@Service
public class FeaturePointsServiceImpl implements FeaturePointsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeaturePointsServiceImpl.class);
	
	private Plane plane = new Plane();
	
	public void addPoint(PointDTO pointDTO) {
		LOGGER.info("addPoint() = {} ", pointDTO);

		// clear lines
		plane.setLines(new ArrayList<>());
		// add new point
		Point point = new Point(pointDTO.getX(), pointDTO.getY());
		plane.addPoint(point);
	}

	public Set<PointDTO> getSpace() {
		LOGGER.info("getSpace()");
		return plane.getPoints()
				.stream()
				.map(p -> new PointDTO(p.getX(), p.getY()))
				.collect(Collectors.toSet());
	}

	public List<LineDTO> getLines(int n) {
		LOGGER.info("getLines() = {} ", n);
		
		if(n < 2) {
			LOGGER.error("n must be greater than 1");
			throw new InvalidInputException();
		}
		
		// if no lines present, draw lines
		if(plane.getLines().isEmpty()) {
			List<Point> points = new ArrayList<>(plane.getPoints());
			
			for(var i = 0; i < points.size(); i++) {
				
				for(var j = i+1; j < points.size(); j++) {
					Point p1 = points.get(i);
					Point p2 = points.get(j);
					
					// check if is already in line, if so get current, otherwise create new one
					Line currLine = getLineThatContainsPoints(p1, p2);
					if(currLine.isEmpty()) {
						LOGGER.info("NO LINE - created new one with two poins {} {}" , p1, p2);
						currLine.addPoints(p1, p2);
						plane.addLine(currLine);
					}
					
					// loops all node and check if there are some node that are collinear to the current line
					for(var k = 0; k < points.size(); k++) {
						
						if (k==i || k==j) {
							continue;
						}
						
						Point p3 = points.get(k);
						
						if(isPointCollinearToLine(p3, currLine)) {
							// add to the current line
							LOGGER.info("add to the current line the point {}" , p3);
							currLine.addPoints(p3);
						}
					}
				}
			}
			
		}
		
		LOGGER.info("lines are {}" , plane.getLines());
		
		return plane.getLines()
				.stream()
				.filter(l -> l.getPoints().size() >= n)
				.map(l -> {
					
					Set<PointDTO> pointsDto = l.getPoints()
							.stream()
							.map(p -> new PointDTO(p.getX(), p.getY()))
							.collect(Collectors.toSet());
					
					LineDTO lineDTO = new LineDTO();
					lineDTO.setPoints(pointsDto);
					return lineDTO;
					
				})
				.collect(Collectors.toList());
	}

	public void cleanSpace() { 
		LOGGER.info("cleanSpace()");
		
		plane.clear();
	}
	
	private Line getLineThatContainsPoints(Point p1, Point p2) {
		LOGGER.info("getLineThatContainsPoints {} {}" , p1, p2);
        return this.plane.getLines()
                .stream()
                .filter(Objects::nonNull)
                .filter(line -> line.getPoints().containsAll(Arrays.asList(p1,p2)))
                .findFirst()
                .orElse(new Line());
    }
	
	public boolean isPointCollinearToLine(final Point p, final Line line) {
		LOGGER.info("isPointCollinearToLine point={} line={}" , p, line);
        final List<Point> linePoints = new ArrayList<>(line.getPoints());
        final Point p1 = linePoints.get(0);
        final Point p2 = linePoints.get(1);

        double result = (p2.getY() - p1.getY()) * p.getX();
        result += (p1.getX() - p2.getX()) * p.getY();
        result += (p2.getX() * p1.getY() - p1.getX() * p2.getY());

        return result == 0d;
    }
}
