package main;

public class PositionSet {
	
	private int minPosition;
	private int maxPosition;

	public PositionSet(int minPosition, int maxPosition) {
		this.minPosition = minPosition;
		this.maxPosition = maxPosition;
	}

	public int getDistance() {
		return maxPosition - minPosition;
	}

	public int getMinPosition() {
		return minPosition;
	}

	public String toString() {
		return String.format("%d와 %d 사이에 있고 거리는 %d 이다.", minPosition,
				maxPosition, maxPosition - minPosition);
	}
}
