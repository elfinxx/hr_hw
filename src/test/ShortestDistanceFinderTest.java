package test;

import static org.junit.Assert.*;

import main.ShortestDistanceFinder;
import main.PositionSet;

import org.junit.Test;


public class ShortestDistanceFinderTest {
	private ShortestDistanceFinder sdf;
	
	public ShortestDistanceFinderTest() {
		this.sdf = ShortestDistanceFinder.getInstance();
	}

	@Test
	public void testFind() {
		
		int[] posA = {1, 18, 30, 44, 58};
		int[] posB = {23, 50, 60};
		int[] posC = {35, 42, 54, 63};
		
		PositionSet solSet = sdf.Find(posA, posB, posC);		
		assertEquals("Example input data", solSet.toString(), new PositionSet(58, 63).toString());
		
		int[] posA2 = {1, 18, 30, 44, 58};
		int[] posB2 = {2, 50, 60};
		int[] posC2 = {3, 42, 54, 63};
		
		solSet = sdf.Find(posA2, posB2, posC2);		
		assertEquals("1, 2, 3", solSet.toString(), new PositionSet(1, 3).toString());
	}
	
	

}
