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
	public int size() { // tested
		Node<T> currentNode = this.head;
		int size = 0;
		while (currentNode != null) {
			currentNode = currentNode.getNextNode();
			size++;
		}
		return size;
	}

	@Override
	public boolean isEmpty() { // tested
		return head == null;
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
		while (currentNode != null) {
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
	public boolean add(Object e) { // tested
		if (this.head == null) {
			this.head = new Node<T>((T) e, null, null);
			return true;
		}
		Node<T> currentNode = this.head;
		while (currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
		}
		Node<T> newNode = new Node<T>((T) e, currentNode, null);
		currentNode.setNextNode(newNode);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) { // tested
		Node<T> currentNode = head;
		while (currentNode != null) {
			if (currentNode.getData().equals((T) o)) {
				Node<T> previousNode = currentNode.getPreviousNode();
				Node<T> nextNode = currentNode.getNextNode();
				if (previousNode == null && nextNode != null) { // removing head
					head = nextNode;
					nextNode.setPreviousNode(previousNode);
				} else if (previousNode != null && nextNode == null) { // removing tail
					previousNode.setNextNode(nextNode);
				} else if (previousNode != null && nextNode != null) {
					nextNode.setPreviousNode(previousNode);
					previousNode.setNextNode(nextNode);
				} else if (nextNode == null && previousNode == null) { // removing the only element
					head = null;
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
			while (currentNode.getNextNode() != null) {
				currentNode = currentNode.getNextNode();
			}
		}
		// add elements of c
		Iterator cIterator = c.iterator();
		if (this.isEmpty()) {
			if (cIterator.hasNext()) {
				head = new Node<T>((T) cIterator.next(), null, null);
				currentNode = head;
			}
		}
		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), currentNode, null);
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
		Node<T> previousNode = null;
		Node<T> currentNode = head;
		int currentIndex = 0;
		while (currentNode != null && currentIndex < index) {
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
				head = new Node<T>((T) cIterator.next(), null, null);
				nodeBefore = head;
			}
		}

		while (cIterator.hasNext()) {
			Node<T> addedNode = new Node<T>((T) cIterator.next(), nodeBefore, null);
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
		this.head = null;
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
		while (currentNode != null && currentIndex < index) {
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
			this.head = new Node<T>((T) element, null, null);
			return;
		}

		int currentIndex = 0;
		Node<T> previousNode = null;
		Node<T> currentNode = this.head;
		while (currentNode != null && currentIndex < index) {
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		Node<T> newNode = new Node<T>((T) element, previousNode, currentNode);

		if (previousNode == null && currentNode != null) { // adding to head
			head = newNode;
			currentNode.setPreviousNode(newNode);
		} else if (previousNode != null && currentNode == null) { // adding to tail
			previousNode.setNextNode(newNode);
		} else if (previousNode != null && currentNode != null) { // adding to middle
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
		while (currentNode != null && currentIndex < index) {
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		Node<T> previousNode = currentNode.getPreviousNode();
		Node<T> nextNode = currentNode.getNextNode();
		if (previousNode == null && nextNode != null) { // removing head
			head = nextNode;
			nextNode.setPreviousNode(previousNode);
		} else if (previousNode != null && nextNode == null) { // removing tail
			previousNode.setNextNode(nextNode);
		} else if (previousNode != null && nextNode != null) {
			nextNode.setPreviousNode(previousNode);
			previousNode.setNextNode(nextNode);
		} else if (nextNode == null && previousNode == null) { // removing the only element
			head = null;
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
		if (this.head == null) {
			listIterator = new LinkedListIterator<T>();
			this.head = listIterator.headOfIterator;
		} else {
			listIterator = new LinkedListIterator<T>(this.head, this.head);
			this.head = listIterator.headOfIterator;
		}
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
		}

		LinkedListIterator(Node<K> head, Node<K> node) {
			if (head == null) {
				headOfIterator = new Node<K>();
			} else {
				headOfIterator = head;
			}
			currentPreviousNode = node.getPreviousNode();
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
			Node<K> newNode = new Node<K>(e, currentPreviousNode, currentNextNode);
			if (currentPreviousNode == null && currentNextNode == null) { // creating new list
				this.headOfIterator.setData(e);
				currentPreviousNode = this.headOfIterator;
			} else if (currentPreviousNode == null && currentNextNode != null) { // adding to head
				Node<K> afterHead = new Node<K>(this.headOfIterator.getData(), currentNextNode,
						this.headOfIterator.getNextNode());
				this.headOfIterator.setNextNode(afterHead);
				this.headOfIterator.setData(e);
				currentNextNode = afterHead;
				currentPreviousNode = this.headOfIterator;
			} else if (currentPreviousNode != null && currentNextNode == null) { // adding to tail
				currentPreviousNode.setNextNode(newNode);
				currentPreviousNode = newNode;
			} else if (currentPreviousNode != null && currentNextNode != null) { // adding to middle
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

