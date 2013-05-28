package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TermSet implements Iterable<String> {

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