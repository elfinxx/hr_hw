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
		return String.format("%d�� %d ���̿� �ְ� �Ÿ��� %d �̴�.", minPosition,
				maxPosition, maxPosition - minPosition);
	}
}
