package test;

import static org.junit.Assert.*;

import main.VariableGuesser;

import org.junit.Test;

public class VariableGuesserTest {

	VariableGuesser vg = VariableGuesser.getInstance();
	
	@Test
	public void testGuess() {
		String p = "XX";
		String q = "YZ";
		String r = "100";
				
		assertEquals("���� ���̽�", 7, vg.guess(p, q, r));
		
		p = "XYZ";
		q = "XY";
		r = "6PP";		
		assertEquals("���� ���̽�2", 3, vg.guess(p, q, r));
	}
}
