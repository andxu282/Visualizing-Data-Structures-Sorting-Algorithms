package trees;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An implementation of a max heap. Index 0 represents how many real values are
 * stored.
 * 
 * @author andrew
 *
 */
public class Heap {
	private int[] heapContents = new int[8];

	public Heap() {

	}

	public Heap(int size) {
		heapContents = new int[size + 1];
	}

	private void heapifyDown() {
		int k = 1;
		while (k < heapContents[0]) {
			int p1 = 2 * k;
			int p2 = 2 * k + 1;
			int p;
			int valToHeapifyDown = heapContents[k];
			if (heapContents[p1] > heapContents[p2]) {
				p = p1;
			} else {
				p = p2;
			}
			if (heapContents[p] > valToHeapifyDown) {
				heapContents[k] = heapContents[p];
				heapContents[p] = valToHeapifyDown;
				k = p;
			} else {
				break;
			}
		}
	}

	public int[] getContents() {
		return heapContents;
	}

	private void heapifyUp() {
		int k = heapContents[0];
		while (k > 1) {
			int p = k / 2;
			int valToHeapUp = heapContents[k];
			if (heapContents[p] < heapContents[k]) {
				heapContents[k] = heapContents[p];
				heapContents[p] = valToHeapUp;
				k = p;
			} else {
				break;
			}
		}
	}

	public void add(int newValue) {
		if (!this.hasSpace()) {
			throw new OutOfMemoryError();
		}
		heapContents[heapContents[0] + 1] = newValue;
		heapContents[0] += 1;
		heapifyUp();
	}

	public int pop() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		int popped = heapContents[1];
		heapContents[1] = heapContents[heapContents[0]];
		heapContents[heapContents[0]] = 0;
		heapContents[0] -= 1;
		heapifyDown();
		return popped;
	}

	private boolean hasSpace() {
		if (heapContents[0] == heapContents.length - 1) {
			return false;
		}
		return true;
	}

	private boolean isEmpty() {
		if (heapContents[0] == 0) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.add(25);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(3);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(20);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(1);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(2);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(17);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.add(23);
		System.out.println(Arrays.toString(heap.getContents()));
		heap.pop();
		System.out.println(Arrays.toString(heap.getContents()));
		heap.pop();
		System.out.println(Arrays.toString(heap.getContents()));
	}
}
