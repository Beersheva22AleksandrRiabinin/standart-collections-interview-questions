package telran.structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MultiCountersTest {
	
	MultiCountersClass mcc = new MultiCountersClass();
	
	@Test	
	void structureTest() {
		assertEquals(1, mcc.addItem("abc"));
		assertEquals(2, mcc.addItem("abc"));
		assertEquals(3, mcc.addItem("abc"));
		assertEquals(4, mcc.addItem("abc"));
		assertEquals(1, mcc.addItem("bcd"));		
		assertEquals(2, mcc.addItem("bcd"));

		assertEquals(4, mcc.getValue("abc"));
		assertEquals(2, mcc.getValue("bcd"));
		assertNull(mcc.getValue("cde"));

		assertTrue(mcc.remove("abc"));
		assertTrue(mcc.remove("bcd"));
		assertFalse(mcc.remove("cde"));
		assertNull(mcc.getValue("abc"));
		assertNull(mcc.getValue("bcd"));
		
		
		mcc.addItem("abc");
		mcc.addItem("abc");
		mcc.addItem("bcd");
		mcc.addItem("cde");
		mcc.addItem("cde");	
		
		Set<Object> set = new HashSet<>();
		set.add("abc");
		set.add("cde");
		assertEquals(set, mcc.getMaxItems());	
		
		mcc.addItem("fff");
		mcc.addItem("fff");
		mcc.addItem("fff");
		Set<Object> set2 = new HashSet<>();
		set2.add("fff");
		set2.add("fff");
		set2.add("fff");
		assertEquals(set2, mcc.getMaxItems());
		mcc.remove("fff");
		assertEquals(set, mcc.getMaxItems());	
		mcc.remove("abc");
		mcc.remove("cde");
		set.remove("abc");
		set.remove("cde");
		set.add("bcd");
		assertEquals(set, mcc.getMaxItems());
	}

}
