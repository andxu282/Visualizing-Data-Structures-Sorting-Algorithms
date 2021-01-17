package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import linearDataStructures.QueueImplementation;

class QueueTest {

	@Test
	void basicTest() {
		QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
		queue.offer(3);
		queue.offer(4);
		assertEquals(3, queue.peek());
		assertEquals(3, queue.poll());
		assertEquals(4, queue.peek());
	}

	@Test
	void test1() {
		QueueImplementation<Integer> queue = new QueueImplementation<Integer>();
		LinkedList<Integer> javaQueue = new LinkedList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			queue.offer(num);
			javaQueue.offer(num);
		}

		for (int i = 0; i < 1000; i++) {
			assertEquals(javaQueue.poll(), queue.poll());
		}
	}

}
