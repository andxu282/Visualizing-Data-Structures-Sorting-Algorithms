package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueImplementation<T> implements Queue<T> {
	private DoublyLinkedList<T> queue;
	private int maxSize;

	public QueueImplementation() {
		this.queue = new DoublyLinkedList<T>();
	}

	public QueueImplementation(int maxSize) {
		this.queue = new DoublyLinkedList<T>();
		this.maxSize = maxSize;
	}

	@Override
	public boolean offer(Object e) {
		try {
			this.add(e);
			return true;
		} catch (OutOfMemoryError error) {
			return false;
		}
	}

	@Override
	public T remove() {
		T removedElement = this.poll();
		if (removedElement == null) {
			throw new NoSuchElementException();
		}
		return removedElement;
	}

	@Override
	public T poll() {
		if (this.isEmpty()) {
			return null;
		}
		return queue.remove(0);
	}

	@Override
	public T element() {
		T peekedElement = this.peek();
		if (peekedElement == null) {
			throw new NoSuchElementException();
		}
		return peekedElement;
	}

	@Override
	public T peek() {
		if (this.isEmpty()) {
			return null;
		}
		return queue.get(0);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return queue.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}

	@Override
	public Object[] toArray() {
		return queue.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		return queue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return queue.remove(o);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsAll(Collection c) {
		return queue.containsAll(c);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean addAll(Collection c) {
		return queue.addAll(c);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean removeAll(Collection c) {
		return removeAll(c);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean retainAll(Collection c) {
		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public boolean add(Object e) {
		if (this.maxSize == this.size()) {
			throw new OutOfMemoryError();
		}
		return queue.add(e);
	}
}
