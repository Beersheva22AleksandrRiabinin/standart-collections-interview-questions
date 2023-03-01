package telran.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.structure.WordsImpl;

class WordsTest {

	WordsImpl wrds = new WordsImpl();

	@Test
	void test() {
		assertTrue(wrds.addWord("Ward1"));
		assertFalse(wrds.addWord("ward1"));
		assertFalse(wrds.addWord("Ward1"));
		assertFalse(wrds.addWord("waRd1"));

		wrds.addWord("word2");
		wrds.addWord("word3");
		wrds.addWord("ww");
		wrds.addWord("www");

		String[] exp = { "word2", "word3" };
		assertArrayEquals(exp, wrds.getWordsByPrefix("Wo").toArray());
		
		String[] exp2 = { "Ward1", "word2", "word3", "ww", "www"};
		assertArrayEquals(exp2, wrds.getWordsByPrefix("w").toArray());
		
		String[] exp3 = {"ww", "www"};
		assertArrayEquals(exp3, wrds.getWordsByPrefix("ww").toArray());
		
		wrds.addWord("Step");
		wrds.addWord("stone");
		wrds.addWord("stop");
		String[] exp4 = {"Step", "stone", "stop"};
		assertArrayEquals(exp4, wrds.getWordsByPrefix("sT").toArray());		
		
		assertNull(wrds.getWordsByPrefix("qq"));
		assertNull(wrds.getWordsByPrefix("wq"));
	}

}
