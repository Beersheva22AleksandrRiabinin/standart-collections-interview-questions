package telran.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersClass implements MultiCounters {

	private TreeMap<Integer, HashSet<Object>> treemap = new TreeMap<>();
	private HashMap<Object, Integer> hashmap = new HashMap<>();

	@Override
	public Integer addItem(Object item) { // O[LogN]
		int res = 1;
		if (!hashmap.containsKey(item)) {
			hashmap.put(item, 1);
			if (treemap.isEmpty()) {
				HashSet<Object> set = new HashSet<>();
				set.add(item);
				treemap.put(1, set);
			} else {
				HashSet<Object> set;
				if (treemap.get(1) == null) {
					set = new HashSet<>();
				} else {
					set = treemap.get(1);
				}
				set.add(item);
				treemap.put(1, set);
			}
		} else {
			int incrementValue = hashmap.get(item) + 1;
			hashmap.put(item, incrementValue);
			res = incrementValue;
			if (treemap.get(incrementValue) == null) {
				HashSet<Object> set = new HashSet<>();
				treemap.put(incrementValue, set);
			}
			HashSet<Object> set = treemap.get(incrementValue);
			set.add(item);
			treemap.put(incrementValue, set);
			treemap.get(incrementValue - 1).remove(item);
			if (treemap.get(incrementValue - 1).isEmpty()) {
				treemap.remove(incrementValue - 1);
			}
		}
		return res;
	}

	@Override
	public Integer getValue(Object item) { // O[1]

		return hashmap.get(item);
	}

	@Override
	public boolean remove(Object item) { // O[LogN]
		if (hashmap.containsKey(item)) {
			treemap.remove(hashmap.get(item));
		}
		return hashmap.remove(item) != null;
	}

	@Override
	public Set<Object> getMaxItems() { // O[LogN]

		return treemap.lastEntry().getValue();
	}

}
