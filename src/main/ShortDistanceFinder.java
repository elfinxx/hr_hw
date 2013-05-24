package main;

public class ShortDistanceFinder {

	private static ShortDistanceFinder sdf = new ShortDistanceFinder();

	private int maxValue, minValue;
	private int dist, minDist;
	
	private ShortDistanceFinder() {
	}

	public static ShortDistanceFinder getInstance() {
		return sdf;
	}

	public synchronized int[] Find(int[] A, int[] B, int[] C) {

		InputWordSet wp = new InputWordSet(A, B, C);
		this.initialize();

		while (isRemainingWord(wp)) {
			calculateShortestDistance(wp);
			moveLeastWordIndex(wp);
		}

		return new int[] { minValue, maxValue, minDist };
	}
	private void initialize() {
		maxValue = minValue = 0;
		dist = 0;
		minDist = Integer.MAX_VALUE;
	}



	private boolean isRemainingWord(InputWordSet wp) {
		return (wp.getIdxA() != wp.getA().length)
				&& (wp.getIdxB() != wp.getB().length)
				&& (wp.getIdxC() != wp.getC().length);
	}

	private void calculateShortestDistance(InputWordSet wp) {
		minValue = getMinValue(wp);
		maxValue = getMaxValue(wp);

		dist = maxValue - minValue;
		if (minDist > dist) {
			minDist = dist;
		}
	}
	
	private void moveLeastWordIndex(InputWordSet wp) {
		if (minValue == wp.getA()[wp.getIdxA()]) {
			wp.increaseIdxA();
		} else if (minValue == wp.getB()[wp.getIdxB()]) {
			wp.increaseIdxB();
		} else {
			wp.increaseIdxC();
		}
	}

	private int getMinValue(InputWordSet wp) {
		int a = wp.getCurrentAValue();
		int b = wp.getCurrentBValue();
		int c = wp.getCurrentCValue();
		if (a < b) {
			if (a < c)
				return a;
			else
				return c;
		} else {
			if (b < c)
				return b;
			else
				return c;
		}
	}

	private int getMaxValue(InputWordSet wp) {
		int a = wp.getCurrentAValue();
		int b = wp.getCurrentBValue();
		int c = wp.getCurrentCValue();

		if (a > b) {
			if (a > c)
				return a;
			else
				return c;
		} else {
			if (b > c)
				return b;
			else
				return c;
		}
	}


	private class InputWordSet {
		final int A[];
		final int B[];
		final int C[];
		private int idxA;
		private int idxB;
		private int idxC;

		public InputWordSet(int A[], int B[], int C[]) {
			this.A = A;
			this.B = B;
			this.C = C;
			idxA = idxB = idxC = 0;
		}

		public int[] getA() {
			return A;
		}

		public int[] getB() {
			return B;
		}

		public int[] getC() {
			return C;
		}

		public int getIdxA() {
			return idxA;
		}

		public int getCurrentAValue() {
			return A[idxA];
		}

		public int getCurrentBValue() {
			return B[idxB];
		}

		public int getCurrentCValue() {
			return C[idxC];
		}

		public void increaseIdxA() {
			this.idxA++;
		}

		public int getIdxB() {
			return idxB;
		}

		public void increaseIdxB() {
			this.idxB++;
		}

		public int getIdxC() {
			return idxC;
		}

		public void increaseIdxC() {
			this.idxC++;
		}

	}

	public static void main(String[] args) {

		ShortDistanceFinder sdf = new ShortDistanceFinder();

		// 각 집합은 오름차순 정렬 되어있다고 가정
		/*
		 * int[] posA = {1, 18, 30, 44, 58}; int[] posB = {23, 50, 60}; int[]
		 * posC = {35, 42, 54, 63};
		 */

		int[] posA = { 1, 18, 30, 44, 58 };
		int[] posB = { 2, 50, 60 };
		int[] posC = { 3, 42, 54, 63 };

		int[] result = sdf.Find(posA, posB, posC);

		System.out.println(result[0] + ", " + result[1] + "에 있고 거리는"
				+ result[2] + "이다.");
	}

}
