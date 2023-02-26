package telran.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrayInt;
import telran.util.StackInt;

class StandartCollectionTest {
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	@Disabled
	void SubListTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,70,-20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);
		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);		
	}
	
	@Test
	@Disabled
	void displayOccurenciesCount() {
		String[] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
//		Map<Integer, List<String>> map = Arrays.stream(strings).
//							collect(Collectors.groupingBy(s -> s.length()));
//		Map<String, Long> map = Arrays.stream(strings).
//							collect(Collectors.groupingBy(s -> s, Collectors.counting()));
//		System.out.println(map);
		Arrays.stream(strings)
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));		
	}
	
	@Test
	@Disabled
	void displayDigitStatistics() {
		//Generate 1kk random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//Consider using flatMap for getting many from one 
		new Random().ints(1, Integer.MAX_VALUE).limit(1_000_000)
					.flatMap(n -> Integer.toString(n).chars()).boxed()
					.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
					.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
					.forEach(e -> System.out.printf("%c: %d\n", e.getKey(), e.getValue()));	
	}
	
	@Test
	void stackTest() {
		StackInt st = new StackInt();
		assertThrowsExactly(NoSuchElementException.class, () -> st.getMax());
		assertThrowsExactly(NoSuchElementException.class, () -> st.pop());
		assertTrue(st.isEmpty());
		st.push(3);
		st.push(4);
		st.push(1);
		st.push(2);
		st.push(5);
		assertEquals(5, st.getMax());
		assertEquals(5, st.pop());
		assertEquals(4, st.getMax());
		assertEquals(2, st.pop());
		assertEquals(4, st.getMax());
		assertEquals(1, st.pop());
		assertEquals(4, st.pop());
		assertEquals(3, st.getMax());
		assertFalse(st.isEmpty());
		assertEquals(3, st.pop());
		assertTrue(st.isEmpty());
	}
	
	@Test
	void myArrayIntTest() {
		MyArrayInt ar = new MyArrayInt(10, 2);
		assertEquals(2, ar.get(5));
		ar.setAll(3);
		assertEquals(3, ar.get(5));	
		ar.set(5, 1);
		assertEquals(1, ar.get(5));	
	}
	
	@Test
	void maxNumberWithNegativeImageTest() {
		int[] ar = {1000, 3, -2, -200, 200, -3};
		int[] ar1 = {1000, -100, 3, -4};
		assertEquals(200, maxNumberWithNegativeImageTest(ar));
		assertEquals(-1, maxNumberWithNegativeImageTest(ar1));
	}
	
	int maxNumberWithNegativeImageTest(int[] ar) {
		Set<Integer> set = new HashSet<>();
		int res = -1;
		boolean setAdd;
		for (int num : ar) {
			setAdd = true;
			setAdd = num > 0 ? set.add(num) : set.add(-num);
			if (!setAdd && num > res) {
				res = num;
			}
		}
		return res;
	}
	
	@Test
	void treeIteratingTest() {
		int array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		createAndIterateTreeInOrder(array);
	}

	private void createAndIterateTreeInOrder(int[] array) {
		// create tree, add in tree numbers from a given array
		// and iterate in the order of array defined in 122
		TreeComparator comp = new TreeComparator();
		TreeSet<Integer> tree = new TreeSet<>(comp);
		for (int n : array) {
			tree.add(n);
		}
		Iterator<Integer> it = tree.iterator();
		for(int n : array) {
			assertEquals(n, it.next());
		}
	}

}
