package main;

import java.util.ArrayList;
import java.util.List;

public class VariableGuesser {

	private static VariableGuesser vg = new VariableGuesser();

	public VariableGuesser() {
	}

	public static VariableGuesser getInstance() {
		return vg;
	}

	public List<TermSet> guess(String p, String q, String r) {

		TermSet aSet = new TermSet(p, q, r);
		return this.guess(aSet);
	}

	public List<TermSet> guess(TermSet termSet) {

		List<Character> varList;
		List<List<Integer>> caseList;
		List<TermSet> solutionTermSet = new ArrayList<TermSet>();
		TermSet aSet;

		varList = buildVariableListBy(termSet);
		caseList = buildPossiableCaseListBy(varList.size());

		for (int i = 0; i < caseList.size(); i++) {
			aSet = replaceVariableToNumber(termSet, varList, caseList.get(i));
			if (isMatchExpression(aSet)) {
				solutionTermSet.add(aSet);
			}
		}
		return solutionTermSet;
	}

	private List<Character> buildVariableListBy(TermSet termSet) {

		List<Character> varList = new ArrayList<Character>();
		String term = termSet.getP() + termSet.getQ() + termSet.getR();

		for (int i = 0; i < term.length(); i++) {
			char curC = term.charAt(i);
			if (isVariable(curC)) {
				if (!varList.contains(curC)) {
					varList.add(curC);
				}
			}
		}
		return varList;
	}

	private List<List<Integer>> buildPossiableCaseListBy(int numberOfVariable) {

		List<List<Integer>> CaseList = new ArrayList<List<Integer>>();
		ArrayList<Integer> caseSet;
		int numberOfLoop = (int) Math.pow(10, numberOfVariable);

		for (int i = 0; i < numberOfLoop; i++) {
			caseSet = generateCase(numberOfVariable, i);
			if (caseSet.size() == numberOfVariable)
				CaseList.add(caseSet);
		}
		return CaseList;
	}

	private ArrayList<Integer> generateCase(int numberOfVariable,
			int candidateNumber) {
		ArrayList<Integer> set = new ArrayList<Integer>();
		String str = String.format("%" + "0" + numberOfVariable + "d",
				candidateNumber);

		for (int i = 0; i < numberOfVariable; i++) {
			if (set.contains(CharToInt(str.charAt(i))))
				break;
			else
				set.add(CharToInt(str.charAt(i)));
		}
		return set;
	}

	private TermSet replaceVariableToNumber(TermSet termSet,
			List<Character> varList, List<Integer> CaseSetList) {

		TermSet aNumberTermSet = termSet.clone();
		for (int n = 0; n < varList.size(); n++) {
			aNumberTermSet = aNumberTermSet.replaceVariableToNumber(
					varList.get(n), CaseSetList.get(n));
		}
		return aNumberTermSet;
	}

	private boolean isMatchExpression(TermSet aSet) {

		for (String term : aSet) {
			if (term.charAt(0) == '0')
				return false;
		}
		return isCorrectAnswer(aSet);
	}

	private boolean isCorrectAnswer(TermSet aSet) {

		// 조건에 의존적인 부분.
		if (Integer.parseInt(aSet.getP()) + Integer.parseInt(aSet.getQ()) == Integer
				.parseInt(aSet.getR()))
			return true;
		else
			return false;
	}

	private boolean isVariable(char c) {
		if (c < 48 || c > 58)
			return true;
		else
			return false;
	}

	private int CharToInt(char ch) {
		return (int) ch - 48;
	}
}
