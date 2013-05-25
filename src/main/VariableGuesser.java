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

	public int guess(String p, String q, String r) {
		ArrayList<Character> varList = new ArrayList<Character>();
		List<List<Integer>> totalCaseSet = new ArrayList<List<Integer>>();
		
		String p_tmp;
		String q_tmp;
		String r_tmp;

		int count = 0;

		addVariable(varList, p);
		addVariable(varList, q);
		addVariable(varList, r);

		totalCaseSet = getNumberOfCase(varList.size());		
		
		for (int i = 0; i < totalCaseSet.size(); i++) {
			p_tmp = p.toString();
			q_tmp = q.toString();
			r_tmp = r.toString();
			
			for (int n = 0; n < varList.size(); n++) {
				p_tmp = p_tmp.replace(varList.get(n), totalCaseSet.get(i).get(n).toString()
						.charAt(0));
				q_tmp = q_tmp.replace(varList.get(n), totalCaseSet.get(i).get(n).toString()
						.charAt(0));
				r_tmp = r_tmp.replace(varList.get(n), totalCaseSet.get(i).get(n).toString()
						.charAt(0));
			}

			if (isMatchExpression(p_tmp, q_tmp, r_tmp)) {
				count++;
			}
		}

		System.out.println(count + "°¡Áö");
		return count;

	}

	private boolean isMatchExpression(String p, String q, String r) {
		if (p.charAt(0) == '0' || q.charAt(0) == '0' || r.charAt(0) == '0')
			return false;

		if (Integer.parseInt(p) + Integer.parseInt(q) == Integer.parseInt(r)) {
			System.out.println(p + "+" + q + "=" + r);
			return true;
		}

		else
			return false;

	}

	private void addVariable(ArrayList<Character> varList, String term) {

		for (int i = 0; i < term.length(); i++) {
			char curC = term.charAt(i);

			if (isVariable(curC)) {

				if (!varList.contains(curC)) {
					varList.add(curC);
				}
			}
		}
	}

	private List<List<Integer>> getNumberOfCase(int numberOfVariable) {
		
		List<List<Integer>> totalSet = new ArrayList<List<Integer>>();
		String condition = String.format("%d", numberOfVariable);
		condition = "%" + "0" + condition + "d";
		
		for (int i = 0; i < Math.pow(10, numberOfVariable) ; i++) {
			String str = String.format(condition, i);
			ArrayList<Integer> set = new ArrayList<Integer>();
			
			for (int j = 0; j < numberOfVariable; j++) {
				if (set.contains((int)str.charAt(j)-48))
					continue;
				else
					set.add((int)str.charAt(j)-48);;
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

}
