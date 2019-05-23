package com.aborraccino.patternrecognition.featurepoints.api.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.aborraccino.patternrecognition.featurepoints.dto.LineDTO;
import com.aborraccino.patternrecognition.featurepoints.dto.PointDTO;
import com.aborraccino.patternrecognition.featurepoints.exception.InvalidInputException;
import com.aborraccino.patternrecognition.featurepoints.model.Plane;
import com.aborraccino.patternrecognition.featurepoints.model.Point;
import com.aborraccino.patternrecognition.featurepoints.service.impl.FeaturePointsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeaturePointsServiceImplTest {
	
	@InjectMocks
	private FeaturePointsServiceImpl featurePointsService;
	@Spy
	private Plane plane = new Plane();
	
	@Before
	public void init() {
		plane.addPoint(new Point(-1, 1));
		plane.addPoint(new Point(1, 1));
		plane.addPoint(new Point(2, 2));
		plane.addPoint(new Point(3, 3));
		plane.addPoint(new Point(4, 4));
	}
	
	@Test(expected = InvalidInputException.class)
    public void getLine_whenInputIsInvalid_thenInvalidInputException() {
		// given
		var n = 1;
		
        // when
		featurePointsService.getLines(n);
    }
	
	@Test
    public void getLine_whenInputIsValid_thenReturnLines() {
        // given
		var n = 3;
		
        // when
		List<LineDTO> result = featurePointsService.getLines(n);
		
		// then
		assertNotNull(result);
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getPoints().size(), is(4));
    }
	
	@Test
    public void addPoint_whenPointIsValid_thenPointAdded() {
        // given
		var x = 10;
		var y = 1;
		
        // when
		PointDTO point = new PointDTO(x, y);
		featurePointsService.addPoint(point);
		Point dummyPoint = new Point(x, y);
		
		// then
		assertThat(plane.getPoints().contains(dummyPoint), is(true));
    }
	
	@Test
    public void getSpace_whenPointIsValid_thenPointAdded() {
        // when
		Set<PointDTO> spaceResult = featurePointsService.getSpace();
		
		// then
		assertNotNull(spaceResult);
		assertThat(spaceResult.size(), is(5));
    }
	
	@Test
    public void cleanSpace_whenTheSpaceIsCleaned_thenReturnNoPoints() {
        // when
		featurePointsService.cleanSpace();
		
		// then
		assertThat(plane.getPoints().size(), is(0));
		assertThat(plane.getLines().size(), is(0));
    }
}
