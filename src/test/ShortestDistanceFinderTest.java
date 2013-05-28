package test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;

import main.ShortestDistanceFinder;
import main.PositionSet;

import org.junit.Test;


public class ShortestDistanceFinderTest {
	private ShortestDistanceFinder sdf;
	
	public ShortestDistanceFinderTest() {
		this.sdf = ShortestDistanceFinder.getInstance();
	}

	@Test
	public void 
	testFindSuccessState() {
		
		int[] posA = {1, 18, 30, 44, 58};
		int[] posB = {23, 50, 60};
		int[] posC = {35, 42, 54, 63};
		
		PositionSet solSet = sdf.Find(posA, posB, posC);		
		assertEquals(solSet.getDistance(), 5);
		
		int[] posA2 = {1, 18, 30, 44, 58};
		int[] posB2 = {2, 50, 60};
		int[] posC2 = {3, 42, 54, 63};
		
		solSet = sdf.Find(posA2, posB2, posC2);		
		assertEquals(solSet.getDistance(), 2);
		
		int[] posA3 = {1};
		int[] posB3 = {2};
		int[] posC3 = {3};
		
		solSet = sdf.Find(posA3, posB3, posC3);		
		assertEquals(solSet.getDistance(), 2);
	}
}
