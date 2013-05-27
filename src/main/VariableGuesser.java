package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VariableGuesser {

	private static VariableGuesser vg = new VariableGuesser();

	public VariableGuesser() {
	}

	public static VariableGuesser getInstance() {
		return vg;
	}

	public int guess(TermSet termSet) {

		List<Character> varList;
		List<List<Integer>> caseList;
		TermSet aSet;
		int count = 0;

		varList = buildVariableList(termSet);
		caseList = buildPossiableCaseList(varList.size());

		for (int i = 0; i < caseList.size(); i++) {
			aSet = replaceVariableToNumber(termSet, varList, caseList.get(i));
			if (isMatchExpression(aSet.getP(), aSet.getQ(), aSet.getR())) {
				count++;
			}
		}
		return count;
	}

	public int guess(String p, String q, String r) {

		TermSet aSet = new TermSet(p, q, r);
		int count = this.guess(aSet);

		return count;
	}

	private TermSet replaceVariableToNumber(TermSet termSet, List<Character> varList, List<Integer> CaseSetList) {

		TermSet aNumberTermSet = termSet.clone();
		for (int n = 0; n < varList.size(); n++) {
			aNumberTermSet = aNumberTermSet.replaceVariableToNumber(varList.get(n), CaseSetList.get(n));
		}
		return aNumberTermSet;
	}

	// 구현 상의 한계점. p+q=r 의 조건이 어떻게 변경될 지 몰라 남겨둠.
	private boolean isMatchExpression(String p, String q, String r) {

		if (p.charAt(0) == '0' || q.charAt(0) == '0' || r.charAt(0) == '0') {
			return false;
		}
		
		if (Integer.parseInt(p) + Integer.parseInt(q) == Integer.parseInt(r)) {
			System.out.println(p + "+" + q + "=" + r);
			return true;
		} else
			return false;
	}

	private List<Character> buildVariableList(TermSet termSet) {

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

	private List<List<Integer>> buildPossiableCaseList(int numberOfVariable) {

		List<List<Integer>> CaseList = new ArrayList<List<Integer>>();
		ArrayList<Integer> caseSet;
		int numberOfLoop = (int) Math.pow(10, numberOfVariable);
		String condition = "%" + "0" + numberOfVariable + "d";

		for (int i = 0; i < numberOfLoop; i++) {
			String str = String.format(condition, i);
			
			caseSet = generateCase(numberOfVariable, str);
			if (caseSet.size() == numberOfVariable)
				CaseList.add(caseSet);
			else
				caseSet = null;
		}
		return CaseList;
	}

	private ArrayList<Integer> generateCase(int numberOfVariable, String str) {
		ArrayList<Integer> set = new ArrayList<Integer>();
		
		for (int i = 0; i < numberOfVariable; i++) {
			if (set.contains((int) str.charAt(i) - 48))
				break;
			else
				set.add((int) str.charAt(i) - 48);
		}
		return set;
	}

	private boolean isVariable(char c) {
		if (c < 48 || c > 58)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {

		VariableGuesser vg = new VariableGuesser();

		String p = "XX";
		String q = "YZ";
		String r = "100";

		System.out.println(vg.guess(p, q, r));
	}

	class TermSet implements Iterable<String> {

		List<String> termList;
		Iterator<String> iter;

		// termList의 deep copy.
		public TermSet(List<String> termList) {
			this.termList = new ArrayList<String>();
			this.iter = termList.iterator();

			for (String aTerm : termList) {
				this.termList.add(aTerm);
			}
		}

		public TermSet(String p, String q, String r) {
			termList = new ArrayList<String>();

			termList.add(p);
			termList.add(q);
			termList.add(r);
		}

		public String getP() {
			return termList.get(0);
		}

		public String getQ() {
			return termList.get(1);
		}

		public String getR() {
			return termList.get(2);
		}

		public TermSet replaceVariableToNumber(char variable, int value) {
			List<String> newList = new ArrayList<String>();

			for (String aTerm : termList) {
				newList.add(aTerm.replace(variable, (char) (value + 48)));
			}
			return new TermSet(newList);
		}

		// deep copy. clone의 의미에 맞는지.
		public TermSet clone() {
			return new TermSet(this.termList);
		}

		@Override
		public Iterator<String> iterator() {
			return this.iter;
		}
	}
}
