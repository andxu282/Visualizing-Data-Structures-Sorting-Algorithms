package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StackImplementation<T> implements List<T> {
	private DoublyLinkedList<T> stack;
	private int maxSize;

	public StackImplementation() {
		this.stack = new DoublyLinkedList<T>();
		this.maxSize = Integer.MAX_VALUE;
	}

	public StackImplementation(int maxSize) {
		this.stack = new DoublyLinkedList<T>();
		this.maxSize = maxSize;
	}

	public boolean empty() {
		return stack.isEmpty();
	}

	public T peek() {
		return stack.get(0);
	}

	public T pop() {
		return stack.remove(0);
	}

	public T push(T item) {
		if (this.maxSize == this.size()) {
			throw new StackOverflowError();
		}
		stack.add(0, item);
		return item;
	}

	public int search(Object o) {
		return stack.indexOf(o) + 1;
	}

	@Override
	public void clear() {
		stack.clear();
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return stack.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return stack.iterator();
	}

	@Override
	public Object[] toArray() {
		return stack.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		return stack.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return stack.containsAll(c);
	}

	@Override
	public int indexOf(Object o) {
		return stack.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return stack.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return stack.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return stack.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return stack.subList(fromIndex, toIndex);
	}

	@Override
	public boolean add(T e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException();
	}
}
