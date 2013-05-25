package test;

import static org.junit.Assert.*;

import main.ShortDistanceFinder;

import org.junit.Test;

public class ShortDistanceFinderTest {
	private ShortDistanceFinder sdf;
	
	public ShortDistanceFinderTest() {
		this.sdf = ShortDistanceFinder.getInstance();
	}

	@Test
	public void testFind() {
		int[] posA = {1, 18, 30, 44, 58};
		int[] posB = {23, 50, 60};
		int[] posC = {35, 42, 54, 63};
		
		assertArrayEquals(new int[] {58, 63, 5}, sdf.Find(posA, posB, posC));		
	}
	
	

}
