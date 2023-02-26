package telran.util;

import java.util.*;

public class StackInt {

	private LinkedList<Integer> list = new LinkedList<>();
	private LinkedList<Integer> max = new LinkedList<>();

	public void push(int num) {
		// adds num into top of stack
		list.add(num);
		if (max.isEmpty() || num >= max.getLast()) {
			max.add(num);
		}
	}

	public int pop() {
		// returns a number from top of stack or throws NoSuchElementException if the stack is empty
			int res = list.removeLast();
			if (res == max.getLast()) {
				max.removeLast();
			}
			return res;
	}

	public boolean isEmpty() {
		// returns true if stack is empty, otherwise false
		return list.isEmpty();
	}

	public int getMax() {
		// returns maximal value of the stack or throws NoSuchElementException if the stack is empty
			return max.getLast();
	}

}
