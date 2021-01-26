package treesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import trees.Heap;


class HeapTest {

	@Test
	void basicTest() {
		Heap<Integer> heap = new Heap<Integer>();
		heap.add(25);
		heap.add(3);
		heap.add(20);
		heap.add(1);
		heap.add(2);
		heap.add(17);
		heap.add(23);
		Object[] heapArray = heap.getContents();
		assertEquals(25, heapArray[0]);
		assertEquals(23, heapArray[2]);
		assertEquals(17, heapArray[5]);
		assertEquals(7, heapArray.length);
		heap.pop();
		heap.pop();
		heapArray = heap.getContents();
		assertEquals(20, heapArray[0]);
		assertEquals(5, heapArray.length);
	}

	@Test
	void addThenPop() {
		Heap<Integer> heap = new Heap<Integer>();
		heap.add(25);
		Object[] heapArray = heap.getContents();
		assertEquals(25, heapArray[0]);
		assertEquals(1, heapArray.length);
		heap.pop();
		heapArray = heap.getContents();
		assertEquals(0, heapArray.length);
	}
}
