package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import linearDataStructures.HashSetImplementation;

class HashSetTest {

	@Test
	void test() {
		Set<Integer> hashSet = new HashSetImplementation<Integer>();
		Set<Integer> hashSet1 = new HashSet<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(10000);
			hashSet.add(num);
			hashSet1.add(num);
		}
		Object[] javaHashSet = hashSet1.toArray();
		for (int i = 0; i < javaHashSet.length; i++) {
			assertEquals(true, hashSet.contains(javaHashSet[i]));
		}
	}

}
