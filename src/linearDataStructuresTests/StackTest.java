package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.Stack;

import org.junit.jupiter.api.Test;

import linearDataStructures.StackImplementation;

class StackTest {

	@Test
	void test() {
		StackImplementation<Integer> stack = new StackImplementation<Integer>();
		Stack<Integer> javaStack = new Stack<Integer>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int num = r.nextInt(100);
			stack.push(num);
			javaStack.push(num);
		}

		for (int i = 0; i < 1000; i++) {
			assertEquals(javaStack.pop(), stack.pop());
		}
	}

}
