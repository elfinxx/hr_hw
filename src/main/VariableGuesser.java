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

	public int guess(TermSet termSet) {
		
		List<Character> varList;
		List<List<Integer>> caseSetList;
		
		varList = buildVariableList(termSet);
		caseSetList = buildPossiableCaseList(varList.size());
		
		for (int i = 0; i < caseSetList.size(); i++) {
			replaceVariableToNumber(termSet, varList, caseSetList.get(i));
			
		}
		return 0;
	}
	
	public int guess(String p, String q, String r) {

		List<Character> varList;
		List<List<Integer>> totalCaseSetList;

		String p_tmp;
		String q_tmp;
		String r_tmp;

		int count = 0;

		varList = buildVariableList(p+q+r);
		totalCaseSetList = buildPossiableCaseList(varList.size());

		for (int i = 0; i < totalCaseSetList.size(); i++) {
			p_tmp = p.toString();
			q_tmp = q.toString();
			r_tmp = r.toString();

			for (int n = 0; n < varList.size(); n++) {
				p_tmp = p_tmp.replace(varList.get(n), totalCaseSetList.get(i)
						.get(n).toString().charAt(0));
				q_tmp = q_tmp.replace(varList.get(n), totalCaseSetList.get(i)
						.get(n).toString().charAt(0));
				r_tmp = r_tmp.replace(varList.get(n), totalCaseSetList.get(i)
						.get(n).toString().charAt(0));
			}

			if (isMatchExpression(p_tmp, q_tmp, r_tmp)) {
				count++;
			}
		}

		System.out.println(count + "°¡Áö");
		return count;

	}

	private TermSet replaceVariableToNumber(TermSet termSet, List<Character> varList, List<Integer> CaseSetList) {
		
		TermSet aNumberTermSet = termSet.;
		
		for (int n = 0; n < varList.size(); n++) {
			
			termSet.g
			p_tmp = p_tmp.replace(varList.get(n), CaseSetList.get(n).toString());
			q_tmp = q_tmp.replace(varList.get(n), CaseSetList.get(n).toString());
			r_tmp = r_tmp.replace(varList.get(n), CaseSetList.get(n).toString());
		}
			
		return aNumberTermSet;
	}
	private boolean isMatchExpression(String p, String q, String r) {
		
		if (p.charAt(0) == '0' || q.charAt(0) == '0' || r.charAt(0) == '0') {
			return false;
		}
		if (Integer.parseInt(p) + Integer.parseInt(q) == Integer.parseInt(r)) {
			System.out.println(p + "+" + q + "=" + r);
			return true;
		}
		else
			return false;
	}

	private List<Character> buildVariableList(String term) {

		List<Character> varList = new ArrayList<Character>();
		
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

		List<List<Integer>> totalSet = new ArrayList<List<Integer>>();
		int numberOfLoop = (int)Math.pow(10, numberOfVariable);
		String condition = String.format("%d", numberOfVariable);
		condition = "%" + "0" + condition + "d";

		for (int i = 0; i < numberOfLoop; i++) {
			String str = String.format(condition, i);
			ArrayList<Integer> set = new ArrayList<Integer>();

			for (int j = 0; j < numberOfVariable; j++) {
				if (set.contains((int) str.charAt(j) - 48))
					break;
				else
					set.add((int) str.charAt(j) - 48);				
			}

			if (set.size() == numberOfVariable)
				totalSet.add(set);
		}

		return totalSet;
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

		vg.guess(p, q, r);
		// System.out.println(vg.getNumberOfCase(3));

	}
	
	class TermSet {

		List<String> termList;
		
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
		
		public void replaceVariableToNumber() {
			
		}
		
	}

}
