package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListImplementation<T> implements List<T> {
	private Node<T> head;


	public LinkedListImplementation() {
		this.head = null;
	}

	public LinkedListImplementation(T data) {
		this.head = new Node<T>(data, null);
	}

	@Override
	public int size() {
		Node<T> currentNode = this.head;
		int size = 0;

		while (currentNode != null) {
			currentNode = currentNode.getNextNode();
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
		Node<T> newNode = new Node<T>((T) e, null);
		if (this.isEmpty()) {
			head = newNode;
			return true;
		}

		Node<T> currentNode = this.head;
		while (currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
		}

		currentNode.setNextNode(newNode);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		// while the current node is non-null
		while (currentNode != null) {
			// if the current node is the one we want to remove
			if (currentNode.getData().equals((T) o)) {
				if (previousNode != null) {
					// make the current node orphaned
					previousNode.setNextNode(currentNode.getNextNode());
				} else {
					// if previous is null, then we are removing the head, set new head
					head = currentNode.getNextNode();
				}
				return true;
			}
			// otherwise, continue looping through the linked list
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsAll(Collection c) {
		Iterator cIterator = c.iterator();
		// loops while c has more items to check
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
		// if c and this linked list are the same object, can't perform the operation
		if (this.equals(c)) {
			return false;
		}

		// traverse to end of current list
		Node<T> currentNode = head;
		if (!this.isEmpty()) {
			while (currentNode.getNextNode() != null) {
				currentNode = currentNode.getNextNode();
			}
		}

		// add elements of c
		Iterator cIterator = c.iterator();
		if (this.isEmpty()) {
			if (cIterator.hasNext()) {
				this.head = new Node<T>((T) cIterator.next(), null);
				currentNode = this.head;
			}
		}

		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), null);
			currentNode.setNextNode(addedNode);
			currentNode = addedNode;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 * @requires index is less than or equal to size.
	 */
	public boolean addAll(int index, Collection c) {
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		// if c and this linked list are the same object, can't perform the operation
		if (this.equals(c)) {
			return false;
		}


		// traverse to index
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		int currentIndex = 0;
		if (!this.isEmpty()) {
			while (currentIndex < index) {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
				currentIndex++;
			}
		}

		Node<T> nodeBefore = previousNode;
		Node<T> firstNodeAfter = currentNode;
		Iterator cIterator = c.iterator();
		if (this.isEmpty()) {
			if (cIterator.hasNext()) {
				this.head = new Node<T>((T) cIterator.next(), null);
				nodeBefore = this.head;
			}
		}

		// add elements of c
		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), null);
			if (nodeBefore != null) {
				nodeBefore.setNextNode(addedNode);
			}
			nodeBefore = addedNode;

		}
		if (nodeBefore != null) {
			nodeBefore.setNextNode(firstNodeAfter);
		}
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
		Node<T> currentNode = head;
		while (currentNode != null) {
			T data = currentNode.getData();
			if (!c.contains(data)) {
				this.remove(data);
				removedItems = true;
			}
			currentNode = currentNode.getNextNode();
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
		int currentIndex = 0;
		T data = null;
		Node<T> currentNode = head;
		while (currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		data = currentNode.getData();
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object set(int index, Object element) {
		if (index >= this.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = 0;
		Node<T> currentNode = head;
		while (currentIndex < index) {
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
		if (index > this.size()) {
			throw new IndexOutOfBoundsException();
		}

		int currentIndex = 0;
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		while (currentIndex < index) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		if (previousNode != null) {
			previousNode.setNextNode(new Node<T>((T) element, currentNode));
		} else {
			head = new Node<T>((T) element, currentNode);
		}

	}

	@Override
	public T remove(int index) {
		if (index >= this.size()) {
			return null;
		}

		int currentIndex = 0;
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		while (currentIndex < index) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		if (previousNode != null) {
			previousNode.setNextNode(currentNode.getNextNode());
		} else {
			head = currentNode.getNextNode();
		}

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
		Node<T> node = head;
		while (node.getNextNode() != null) {
			node = node.getNextNode();
		}
		return new LinkedListIterator<T>(node);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		int currentIndex = 0;
		Node<T> currentNode = this.head;
		while (currentIndex < fromIndex) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		LinkedListImplementation<T> subList = new LinkedListImplementation<T>(currentNode.getData());
		while (currentIndex < toIndex - 1) {
			currentIndex++;
			currentNode = currentNode.getNextNode();
			subList.add(currentNode.getData());
		}
		return subList;
	}


	private class Node<K> {
		private K data;
		private Node<K> next;


		Node(K data, Node<K> next) {
			this.data = data;
			this.next = next;
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

		void setNextNode(Node<K> next) {
			this.next = next;
		}

	}

	private class LinkedListIterator<K> implements Iterator<K>, ListIterator<K> {
		int operationCalled = 0; // 0 = already called set/add/remove, 1 = next, -1 = previous
		int currentPreviousIndex = -1;
		int currentNextIndex = 0;
		Node<K> currentPreviousPreviousNode;
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
				K data = currentNextNode.getData();
				currentPreviousPreviousNode = currentPreviousNode;
				currentPreviousNode = currentNextNode;
				currentNextNode = currentNextNode.getNextNode();
				currentNextIndex++;
				currentPreviousIndex++;
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
				K data = currentPreviousNode.getData();
				currentNextNode = currentPreviousNode;
				currentPreviousNode = currentPreviousPreviousNode;
				currentNextIndex--;
				currentPreviousIndex--;
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
			currentPreviousNode.setNextNode(new Node<K>(e, currentNextNode));
			operationCalled = 0;
		}

		@Override
		public void remove() {
			if (operationCalled == 0) {
				return;
			} else if (operationCalled == 1) {
				currentPreviousPreviousNode.setNextNode(currentNextNode);
				operationCalled = 0;
			} else if (operationCalled == -1) {
				currentPreviousNode.setNextNode(currentNextNode.getNextNode());
				operationCalled = 0;
			}
		}
	}

}
