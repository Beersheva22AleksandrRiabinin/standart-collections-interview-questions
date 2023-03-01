package telran.structure;

import java.util.*;

public class ConnectionPoolImpl implements ConnectionsPool {

	private LinkedList<Object> linkedlist = new LinkedList<>();
	private HashMap<Integer, Object> hashmap = new HashMap<>();
	
	private int maxsize = 3;

	@Override
	public boolean addConnection(Connection connection) {
		boolean res = false;
		int id = connection.getId();
		if (!hashmap.containsKey(id)) {
			linkedlist.addLast(connection);
			hashmap.put(id, connection);
			res = true;
		}
		if (linkedlist.size() > maxsize) {
			removeOldest();
		}
		return res;
	}

	private void removeOldest() {
		Connection con = (Connection) linkedlist.getFirst();
		int idFirst = con.getId();
		linkedlist.removeFirst();
		hashmap.remove(idFirst);
	}

	@Override
	public Connection getConnection(int id) {
		Connection res = (Connection) hashmap.get(id);
		linkedlist.remove(res);
		linkedlist.addLast(res);
		return res;
	}

	public Iterator<Object> iterator() {
		
		return linkedlist.iterator();
	}

}
