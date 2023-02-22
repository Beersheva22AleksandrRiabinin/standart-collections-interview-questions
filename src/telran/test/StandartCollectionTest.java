package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
	void displayDigitStatistics() {
		//Generate 1kk random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//Consider using flatMap for getting many from one 
		new Random().ints(1, Integer.MAX_VALUE).limit(1_000_000)
					.mapToObj(n -> Integer.toString(n)).flatMap(n -> n.chars().boxed())
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

}
