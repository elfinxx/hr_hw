package main;

import java.util.ArrayList;

public class MagicNumberFinder {

	char var[] = {'c', };
	ArrayList<Character> varList;
	ArrayList<Integer> intList;

	int varIdx;
	int loopCount;

	public MagicNumberFinder() {

		varList = new ArrayList<Character>();
		intList = new ArrayList<Integer>();
		loopCount = 1;
	}

	public void Find(String p, String q, String r) {

		ArrayList<Character> tempList = new ArrayList<>();
		boolean existVar = false;		
		this.varIdx = 0;
		String p_tmp;
		String q_tmp;
		String r_tmp;
		int count = 0;
		

		for(int i = 0; i<p.length(); i++) {

			char curC = p.charAt(i);

			if ( !(this.isDigit(curC)) ) {	

				if (varList.contains(curC)) {
					existVar = true;
					break;
				}

				if(!existVar) {
					varList.add(curC);				
					existVar = false;
				}
			}
		}

		existVar = false;

		for(int i = 0; i<q.length(); i++) {

			char curC = q.charAt(i);

			if ( !(this.isDigit(curC)) ) {	

				if (varList.contains(curC)) {
					existVar = true;
					break;
				}
				if(!existVar) {
					varList.add(curC);				
					existVar = false;
				}
			}
		}

		existVar = false;

		for(int i = 0; i<r.length(); i++) {

			char curC = r.charAt(i);

			if ( !(this.isDigit(curC)) ) {	

				if (varList.contains(curC)) {
					existVar = true;
					break;
				}

				if(!existVar) {
					varList.add(curC);				
					existVar = false;
				}
			}
		}

		for (int i = 0; i<varList.size(); i++)
			loopCount *= 10;

		for (int i = varList.size() - 1; i >=0; i--) {
			tempList.add(varList.get(i));
		}

		varList = tempList;
		for (int i=0; i<loopCount; i++) {

			int tempCount = i;
			intList.clear();

			for (int j=0; j<varList.size(); j++) {

				if (intList.contains(tempCount % 10))
					break;

				intList.add(tempCount % 10);
				tempCount = tempCount / 10;				

			}

			if(intList.size() < varList.size())
				continue;			

			
			p_tmp = p.toString();
			q_tmp = q.toString();
			r_tmp = r.toString();

			for (int n = 0; n < varList.size(); n++) {			
				p_tmp = p_tmp.replace(varList.get(n), intList.get(n).toString().charAt(0));
				q_tmp = q_tmp.replace(varList.get(n), intList.get(n).toString().charAt(0));
				r_tmp = r_tmp.replace(varList.get(n), intList.get(n).toString().charAt(0));

			}
			
			if (p_tmp.charAt(0) == '0' || q_tmp.charAt(0) == '0' || r_tmp.charAt(0) == '0')
				continue;

			if (Integer.parseInt(p_tmp) + Integer.parseInt(q_tmp) == Integer.parseInt(r_tmp)){
				System.out.println(p_tmp +"+"+ q_tmp  + "=" +r_tmp);
				count++;

			}

		}

		System.out.println(count+"°¡Áö");
	}

	public static void main(String[] args) {

		MagicNumberFinder mnf = new MagicNumberFinder();

		String p = "XX";
		String q =  "YZ";
		String r = "100";

		mnf.Find(p, q, r);
	}


	private boolean isDigit(char c) {
		if( c < 48 || c > 58)
			return false;
		else
			return true;
	}
}

