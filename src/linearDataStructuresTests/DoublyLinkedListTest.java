package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import org.junit.jupiter.api.Test;

import linearDataStructures.DoublyLinkedList;

class DoublyLinkedListTest {

	@Test
	void basicTest() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		list.add(3);
		list.add(4);
		javaList.add(3);
		javaList.add(4);
		assertEquals(javaList.get(0), list.get(0));
		assertEquals(javaList.get(1), list.get(1));
	}

	@Test
	void testSize() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			list.add(r.nextInt());
		}
		assertEquals(list.size(), 1000);
	}

	@Test
	void testSize1() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertEquals(list.size(), 0);
	}

	@Test
	void testEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertEquals(list.isEmpty(), true);
	}

	@Test
	void testEmpty1() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(3);
		assertEquals(list.isEmpty(), false);
	}

	@Test
	void testAdd() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		for (int i = 0; i < 100; i++) {
			list.add((Integer) i);
			javaList.add((Integer) i);
		}
		for (int i = 0; i < 100; i++) {
		}

	}

	@Test
	void testAddIndex() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		LinkedList<String> javaList = new LinkedList<String>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			int index = r.nextInt(i + 1);
			list.add(index, "" + i);
			javaList.add(index, "" + i);
		}

		for (int i = 0; i < 100; i++) {
			assertEquals(javaList.get(i), list.get(i));
		}
	}

	@Test
	void testContains() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			list.add(num);
			supplementaryList.add(num);
		}

		for (Integer n : supplementaryList) {
			assertEquals(list.contains(n), true);
		}
	}

	@Test
	void testRemoveObject() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		LinkedList<String> javaList = new LinkedList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("" + i);
			javaList.add("" + i);
		}
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int index = r.nextInt(100 - i);
			list.remove(index + "");
			javaList.remove(index + "");
		}

		for (int i = 0; i < 90; i++) {
			assertEquals(javaList.get(i), list.get(i));
		}
	}

	@Test
	void testRemoveObject1() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}
		list.remove(0);
		for (int i = 1; i < 10; i++) {
			assertEquals(list.get(i - 1), "" + i);
		}
	}

	@Test
	void testRemoveObject2() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}
		list.remove(9);
		for (int i = 0; i < 9; i++) {
			assertEquals(list.get(i), "" + i);
		}
	}

	@Test
	void testRemoveIndex() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		LinkedList<String> javaList = new LinkedList<String>();
		for (int i = 0; i < 100; i++) {
			list.add("" + i);
			javaList.add("" + i);
		}
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int index = r.nextInt(100 - i);
			list.remove(index);
			javaList.remove(index);
		}

		for (int i = 0; i < 90; i++) {
			assertEquals(javaList.get(i), list.get(i));
		}
	}

	@Test
	void testRemoveIndex1() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}
		list.remove(0);
		for (int i = 1; i < 10; i++) {
			assertEquals(list.get(i - 1), "" + i);
		}
	}

	@Test
	void testRemoveIndex2() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}
		list.remove(9);
		for (int i = 0; i < 9; i++) {
			assertEquals(list.get(i), "" + i);
		}
	}

	@Test
	void testToArray() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		Object[] expectedList = new Object[10];

		for (int i = 0; i < 10; i++) {
			list.add("" + i);
			expectedList[i] = "" + i;
		}
		Object[] actualList = list.toArray();
		for (int i = 0; i < 10; i++) {
			assertEquals(actualList[i], expectedList[i]);
		}
	}

	@Test
	void testToArray1() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		Object[] expectedList = new Object[6];

		for (int i = 0; i < 10; i++) {
			list.add("" + i);
		}

		for (int i = 0; i < 6; i++) {
			expectedList[i] = "" + i;
		}

		Object[] fillerList = new Object[6];
		Object[] actualList = list.toArray(fillerList);
		for (int i = 0; i < 6; i++) {
			assertEquals(actualList[i], expectedList[i]);
		}
	}

	@Test
	void testContainsAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			list.add(num);
			supplementaryList.add(num);
		}
		assertEquals(list.containsAll(supplementaryList), true);
	}

	@Test
	void testAddAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			supplementaryList.add(num);
		}
		list.addAll(supplementaryList);
		assertEquals(list.containsAll(supplementaryList), true);
	}

	@Test
	void testAddAllEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		list.addAll(supplementaryList);
		assertEquals(list.isEmpty(), true);
	}

	@Test
	void testAddAllIndex() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			supplementaryList.add(num);
		}
		list.addAll(0, supplementaryList);
		assertEquals(list.containsAll(supplementaryList), true);
	}

	@Test
	void testAddAllIndexEmpty() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		list.addAll(0, supplementaryList);
		assertEquals(list.isEmpty(), true);
	}

	@Test
	void testAddAllIndex1() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 50; i++) {
			int num = r.nextInt();
			list.add(num);
			javaList.add(num);
		}

		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			supplementaryList.add(num);
		}
		list.addAll(40, supplementaryList);
		javaList.addAll(40, supplementaryList);
		for (int i = 0; i < 1050; i++) {
			assertEquals(list.get(i), javaList.get(i));
		}
	}

	@Test
	void testAddAllIndex2() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(2);
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			supplementaryList.add(num);
		}
		list.addAll(2, supplementaryList);
		assertEquals(list.containsAll(supplementaryList), true);
	}

	@Test
	void testRemoveAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt();
			list.add(num);
			supplementaryList.add(num);
		}
		assertEquals(list.removeAll(supplementaryList), true);
		assertEquals(list.isEmpty(), true);
	}

	@Test
	void testRemoveAll1() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			list.add(i);
			supplementaryList.add(i + 1000);
		}
		assertEquals(list.removeAll(supplementaryList), false);
	}

	@Test
	void testRemoveAll2() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}

		for (int i = 0; i < 500; i++) {
			int num = r.nextInt(100);
			supplementaryList.add(num);
		}

		assertEquals(list.removeAll(supplementaryList), javaList.removeAll(supplementaryList));
	}

	@Test
	void testRetainAll() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}

		for (int i = 0; i < 500; i++) {
			int num = r.nextInt(100);
			supplementaryList.add(num);
		}

		assertEquals(list.retainAll(supplementaryList), javaList.retainAll(supplementaryList));
		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), javaList.get(i));
		}
	}

	@Test
	void testSet() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}

		for (int i = 0; i < 100; i++) {
			int element = r.nextInt(100);
			int index = r.nextInt(1000);
			list.set(index, element);
			javaList.set(index, element);
		}

		for (int i = 0; i < 1000; i++) {
			assertEquals(list.get(i), javaList.get(i));
		}
	}

	@Test
	void testIndexOf() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}
		for (int i = 0; i < 100; i++) {
			int randomIndex = r.nextInt(1000);
			assertEquals(javaList.indexOf(javaList.get(randomIndex)), list.indexOf(list.get(randomIndex)));
		}
		assertEquals(javaList.indexOf(1000), list.indexOf(1000));
	}

	@Test
	void testLastIndexOf() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}
		for (int i = 0; i < 100; i++) {
			int randomIndex = r.nextInt(1000);
			assertEquals(javaList.lastIndexOf(javaList.get(randomIndex)), list.lastIndexOf(list.get(randomIndex)));
		}
		assertEquals(javaList.lastIndexOf(1000), list.lastIndexOf(1000));
	}

	@Test
	void testSubList() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}

		int fromIndex = r.nextInt(500);
		int toIndex = 500 + r.nextInt(500);

		DoublyLinkedList<Integer> subList = (DoublyLinkedList<Integer>) list.subList(fromIndex, toIndex);
		List<Integer> javaSubList = javaList.subList(fromIndex, toIndex);

		for (int i = 0; i < toIndex - fromIndex; i++) {
			assertEquals(subList.get(i), javaSubList.get(i));
		}
	}

	@Test
	void testListIteratorIterate() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			list.add(num);
			javaList.add(num);
		}

		ListIterator<Integer> listIterator = list.listIterator();
		ListIterator<Integer> javaListIterator = javaList.listIterator();

		while (javaListIterator.hasNext()) {
			assertEquals(javaListIterator.next(), listIterator.next());
		}
	}

	@Test
	void testListIteratorAdd() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		ListIterator<Integer> listIterator = list.listIterator();
		ListIterator<Integer> javaListIterator = javaList.listIterator();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			listIterator.add(num);
			javaListIterator.add(num);
		}

		for (int i = 0; i < 1000; i++) {
			assertEquals(javaList.get(i), list.get(i));
		}
	}

	@Test
	void testListIteratorRemove() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		Random r = new Random();
		ListIterator<Integer> listIterator = list.listIterator();
		ListIterator<Integer> javaListIterator = javaList.listIterator();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			listIterator.add(num);
			javaListIterator.add(num);
		}

		for (int i = 0; i < 100; i++) {
			int limit = r.nextInt(1001 - i);
			ListIterator<Integer> listIterator1 = list.listIterator();
			ListIterator<Integer> javaListIterator1 = javaList.listIterator();
			for (int j = 0; j < limit; j++) {
				listIterator1.next();
				javaListIterator1.next();
			}
			listIterator1.remove();
			javaListIterator1.remove();
		}

		for (int i = 0; i < 900; i++) {
			assertEquals(javaList.get(i), list.get(i));
		}
	}
}

