package telran.structure;

import java.util.*;

public class WordsImpl implements Words {
	
	HashSet<String> contains = new HashSet<>();
	HashMap<String, Set<String>> words = new HashMap<>();

	@Override
	public boolean addWord(String word) {
		boolean res;
		if (contains.contains(word.toLowerCase())) {
			res = false;
		} else {
			res = true;
			contains.add(word.toLowerCase());
			String firstSymbol = word.substring(0, 1).toLowerCase();
			Set<String> set;
			if (words.containsKey(firstSymbol)) {
				set = words.get(firstSymbol);
			} else {
				set = new HashSet<>();
			}
			set.add(word);
			words.put(firstSymbol, set);
		}
		
		return res;
	}

	@Override
	public List<String> getWordsByPrefix(String pref) {
		String prefix = pref.toLowerCase();
		String firstSymbol = prefix.substring(0, 1);
		if (!words.containsKey(firstSymbol)) {
			return null;
		}
		Set<String> set = words.get(firstSymbol);
		String[] ar = new String[set.size()];
		ar = set.toArray(ar);
		Arrays.sort(ar);
		Comparator<String> comp = (o1, o2) -> {
			return o1.substring(0, prefix.length()).compareTo(o2);
		};
		int anyMatchIndex = Arrays.binarySearch(ar, prefix, comp);
		if (anyMatchIndex < 0) {
			return null;
		}		
		int firstIndex = anyMatchIndex;
		int lastIndex = anyMatchIndex;
		while (firstIndex > - 1 && ar[firstIndex].toLowerCase().startsWith(prefix)) {
			firstIndex--;
		}
		while (lastIndex < ar.length && ar[lastIndex].toLowerCase().startsWith(prefix)) {
			lastIndex++;
		}
		List<String> res = new ArrayList<>();
		for (int i = firstIndex + 1; i < lastIndex; i++) {
			res.add(ar[i]);
		}
		return res;
	}

}
