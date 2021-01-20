package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import linearDataStructures.HashTable;

class HashTableTest {

//	@Test
//	void basicTest() {
//		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
//		hashTable.put("3", 3);
//		assertEquals(true, hashTable.containsKey("3"));
//		assertEquals(true, hashTable.containsValue(3));
//	}
//
//	@Test
//	void testPutDistinct() {
//		HashTable<String, Integer> hashTable = new HashTable<String, Integer>();
//		Hashtable<String, Integer> javaHashTable = new Hashtable<String, Integer>();
//		for (int i = 0; i < 10; i++) {
//			hashTable.put("" + i, i);
//			javaHashTable.put("" + i, i);
//		}
//		for (int i = 0; i < 10; i++) {
//			assertEquals(true, hashTable.containsKey("" + i));
//			assertEquals(true, hashTable.containsValue(i));
//			assertEquals(javaHashTable.get("" + i), hashTable.get("" + i));
//		}
//
//	}

	@Test
	void testResize() {
		HashTable<String, Integer> hashTable = new HashTable<String, Integer>(1);
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " Iteration");
			hashTable.put("" + i, i);
			System.out.println(hashTable.get("" + i));
			for (int j = 0; j < i; j++) {
				System.out.println(hashTable.get("" + j));
			}
			System.out.println();
		}

		for (int i = 0; i < 10; i++) {
			assertEquals(true, hashTable.containsKey("" + i));
			assertEquals(true, hashTable.containsValue(i));
		}

	}

}
