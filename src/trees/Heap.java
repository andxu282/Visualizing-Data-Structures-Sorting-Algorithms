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
public class Heap<T extends Comparable<T>> {
	private Object[] heapContents = new Object[2049];

	private int numValues = 0;

	public Heap() {

	}

	public Heap(int size) {
		this.heapContents = new Object[size + 1];
	}


	public Object[] getContents() {
		return Arrays.copyOfRange(this.heapContents, 1, numValues + 1);
	}



	public void add(T newValue) {
		if (!this.hasSpace()) {
			return;
		}
		this.heapContents[numValues + 1] = newValue;
		heapifyUp();
		numValues++;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		T poppedValue = (T) this.heapContents[1];
		this.heapContents[1] = this.heapContents[numValues];
		this.heapContents[numValues] = null;
		heapifyDown();
		numValues--;
		return poppedValue;
	}

	@SuppressWarnings("unchecked")
	private void heapifyDown() {
		int smallValueIndex = 1;
		int leftChildIndex = leftChildIndex(smallValueIndex);
		int rightChildIndex = rightChildIndex(smallValueIndex);
		T smallValue = (T) this.heapContents[smallValueIndex];
		T leftChild = (T) this.heapContents[leftChildIndex];
		T rightChild = (T) this.heapContents[rightChildIndex];
		if (leftChild == null) {
			return;
		} else if (rightChild == null) {
			if (leftChild.compareTo(smallValue) > 0) {
				this.swapNodes(smallValueIndex, leftChildIndex);
			}
			return;
		}
		while (smallValue.compareTo(leftChild) < 0 || smallValue.compareTo(rightChild) < 0) {
			if (leftChild.compareTo(rightChild) > 0) {
				this.swapNodes(smallValueIndex, leftChildIndex);
				smallValueIndex = leftChildIndex;
				leftChildIndex = leftChildIndex(smallValueIndex);
				rightChildIndex = rightChildIndex(smallValueIndex);
				smallValue = (T) this.heapContents[smallValueIndex];
				leftChild = (T) this.heapContents[leftChildIndex];
				rightChild = (T) this.heapContents[rightChildIndex];
			} else if (leftChild.compareTo(rightChild) < 0) {
				this.swapNodes(smallValueIndex, rightChildIndex);
				smallValueIndex = rightChildIndex;
				leftChildIndex = leftChildIndex(smallValueIndex);
				rightChildIndex = rightChildIndex(smallValueIndex);
				smallValue = (T) this.heapContents[smallValueIndex];
				leftChild = (T) this.heapContents[leftChildIndex];
				rightChild = (T) this.heapContents[rightChildIndex];
			}
			if (leftChild == null) {
				return;
			} else if (rightChild == null) {
				if (leftChild.compareTo(smallValue) > 0) {
					this.swapNodes(smallValueIndex, leftChildIndex);
				}
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void heapifyUp() {
		int newValueIndex = numValues + 1;
		int parentIndex = parentIndex(newValueIndex);
		T newValue = (T) this.heapContents[newValueIndex];
		T parent = (T) this.heapContents[parentIndex];
		if (parent == null) {
			return;
		}
		while (newValue.compareTo(parent) > 0 && newValueIndex > 1) {
			this.swapNodes(newValueIndex, parentIndex);
			newValueIndex = parentIndex;
			parentIndex = parentIndex(parentIndex);
			newValue = (T) this.heapContents[newValueIndex];
			parent = (T) this.heapContents[parentIndex];
		}
	}

	private int leftChildIndex(int parentIndex) {
		return 2 * parentIndex;
	}

	private int rightChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int parentIndex(int childIndex) {
		return childIndex / 2;
	}

	private boolean hasSpace() {
		return numValues < this.heapContents.length;
	}

	private boolean isEmpty() {
		return numValues == 0;
	}

	@SuppressWarnings("unchecked")
	private void swapNodes(int index1, int index2) {
		T value1 = (T) this.heapContents[index1];
		T value2 = (T) this.heapContents[index2];
		this.heapContents[index2] = value1;
		this.heapContents[index1] = value2;
	}
}
