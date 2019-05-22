package com.aborraccino.patternrecognition.featurepoints.api.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.aborraccino.patternrecognition.featurepoints.exception.InvalidInputException;
import com.aborraccino.patternrecognition.featurepoints.model.Plane;
import com.aborraccino.patternrecognition.featurepoints.model.Point;

@RunWith(MockitoJUnitRunner.class)
public class FeaturePointsServiceImplTest {
	
	@InjectMocks
	private FeaturePointsServiceImpl featureService;
	@Spy
	private Plane plane = new Plane();
	
	@Before
	public void init() {
		plane.addPoint(new Point(-1, 1));
		plane.addPoint(new Point(1, 1));
		plane.addPoint(new Point(2, 1));
		plane.addPoint(new Point(3, 1));
		plane.addPoint(new Point(4, 1));
		plane.addPoint(new Point(0, 0));
	}
	
	@Test(expected = InvalidInputException.class)
    public void getLine_whenInputIsInvalid_thenInvalidInputException() {

        // when
		featureService.getLines(1);
    }
	
	@Test
    public void getLine_whenInputIsValid_thenReturnLines() {
        // given
        //OfferDTO offerDTO = givenDummyOfferDTO();
        //given(offerRepository.findById(any())).willReturn(Optional.of(new Offer()));

        // when
		featureService.getLines(5);
    }

}
