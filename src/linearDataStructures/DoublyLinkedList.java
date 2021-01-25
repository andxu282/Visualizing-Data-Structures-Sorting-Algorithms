package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyLinkedList<T> implements List<T> {
	private Node<T> head;

	public DoublyLinkedList() {
		this.head = new Node<T>();
		this.head.setPreviousNode(new Node<T>());
		this.head.setNextNode(new Node<T>());
	}

	public DoublyLinkedList(T data) {
		this.head = new Node<T>(data, new Node<T>(), new Node<T>());
	}

	@Override
	public int size() { // tested
		Node<T> currentNode = this.head;
		int size = 0;
		while (!currentNode.isEmpty()) {
			currentNode = currentNode.getNextNode();
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() { // tested
		return this.head.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) { // tested
		ListIterator<T> iterator = this.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals((T) o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() { // tested
		return this.listIterator();
	}

	@Override
	public Object[] toArray() { // tested
		Object[] listToArray = new Object[this.size()];
		Node<T> currentNode = this.head;
		int index = 0;
		while (!currentNode.isEmpty()) {
			listToArray[index] = currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return listToArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) { // tested
		int size = this.size();
		Object[] listToArray = new Object[size];
		if (a.length > size) {
			listToArray = a;
			listToArray[size] = null;
		}
		Node<T> currentNode = this.head;
		int index = 0;
		while (!currentNode.isEmpty()) {
			listToArray[index] = currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		}
		return listToArray;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Object e) { // tested
		if (this.head.isEmpty()) {
			this.head = new Node<T>((T) e, new Node<T>(), new Node<T>());
			return true;
		}
		Node<T> currentNode = this.head;
		while (!currentNode.getNextNode().isEmpty()) {
			currentNode = currentNode.getNextNode();
		}
		Node<T> newNode = new Node<T>((T) e, currentNode, new Node<T>());
		currentNode.setNextNode(newNode);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) { // tested
		Node<T> currentNode = head;
		while (!currentNode.isEmpty()) {
			if (currentNode.getData().equals((T) o)) {
				Node<T> previousNode = currentNode.getPreviousNode();
				Node<T> nextNode = currentNode.getNextNode();
				if (previousNode.isEmpty() && !nextNode.isEmpty()) { // removing head
					head = nextNode;
					nextNode.setPreviousNode(previousNode);
				} else if (!previousNode.isEmpty() && nextNode.isEmpty()) { // removing tail
					previousNode.setNextNode(nextNode);
				} else if (!previousNode.isEmpty() && !nextNode.isEmpty()) {
					nextNode.setPreviousNode(previousNode);
					previousNode.setNextNode(nextNode);
				} else if (nextNode.isEmpty() && previousNode.isEmpty()) { // removing the only element
					this.head.clear();
				}
				return true;
			}
			currentNode = currentNode.getNextNode();
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean containsAll(Collection c) { // tested
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
	public boolean addAll(Collection c) { // tested
		if (this.equals(c)) {
			return false;
		}

		// traverse to end of current list
		Node<T> currentNode = head;
		if (!this.isEmpty()) {
			while (!currentNode.getNextNode().isEmpty()) {
				currentNode = currentNode.getNextNode();
			}
		}
		// add elements of c
		Iterator cIterator = c.iterator();
		if (this.isEmpty()) {
			if (cIterator.hasNext()) {
				head = new Node<T>((T) cIterator.next(), new Node<T>(), new Node<T>());
				currentNode = head;
			}
		}
		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), currentNode, new Node<T>());
			currentNode.setNextNode(addedNode);
			currentNode = addedNode;
		}
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean addAll(int index, Collection c) { // tested
		if (this.equals(c)) {
			return false;
		}

		// traverse to index
		Node<T> previousNode = this.head.getPreviousNode();
		Node<T> currentNode = this.head;
		int currentIndex = 0;
		while (!currentNode.isEmpty() && currentIndex < index) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		Node<T> firstNodeAfter = currentNode;
		Node<T> nodeBefore = previousNode;
		// add elements of c
		Iterator cIterator = c.iterator();
		if (this.isEmpty()) {
			if (cIterator.hasNext()) {
				head = new Node<T>((T) cIterator.next(), new Node<T>(), new Node<T>());
				nodeBefore = head;
			}
		}

		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), nodeBefore, new Node<T>());
			if (!nodeBefore.isEmpty()) {
				nodeBefore.setNextNode(addedNode);
			}
			nodeBefore = addedNode;
		}
		if (!nodeBefore.isEmpty()) {
			nodeBefore.setNextNode(firstNodeAfter);
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean removeAll(Collection c) { // tested
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
	public boolean retainAll(Collection c) { // tested
		boolean removedItems = false;
		ListIterator iterator = this.listIterator();
		while (iterator.hasNext()) {
			Object nextItem = iterator.next();
			if (!c.contains(nextItem)) {
				this.remove(nextItem);
				removedItems = true;
			}
		}
		return removedItems;
	}

	@Override
	public void clear() { // tested
		this.head.clear();
	}

	@Override
	public T get(int index) { // tested
		if (index >= this.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = 0;
		Node<T> currentNode = this.head;
		while (currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		return currentNode.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object set(int index, Object element) { // tested
		if (index >= this.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = 0;
		Node<T> currentNode = head;
		while (!currentNode.isEmpty() && currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		T currentData = currentNode.getData();
		currentNode.setData((T) element);
		return currentData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) { // tested
		if (this.isEmpty()) {
			this.head = new Node<T>((T) element, new Node<T>(), new Node<T>());
			return;
		}

		int currentIndex = 0;
		Node<T> previousNode = this.head.getPreviousNode();
		Node<T> currentNode = this.head;
		while (!currentNode.isEmpty() && currentIndex < index) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		Node<T> newNode = new Node<T>((T) element, previousNode, currentNode);

		if (previousNode.isEmpty() && !currentNode.isEmpty()) { // adding to head
			this.head = newNode;
			currentNode.setPreviousNode(newNode);
		} else if (!previousNode.isEmpty() && currentNode.isEmpty()) { // adding to tail
			previousNode.setNextNode(newNode);
		} else if (!previousNode.isEmpty() && !currentNode.isEmpty()) { // adding to middle
			currentNode.setPreviousNode(newNode);
			previousNode.setNextNode(newNode);
		}
	}

	@Override
	public T remove(int index) { // tested
		if (index >= this.size()) {
			return null;
		}

		int currentIndex = 0;
		Node<T> currentNode = head;
		while (!currentNode.isEmpty() && currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		Node<T> previousNode = currentNode.getPreviousNode();
		Node<T> nextNode = currentNode.getNextNode();
		if (previousNode.isEmpty() && !nextNode.isEmpty()) { // removing head
			head = nextNode;
			nextNode.setPreviousNode(previousNode);
		} else if (!previousNode.isEmpty() && nextNode.isEmpty()) { // removing tail
			previousNode.setNextNode(nextNode);
		} else if (!previousNode.isEmpty() && !nextNode.isEmpty()) {
			nextNode.setPreviousNode(previousNode);
			previousNode.setNextNode(nextNode);
		} else if (nextNode.isEmpty() && previousNode.isEmpty()) { // removing the only element
			head.clear();
		}
		return currentNode.getData();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) { // tested
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
	public int lastIndexOf(Object o) { // tested
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
		LinkedListIterator<T> listIterator;
		if (this.head.isEmpty()) {
			listIterator = new LinkedListIterator<T>();
		} else {
			listIterator = new LinkedListIterator<T>(this.head, this.head);
		}
		this.head = listIterator.headOfIterator;
		return listIterator;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		if (index == 0) {
			return this.listIterator();
		}
		int currentIndex = 0;
		Node<T> currentNode = head;
		while (currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}

		LinkedListIterator<T> listIterator = new LinkedListIterator<T>(this.head, currentNode);
		return listIterator;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) { // tested
		int currentIndex = 0;
		Node<T> currentNode = head;
		while (currentIndex < fromIndex) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		DoublyLinkedList<T> subList = new DoublyLinkedList<T>(currentNode.getData());
		while (currentIndex < toIndex - 1) {
			currentIndex++;
			currentNode = currentNode.getNextNode();
			subList.add(currentNode.getData());
		}
		return subList;
	}

	private class Node<K> {
		private K data;
		private Node<K> previous;
		private Node<K> next;
		
		Node() {
			this.data = null;
			this.previous = null;
			this.next = null;
		}

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

		void clear() {
			this.data = null;
		}

		boolean isEmpty() {
			return this.data == null;
		}
	}

	private class LinkedListIterator<K> implements Iterator<K>, ListIterator<K> {
		int operationCalled = 0;
		int currentPreviousIndex = -1;
		int currentNextIndex = 0;
		Node<K> currentPreviousNode;
		Node<K> currentNextNode;
		Node<K> headOfIterator;

		LinkedListIterator() {
			headOfIterator = new Node<K>();
			headOfIterator.setPreviousNode(new Node<K>());
			headOfIterator.setNextNode(new Node<K>());
			currentPreviousNode = headOfIterator.getPreviousNode();
			currentNextNode = headOfIterator;
		}

		LinkedListIterator(Node<K> head, Node<K> node) {
			if (head.isEmpty()) {
				headOfIterator = new Node<K>();
			} else {
				headOfIterator = head;
			}
			currentPreviousNode = node.getPreviousNode();
			currentNextNode = node;
		}

		@Override
		public boolean hasNext() {
			return !currentNextNode.isEmpty();
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
			return !currentPreviousNode.isEmpty();
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
			Node<K> newNode = new Node<K>(e, currentPreviousNode, currentNextNode);
			if (currentPreviousNode.isEmpty() && currentNextNode.isEmpty()) { // creating new list
				this.headOfIterator.setData(e);
				currentPreviousNode = this.headOfIterator;
				currentNextNode = this.headOfIterator.getNextNode();
			} else if (currentPreviousNode.isEmpty() && !currentNextNode.isEmpty()) { // adding to head
				Node<K> afterHead = new Node<K>(this.headOfIterator.getData(), currentNextNode,
						this.headOfIterator.getNextNode());
				this.headOfIterator.setNextNode(afterHead);
				this.headOfIterator.setData(e);
				currentNextNode = afterHead;
				currentPreviousNode = this.headOfIterator;
			} else if (!currentPreviousNode.isEmpty() && currentNextNode.isEmpty()) { // adding to tail
				currentPreviousNode.setNextNode(newNode);
				currentPreviousNode = newNode;
			} else if (!currentPreviousNode.isEmpty() && !currentNextNode.isEmpty()) { // adding to middle
				currentNextNode.setPreviousNode(newNode);
				currentPreviousNode.setNextNode(newNode);
				currentPreviousNode = newNode;
			}
			currentPreviousIndex++;
			operationCalled = 0;
		}

		@Override
		public void remove() {
			if (operationCalled == 0) {
				return;
			} else if (operationCalled == 1) {
				if (currentPreviousNode.getPreviousNode().isEmpty() && currentNextNode.isEmpty()) {
					this.headOfIterator.clear();
				} else if (currentPreviousNode.getPreviousNode().isEmpty() && !currentNextNode.isEmpty()) { // removing
																											// head
					Node<K> afterNewHead = currentNextNode.getNextNode();
					this.headOfIterator.setNextNode(afterNewHead);
					this.headOfIterator.setData(currentNextNode.getData());
					afterNewHead.setPreviousNode(this.headOfIterator);
					currentNextNode = this.headOfIterator;
					currentPreviousNode = new Node<K>();
				} else if (!currentPreviousNode.getPreviousNode().isEmpty() && currentNextNode.isEmpty()) { // removing
																											// tail
					Node<K> newTail = currentPreviousNode.getPreviousNode();
					newTail.setNextNode(new Node<K>());
					currentPreviousNode = newTail;
				} else if (!currentPreviousNode.getPreviousNode().isEmpty() && !currentNextNode.isEmpty()) { // removing
																										// middle
					Node<K> newPrevious = currentPreviousNode.getPreviousNode();
					currentNextNode.setPreviousNode(newPrevious);
					newPrevious.setNextNode(currentNextNode);
					currentPreviousNode = newPrevious;
				}
				currentPreviousIndex--;
				currentNextIndex--;

			} else if (operationCalled == -1) {
				if (currentPreviousNode.isEmpty() && currentNextNode.getNextNode().isEmpty()) {
					this.headOfIterator.clear();
				} else if (currentPreviousNode.isEmpty() && !currentNextNode.getNextNode().isEmpty()) { // removing head
					Node<K> newHead = currentNextNode.getNextNode();
					this.headOfIterator.setData(newHead.getData());
					this.headOfIterator.setNextNode(newHead.getNextNode());
					currentNextNode = newHead;
				} else if (!currentPreviousNode.isEmpty() && currentNextNode.getNextNode().isEmpty()) { // removing tail
					currentPreviousNode.setNextNode(new Node<K>());
					currentNextNode = new Node<K>();
				} else if (!currentPreviousNode.isEmpty() && !currentNextNode.getNextNode().isEmpty()) { // removing
																											// middle
					Node<K> newNext = currentNextNode.getNextNode();
					newNext.setPreviousNode(currentPreviousNode);
					currentPreviousNode.setNextNode(newNext);
					currentNextNode = newNext;
				}
			}
			operationCalled = 0;
		}
	}
}

