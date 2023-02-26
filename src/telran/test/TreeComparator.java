package telran.test;

import java.util.Comparator;

public class TreeComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int i1 = o1.toString().chars().map(n -> n - '0').sum();
		int i2 = o2.toString().chars().map(n -> n - '0').sum();
		return i1 - i2;
	}

}
