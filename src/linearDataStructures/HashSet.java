package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashSet<T> implements Set<T> {
	/**
	 * HashSet internally represented by HashTable, contains no duplicates and no
	 * set order.
	 */
	HashTable<T, T> set = new HashTable<T, T>();

	/**
	 * Size of set
	 */
	@Override
	public int size() {
		return set.size();
	}

	/**
	 * Returns whether this set is empty
	 * 
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/**
	 * Returns whether this object is in the set.
	 * 
	 * @return true if the object is in the set.
	 */
	@Override
	public boolean contains(Object o) {
		return set.containsValue(o);
	}

	/**
	 * Creates an iterator over the set.
	 */
	@Override
	public Iterator<T> iterator() {
		return new SetIterator<T>(this.toArray());
	}

	/**
	 * Turns the set into an array.
	 */
	@Override
	public Object[] toArray() {
		Collection<T> setValues = set.values();
		return setValues.toArray();
	}

	/**
	 * Turns the set into an array.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		Collection<T> setValues = set.values();
		return setValues.toArray(a);
	}

	/**
	 * Adds an element of type T to the set. Will fail if the element is already in
	 * the set.
	 * 
	 * @return true if set is modified.
	 */
	@Override
	public boolean add(T e) {
		return set.put(e, e) == null;
	}

	/**
	 * Removes an element from the set.
	 * 
	 * @return true if set is modified.
	 */
	@Override
	public boolean remove(Object o) {
		return set.remove(o) != null;
	}

	/**
	 * Checks if every element in a collection is in the set.
	 * 
	 * @return true if every element in the collection is in the set.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		// iterate over c
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			// if at any point, we find an element in c that isn't in the set, return false
			if (!this.contains(cIterator.next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds all the elements in c to the set
	 * 
	 * @return true if set is modified
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean elementsAdded = false;
		// iterates over c and adds them one by one
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (this.add((T) cIterator.next())) {
				elementsAdded = true;
			}
		}
		return elementsAdded;
	}

	/**
	 * Retains all elements in c that are also in the set.
	 * 
	 * @return true if set is modified
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean elementsRemoved = false;
		Object[] setArray = this.toArray();
		for (Object o : setArray) {
			// if this element of the set is not in c, then remove it
			if (!c.contains(o)) {
				this.remove(o);
				elementsRemoved = true;
			}
		}
		return elementsRemoved;
	}

	/**
	 * Remove all elements in c from the set
	 * 
	 * @return true if the set is modified.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean elementsRemoved = false;
		// iterate over c
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			// will only remove it if the element is in the set, otherwise, no effect
			if (this.remove((T) cIterator.next())) {
				elementsRemoved = true;
			}
		}
		return elementsRemoved;
	}

	/**
	 * Clears the set.
	 */
	@Override
	public void clear() {
		set.clear();
	}

	/**
	 * SetIterator iterates over the elements of the set
	 * 
	 * @author andrew
	 *
	 * @param <K>
	 */
	private class SetIterator<K> implements Iterator<K> {
		/**
		 * The set represented by an Object array
		 */
		private Object[] set;
		/**
		 * The current next index when next is called.
		 */
		private int currentNextIndex = 0;

		/**
		 * Constructs an iterator from a set.
		 * 
		 * @param set an Object array representing the set.
		 */
		SetIterator(Object[] set) {
			this.set = set;
		}

		/**
		 * Returns if the iterator can continue.
		 */
		@Override
		public boolean hasNext() {
			// if the currentNextIndex is less than the set.length, it can proceed.
			return currentNextIndex < set.length;
		}

		/**
		 * Returns the next value of the set, if there is one.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public K next() {
			if (this.hasNext()) {
				K data = (K) set[currentNextIndex];
				// increment the current next index because we move forward
				currentNextIndex++;
				// return the data
				return data;
			}
			return null;
		}

	}
}
