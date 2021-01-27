package trees;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class Heap<T extends Comparable<T>> {
	protected Object[] heapContents = new Object[2049];

	protected int numValues = 0;

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

	protected abstract boolean swapCondition(T value1, T value2);

	@SuppressWarnings("unchecked")
	protected void heapifyDown() {
		int smallValueIndex = 1;
		int leftChildIndex = leftChildIndex(smallValueIndex);
		int rightChildIndex = rightChildIndex(smallValueIndex);
		T smallValue = (T) this.heapContents[smallValueIndex];
		T leftChild = (T) this.heapContents[leftChildIndex];
		T rightChild = (T) this.heapContents[rightChildIndex];
		if (leftChild == null) {
			return;
		} else if (rightChild == null) {
			if (swapCondition(leftChild, smallValue)) {
				this.swapNodes(smallValueIndex, leftChildIndex);
			}
			return;
		}
		while (swapCondition(leftChild, smallValue) || swapCondition(rightChild, smallValue)) {
			if (swapCondition(leftChild, rightChild)) {
				this.swapNodes(smallValueIndex, leftChildIndex);
				smallValueIndex = leftChildIndex;
				leftChildIndex = leftChildIndex(smallValueIndex);
				rightChildIndex = rightChildIndex(smallValueIndex);
				smallValue = (T) this.heapContents[smallValueIndex];
				leftChild = (T) this.heapContents[leftChildIndex];
				rightChild = (T) this.heapContents[rightChildIndex];
			} else if (!swapCondition(leftChild, rightChild)) {
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
				if (swapCondition(leftChild, smallValue)) {
					this.swapNodes(smallValueIndex, leftChildIndex);
				}
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void heapifyUp() {
		int newValueIndex = numValues + 1;
		int parentIndex = parentIndex(newValueIndex);
		T newValue = (T) this.heapContents[newValueIndex];
		T parent = (T) this.heapContents[parentIndex];
		if (parent == null) {
			return;
		}
		while (swapCondition(newValue, parent) && newValueIndex > 1) {
			this.swapNodes(newValueIndex, parentIndex);
			newValueIndex = parentIndex;
			parentIndex = parentIndex(parentIndex);
			newValue = (T) this.heapContents[newValueIndex];
			parent = (T) this.heapContents[parentIndex];
			if (parent == null) {
				return;
			}
		}
	}

	protected int leftChildIndex(int parentIndex) {
		return 2 * parentIndex;
	}

	protected int rightChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	protected int parentIndex(int childIndex) {
		return childIndex / 2;
	}

	protected boolean hasSpace() {
		return numValues < this.heapContents.length;
	}

	protected boolean isEmpty() {
		return numValues == 0;
	}

	@SuppressWarnings("unchecked")
	protected void swapNodes(int index1, int index2) {
		T value1 = (T) this.heapContents[index1];
		T value2 = (T) this.heapContents[index2];
		this.heapContents[index2] = value1;
		this.heapContents[index1] = value2;
	}
}
