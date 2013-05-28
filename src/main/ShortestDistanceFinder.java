package main;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceFinder {

	private static ShortestDistanceFinder sdf = new ShortestDistanceFinder();

	private ShortestDistanceFinder() {
	}

	public static ShortestDistanceFinder getInstance() {
		return sdf;
	}

	public PositionSet Find(List<WordPositionSet> positionSetList) {

		PositionSet positionSet;
		PositionSet solutionSet;

		solutionSet = new PositionSet(0, Integer.MAX_VALUE);

		while (!isArrivalEverySetEnd(positionSetList)) {

			positionSet = calculateMinMaxPositionOf(positionSetList);
			solutionSet = UpdateSolutionSetUsing(solutionSet, positionSet);
			IncreaseMinPositionSetIndex(positionSet.getMinPosition(),
					positionSetList);
		}
		return solutionSet;
	}

	public PositionSet Find(int[] A, int[] B, int[] C) {

		List<WordPositionSet> positionSetList;
		positionSetList = buildUpSetList(A, B, C);

		return this.Find(positionSetList);
	}

	private PositionSet calculateMinMaxPositionOf(
			List<WordPositionSet> setList) {

		int minValue, maxValue;

		minValue = getMinPositionOfSet(setList);
		maxValue = getMaxPositionOfSet(setList);

		return new PositionSet(minValue, maxValue);
	}

	private PositionSet UpdateSolutionSetUsing(PositionSet previousPosSet,
			PositionSet currentPosSet) {

		if (previousPosSet.getDistance() > currentPosSet.getDistance())
			return currentPosSet;
		else
			return previousPosSet;
	}

	private void IncreaseMinPositionSetIndex(int minPosition,
			List<WordPositionSet> setList) {

		for (WordPositionSet aSet : setList) {
			if (minPosition == aSet.getCurruntPostion()) {
				aSet.increaseIdx();
				return;
			}
		}
	}

	private boolean isArrivalEverySetEnd(List<WordPositionSet> setList) {

		for (WordPositionSet aSet : setList) {
			if (aSet.idx == aSet.position.length)
				return true;
		}
		return false;
	}

	private int getMinPositionOfSet(List<WordPositionSet> setList) {

		int minPosition = Integer.MAX_VALUE;

		for (WordPositionSet aSet : setList) {
			if (minPosition > aSet.getCurruntPostion())
				minPosition = aSet.getCurruntPostion();
		}

		return minPosition;
	}

	private int getMaxPositionOfSet(List<WordPositionSet> setList) {

		int maxPosition = Integer.MIN_VALUE;

		for (WordPositionSet aSet : setList) {
			if (maxPosition < aSet.getCurruntPostion())
				maxPosition = aSet.getCurruntPostion();
		}

		return maxPosition;
	}

	private List<WordPositionSet> buildUpSetList(int[] A, int[] B, int[] C) {
		List<WordPositionSet> setList = new ArrayList<WordPositionSet>();

		WordPositionSet aSet = new WordPositionSet(A);
		WordPositionSet bSet = new WordPositionSet(B);
		WordPositionSet cSet = new WordPositionSet(C);
		setList.add(aSet);
		setList.add(bSet);
		setList.add(cSet);

		return setList;
	}

	class WordPositionSet {

		final int position[];
		private int idx;

		public WordPositionSet(int position[]) {
			this.position = position;
			idx = 0;
		}

		public int getCurruntPostion() {
			return position[idx];
		}

		public void increaseIdx() {
			idx++;
		}
	}
}
