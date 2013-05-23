package main;

public class ShortDistanceFinder {

	private int maxValue, minValue;
	private int dist, minDist;

	public void Find(int[] A, int[] B, int[] C) {
		
		int idxA, idxB, idxC;		
		
		idxA=idxB=idxC=0;
		minDist = getMaxValue(A[A.length-1], B[B.length-1], C[C.length-1]);
		
		
		while ((idxA != A.length) && (idxB != B.length) && (idxC != C.length)) {
			minValue = getMinValue(A[idxA], B[idxB], C[idxC]);
			maxValue = getMaxValue(A[idxA], B[idxB], C[idxC]);			
			dist = maxValue - minValue;
			
			if (minDist > dist)
				setSolutionWord(maxValue, minValue);
			
			if (minValue == A[idxA]) {
				idxA++;				
			} else if(minValue == B[idxB]) {
				idxB++;
			} else {
				idxC++;
			}
		}
		
		System.out.println(minValue + ", " + maxValue + "에 있고 거리는" + minDist + "이다.");
	}
	
	private int getMinValue(int a, int b, int c) {

		if (a < b) {
			if (a < c) 
				return a;
			else 
				return c;
		} 
		else {
			if (b < c)
				return b;
			else
				return c;
		}
	}

	private int getMaxValue(int a, int b, int c) {
		
		if (a > b) {
			if (a > c)
				return a;
			else 
				return c;
		}
		else {
			if (b > c)
				return b;
			else 
				return c;
		}
	}
	
	private void setSolutionWord(int maxValue, int minValue) {
		this.maxValue = maxValue;
		this.minValue = minValue;		
		minDist = dist;
	}
	
	public static void main(String[] args) {

		ShortDistanceFinder sdf = new ShortDistanceFinder();
		
		// 각 집합은 오름차순 정렬 되어있다고 가정
		int[] posA = {1, 2, 3, 4, 5};
		int[] posB = {6, 7, 8};
		int[] posC = {9, 10, 11, 12};
		
		sdf.Find(posA, posB, posC);
	}

}

