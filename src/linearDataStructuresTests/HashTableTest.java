package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import linearDataStructures.HashTable;

class HashTableTest {

	@Test
	void basicTest() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		hashTable.put("3", 3);
		assertEquals(true, hashTable.containsKey("3"));
		assertEquals(true, hashTable.containsValue(3));
	}

	@Test
	void testPutDistinct() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		Hashtable<String, Integer> javaHashTable = new Hashtable<String, Integer>();
		for (int i = 0; i < 1000; i++) {
			hashTable.put("" + i, i);
			javaHashTable.put("" + i, i);
		}
		for (int i = 0; i < 1000; i++) {
			assertEquals(true, hashTable.containsKey("" + i));
			assertEquals(true, hashTable.containsValue(i));
			assertEquals(javaHashTable.get("" + i), hashTable.get("" + i));
		}

	}

	@Test
	void testResize() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>(1);
		for (int i = 0; i < 1000; i++) {
			hashTable.put("" + i, i);
		}

		for (int i = 0; i < 1000; i++) {
			assertEquals(true, hashTable.containsKey("" + i));
			assertEquals(true, hashTable.containsValue(i));
		}

	}

	@Test
	void testKeySet() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		Hashtable<String, Integer> javaHashTable = new Hashtable<String, Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			hashTable.put("" + num, num);
			javaHashTable.put("" + num, num);
		}

		Set<String> keys = hashTable.keySet();
		Set<String> javaKeys = javaHashTable.keySet();
		Object[] javaKeyArray = javaKeys.toArray();

		for (int i = 0; i < 1000; i++) {
			assertEquals(true, keys.contains(javaKeyArray[i]));
		}
	}

	@Test
	void testValues() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		Hashtable<String, Integer> javaHashTable = new Hashtable<String, Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			hashTable.put("" + num, num);
			javaHashTable.put("" + num, num);
		}

		Collection<Integer> values = hashTable.values();
		Collection<Integer> javaValues = javaHashTable.values();
		Object[] javaValueArray = javaValues.toArray();

		for (int i = 0; i < 1000; i++) {
			assertEquals(true, values.contains(javaValueArray[i]));
		}
	}

	@Test
	void testEntrySet() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
		Hashtable<String, Integer> javaHashTable = new Hashtable<String, Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			hashTable.put("" + num, num);
			javaHashTable.put("" + num, num);
		}

		Collection<Integer> values = hashTable.values();
		Collection<Integer> javaValues = javaHashTable.values();
		Object[] javaValueArray = javaValues.toArray();

		for (int i = 0; i < 1000; i++) {
			assertEquals(true, values.contains(javaValueArray[i]));
		}
	}
}
