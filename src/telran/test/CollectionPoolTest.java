package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.structure.Connection;
import telran.structure.ConnectionPoolImpl;

class CollectionPoolTest {
	
	ConnectionPoolImpl pool = new ConnectionPoolImpl();

	@Test
	void poolTest() {
		Connection con1 = new Connection(1, "ip1", 11);
		Connection con2 = new Connection(2, "ip2", 22);
		Connection con3 = new Connection(3, "ip3", 33);
		
		assertTrue(pool.addConnection(con1));
		assertTrue(pool.addConnection(con2));
		assertTrue(pool.addConnection(con3));  // 1 - 2 - 3
		assertFalse(pool.addConnection(con1));
		
		assertEquals(pool.getConnection(2), con2); // 1 - 3 - 2
		
		Connection con4 = new Connection(4, "ip4", 44);
		assertTrue(pool.addConnection(con4)); // 3 - 2 - 4
		assertFalse(pool.addConnection(con4));
		assertFalse(pool.addConnection(con3));
		assertFalse(pool.addConnection(con2));
		
		assertTrue(pool.addConnection(con1)); // 2 - 4 - 1
		assertEquals(pool.getConnection(2), con2); // 4 - 1 - 2
		assertFalse(pool.addConnection(con4));
		assertFalse(pool.addConnection(con1));
		assertFalse(pool.addConnection(con2));
		
		Iterator<Object> it = pool.iterator();
		assertEquals(it.next(), con4);
		assertEquals(it.next(), con1);
		assertEquals(it.next(), con2);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}

}
