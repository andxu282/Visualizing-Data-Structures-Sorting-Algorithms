package trees;

/**
 * An implementation of a max heap. Index 0 represents how many real values are
 * stored.
 * 
 * @author andrew
 *
 */
public class MinHeap<T extends Comparable<T>> extends Heap<T> {
	public MinHeap() {
		super();
	}

	public MinHeap(int size) {
		super(size);
	}

	@Override
	protected boolean swapCondition(T value1, T value2) {
		return value1.compareTo(value2) < 0;
	}
}

