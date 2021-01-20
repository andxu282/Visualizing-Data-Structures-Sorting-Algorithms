package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class HashSetImplementation<T> implements Set<T> {

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
		return set.containsKey(o);
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

	static class HashTable<K, V> implements Map<K, V> {
		/**
		 * HashTable internally represented by an array of doubly linked lists of
		 * tuples. Default size: 2048.
		 */
		@SuppressWarnings("unchecked")
		protected DoublyLinkedList<Tuple<K, V>>[] hashTable = new DoublyLinkedList[2048];

		/**
		 * Total number of key value pairs this table holds.
		 */
		protected int numValues = 0;

		/**
		 * The max loading factor of this table. Default: 0.75
		 */
		protected double loadingFactor = 0.75;

		/**
		 * Default constructor with default size of 2048 and max loading factor of 0.75.
		 */
		public HashTable() {

		}

		/**
		 * Constructor with an input size and a max loading factor of 0.75.
		 * 
		 * @param size Size of the HashTable
		 */
		@SuppressWarnings("unchecked")
		public HashTable(int size) {
			hashTable = new DoublyLinkedList[size];
		}

		/**
		 * Constructor with an input size and input loading factor.
		 * 
		 * @param size          Size of the HashTable
		 * @param loadingFactor Max loading factor
		 */
		@SuppressWarnings("unchecked")
		public HashTable(int size, double loadingFactor) {
			hashTable = new DoublyLinkedList[size];
			this.loadingFactor = loadingFactor;
		}

		/**
		 * Hashes a key to its appropriate bucket. Uses multiplicative hashing.
		 * 
		 * @param key Object to be hashed
		 * @return hashedIndex
		 */
		protected int hashFunction(K key) {
			double a = key.hashCode() * ((Math.sqrt(5) + 1) / 2.0 - 1);
			int hashCode = ((int) Math.floor(this.size() * (key.hashCode() * (a - Math.floor(a))))) % this.size();
			System.out.print(key + " ");
			System.out.println(hashCode);
			return hashCode;
		}

		/**
		 * Doubles the size of the HashTable when the max loading factor is exceeded.
		 */
		@SuppressWarnings("unchecked")
		protected void resize() {
			// if the current loading factor is less than the max, then don't do anything
			if (this.calculateLoadingFactor() < this.loadingFactor) {
				return;
			}

			System.out.println("Resizing");
			// save the old hash table
			DoublyLinkedList<Tuple<K, V>>[] oldHashTable = this.hashTable;
			// set the new hash table to be one of double the size
			this.hashTable = new DoublyLinkedList[this.size() * 2];
			this.numValues = 0;
			// loop over the old hash table
			for (int i = 0; i < oldHashTable.length; i++) {
				// retrieve the bucket at index i
				DoublyLinkedList<Tuple<K, V>> currentLinkedList = oldHashTable[i];
				// if there's nothing at that bucket, continue
				if (currentLinkedList == null) {
					continue;
				}
				// iterate over linked list
				ListIterator<Tuple<K, V>> listIterator = currentLinkedList.listIterator();
				while (listIterator.hasNext()) {
					// put these key value pairs into the new hash table
					Tuple<K, V> keyValuePair = listIterator.next();
					K key = keyValuePair.getKey();
					V value = keyValuePair.getValue();
					this.put(key, value);
				}
			}
		}

		/**
		 * Calculates the loading factor.
		 * 
		 * @return loading factor
		 */
		protected double calculateLoadingFactor() {
			return (this.numValues / 1.0) / (this.size() / 1.0);
		}

		/**
		 * Size of the HashTable
		 */
		@Override
		public int size() {
			return this.hashTable.length;
		}

		/**
		 * @return true if empty
		 */
		@Override
		public boolean isEmpty() {
			return numValues == 0;
		}

		/**
		 * @requires key is of type K
		 * @return true if contains this key
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean containsKey(Object key) {
			// calculate the hashed index
			int hashedIndex = this.hashFunction((K) key);
			// retrieve the linked list at this index
			DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
			// if there's nothing at this bucket, return false
			if (hashedLinkedList == null) {
				return false;
			}
			// iterate over linked list
			ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
			while (listIterator.hasNext()) {
				if (listIterator.next().getKey().equals(key)) { // key found
					return true;
				}
			}
			return false;
		}

		/**
		 * @requires value is of type V
		 * @return true if contains this value
		 */
		@Override
		public boolean containsValue(Object value) {
			throw new UnsupportedOperationException();
		}

		/**
		 * Retreives the value for the given key.
		 * 
		 * @requires key is of type K
		 * @return value of type V for the given key found in the HashTable.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public V get(Object key) {
			// find hashed index
			int hashedIndex = this.hashFunction((K) key);
			// return the linked list at this index
			DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
			// if empty, return null
			if (hashedLinkedList == null) {
				return null;
			}
			// otherwise, iterate over the linked list
			ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
			while (listIterator.hasNext()) {
				Tuple<K, V> keyValuePair = listIterator.next();
				if (keyValuePair.getKey().equals(key)) { // return value if key is found
					return keyValuePair.getValue();
				}
			}
			return null;
		}

		/**
		 * Puts a key value pair into the HashTable.
		 * 
		 * @return old value for the given key, null if no such value
		 */
		@Override
		public V put(K key, V value) {
			// find hashed index
			int hashedIndex = this.hashFunction((K) key);
			// return linked list at this index
			DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
			// if linked list is null, then create a new linked list with this key value
			// pair as its head
			if (hashedLinkedList == null) {
				hashTable[hashedIndex] = new DoublyLinkedList<Tuple<K, V>>(new Tuple<K, V>(key, value));
				this.numValues++;
				this.resize();
				return null;
			}
			// otherwise, start looping over the current linked list
			ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
			while (listIterator.hasNext()) {
				Tuple<K, V> keyValuePair = listIterator.next();
				K currentKey = keyValuePair.getKey();
				V currentValue = keyValuePair.getValue();
				// if we have a matching key, then replace the current value with the new one
				if (currentKey.equals(key)) {
					listIterator.set(new Tuple<K, V>(key, value));
					return currentValue; // return the old value
				}
			}
			// otherwise, we've reached the end of the linked list, and simply add a new
			// node to the list
			listIterator.add(new Tuple<K, V>(key, value));
			this.numValues++;
			this.resize();
			// no old value found, so return null
			return null;
		}

		/**
		 * Removes a key value pair based on the given key.
		 * 
		 * @return value of type V of the key value pair removed, null if nothing
		 *         removed
		 */
		@SuppressWarnings("unchecked")
		@Override
		public V remove(Object key) {
			// find hashed index
			int hashedIndex = this.hashFunction((K) key);
			// get the linked list at this index
			DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
			// if there's no linked list, then we can't remove anything
			if (hashedLinkedList == null) {
				return null;
			}
			// otherwise, begin looping over linked list
			ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
			while (listIterator.hasNext()) {
				Tuple<K, V> keyValuePair = listIterator.next();
				K currentKey = keyValuePair.getKey();
				V currentValue = keyValuePair.getValue();
				// if there's a match, remove this node from the linked list
				if (currentKey.equals(key)) {
					listIterator.remove();
					this.numValues--;
					// return the removed value
					return currentValue;
				}
			}
			// otherwise, we've reached the end of the linked list, and still haven't found
			// it
			return null;
		}

		/**
		 * Puts all of the mappings in the given map into the HashTable
		 * 
		 */
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
			Set<? extends K> keys = m.keySet();
			Collection<? extends V> values = m.values();
			Iterator<? extends K> keysIterator = keys.iterator();
			Iterator<? extends V> valuesIterator = values.iterator();
			// iterate over the map and put the key value pairs in one by one
			while (keysIterator.hasNext()) {
				this.put(keysIterator.next(), valuesIterator.next());
			}
		}

		/**
		 * Clears the HashTable
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void clear() {
			int size = this.size();
			hashTable = new DoublyLinkedList[size];
		}

		/**
		 * Returns all keys
		 */
		@Override
		public Set<K> keySet() {
			throw new UnsupportedOperationException();
		}

		/**
		 * Returns all values
		 */
		@Override
		public Collection<V> values() {
			throw new UnsupportedOperationException();
		}

		/**
		 * Returns all mappings
		 */
		@Override
		public Set<Entry<K, V>> entrySet() {
			throw new UnsupportedOperationException();
		}
	}
}
