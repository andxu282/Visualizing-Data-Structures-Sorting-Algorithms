package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedList<T> implements List<T> {
	private Node<T> head;

	public DoublyLinkedList() {
		this.head = null;
	}

	public DoublyLinkedList(T data) {
		this.head = new Node<T>(data, null, null);
	}

	@Override
	public int size() {
		ListIterator<T> iterator = this.listIterator();
		int size = 0;
		while (iterator.hasNext()) {
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals((T) o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return this.listIterator();
	}

	@Override
	public Object[] toArray() {
		Object[] listToArray = new Object[this.size()];
		ListIterator<T> iterator = this.listIterator();
		int index = 0;
		while (iterator.hasNext()) {
			listToArray[index] = iterator.next();
			index++;
		}
		return listToArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		int size = this.size();
		Object[] listToArray = new Object[size];
		if (a.length > size) {
			listToArray = a;
			listToArray[size] = null;
		}
		ListIterator<T> iterator = this.listIterator();
		int index = 0;
		while (iterator.hasNext()) {
			listToArray[index] = iterator.next();
			index++;
		}
		return listToArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Object e) {
		Node<T> currentHead = this.head;
		this.head = new Node<T>((T) e, null, currentHead);
		currentHead.setPreviousNode(this.head);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals((T) o)) {
				previousNode.setNextNode(currentNode.getNextNode());
				return true;
			}
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsAll(Collection c) {
		Iterator cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (!this.contains(cIterator.next())) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean addAll(Collection c) {
		if (this.equals(c)) {
			return false;
		}

		// traverse to end of current list
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			iterator.next();
			currentNode = currentNode.getNextNode();
		}

		// add elements of c
		Iterator cIterator = c.iterator();
		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), currentNode, null);
			currentNode.setNextNode(addedNode);
			currentNode = addedNode;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean addAll(int index, Collection c) {
		if (this.equals(c)) {
			return false;
		}

		// traverse to index
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		int currentIndex = -1;
		while (iterator.hasNext() && currentIndex < index) {
			iterator.next();
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		Node<T> firstNodeAfter = currentNode.getNextNode();

		// add elements of c
		Iterator cIterator = c.iterator();
		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), currentNode, null);
			currentNode.setNextNode(addedNode);
			currentNode = addedNode;
		}
		currentNode.setNextNode(firstNodeAfter);
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean removeAll(Collection c) {
		boolean removedItems = false;
		Iterator cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (this.remove(cIterator.next())) {
				removedItems = true;
			}
		}
		return removedItems;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean retainAll(Collection c) {
		boolean removedItems = false;
		ListIterator iterator = this.listIterator();
		while (iterator.hasNext()) {
			Object nextItem = iterator.next();
			if (!c.contains(nextItem)) {
				this.remove(nextItem);
			}
		}
		return removedItems;
	}

	@Override
	public void clear() {
		this.head = null;
	}

	@Override
	public T get(int index) {
		if (index >= this.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = -1;
		ListIterator<T> iterator = this.listIterator();
		while (currentIndex < index - 1) {
			iterator.next();
			currentIndex++;
		}

		return iterator.next();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object set(int index, Object element) {
		if (index >= this.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = -1;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (currentIndex < index) {
			iterator.next();
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		T currentData = currentNode.getData();
		currentNode.setData((T) element);
		return currentData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) {
		int currentIndex = -1;
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext() && currentIndex < index) {
			iterator.next();
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		previousNode.setNextNode(new Node<T>((T) element, previousNode, currentNode));
	}

	@Override
	public T remove(int index) {
		if (index >= this.size()) {
			return null;
		}

		int currentIndex = -1;
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext() && currentIndex < index) {
			iterator.next();
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		previousNode.setNextNode(currentNode.getNextNode());
		return currentNode.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		int currentIndex = -1;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			currentIndex++;
			if (iterator.next().equals((T) o)) {
				return currentIndex;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int lastIndexOf(Object o) {
		int currentIndex = -1;
		int maxIndex = -1;
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			currentIndex++;
			if (iterator.next().equals((T) o)) {
				maxIndex = currentIndex;
			}
		}
		return maxIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new LinkedListIterator<T>(this.head);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		int currentIndex = -1;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (currentIndex < index) {
			iterator.next();
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		return new LinkedListIterator<T>(currentNode);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		int currentIndex = -1;
		Node<T> currentNode = head;
		ListIterator<T> iterator = this.listIterator();
		while (currentIndex < fromIndex) {
			iterator.next();
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		DoublyLinkedList<T> subList = new DoublyLinkedList<T>();
		for (int i = currentIndex; i < toIndex; i++) {
			subList.add(iterator.next());
		}
		return subList;
	}

	private class Node<K> {
		private K data;
		private Node<K> previous;
		private Node<K> next;

		Node(K data, Node<K> previous, Node<K> next) {
			this.data = data;
			this.previous = previous;
			this.next = next;
		}

		Node<K> getPreviousNode() {
			return this.previous;
		}

		K getData() {
			return this.data;
		}

		Node<K> getNextNode() {
			return this.next;
		}

		void setData(K data) {
			this.data = data;
		}

		void setPreviousNode(Node<K> previous) {
			this.previous = previous;
		}

		void setNextNode(Node<K> next) {
			this.next = next;
		}

	}

	private class LinkedListIterator<K> implements Iterator<K>, ListIterator<K> {
		int operationCalled = 0;
		int currentPreviousIndex = -1;
		int currentNextIndex = 0;
		Node<K> currentPreviousNode;
		Node<K> currentNextNode;

		LinkedListIterator(Node<K> node) {
			currentPreviousNode = null;
			currentNextNode = node;
		}

		@Override
		public boolean hasNext() {
			return currentNextNode != null;
		}

		@Override
		public K next() {
			if (hasNext()) {
				operationCalled = 1;
				K data = currentNextNode.getData();
				currentPreviousNode = currentNextNode;
				currentNextNode = currentNextNode.getNextNode();
				currentPreviousIndex++;
				currentNextIndex++;
				return data;
			}
			return null;
		}

		@Override
		public boolean hasPrevious() {
			return currentPreviousNode != null;
		}

		@Override
		public K previous() {
			if (hasPrevious()) {
				operationCalled = -1;
				K data = currentPreviousNode.getData();
				currentNextNode = currentPreviousNode;
				currentPreviousNode = currentPreviousNode.getPreviousNode();
				currentPreviousIndex--;
				currentNextIndex--;
				return data;
			}
			return null;
		}

		@Override
		public int nextIndex() {
			return currentNextIndex;
		}

		@Override
		public int previousIndex() {
			return currentPreviousIndex;
		}

		@Override
		public void set(K e) {
			if (operationCalled == 0) {
				return;
			} else if (operationCalled == 1) {
				currentPreviousNode.setData(e);
				operationCalled = 0;
			} else if (operationCalled == -1) {
				currentNextNode.setData(e);
				operationCalled = 0;
			}
		}

		@Override
		public void add(K e) {
			if (operationCalled == 0) {
				return;
			}
			Node<K> newNode = new Node<K>(e, currentPreviousNode, currentNextNode);
			currentPreviousNode = newNode;
			currentPreviousIndex++;
			operationCalled = 0;
		}

		@Override
		public void remove() {
			if (operationCalled == 0) {
				return;
			} else if (operationCalled == 1) {
				currentPreviousNode.getPreviousNode().setNextNode(currentNextNode);
				currentPreviousNode = currentPreviousNode.getPreviousNode();
				currentPreviousIndex--;
				currentNextIndex--;
				operationCalled = 0;
			} else if (operationCalled == -1) {
				currentPreviousNode.setNextNode(currentNextNode.getNextNode());
				currentNextNode = currentNextNode.getNextNode();
				operationCalled = 0;
			}
		}
	}

}

