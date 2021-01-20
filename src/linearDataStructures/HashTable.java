package linearDataStructures;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class HashTable<K, V> extends HashSetImplementation.HashTable<K, V> implements Map<K, V> {
	/**
	 * The set of keys
	 */
	private Set<K> keys = new HashSetImplementation<K>();

	/**
	 * The set of values
	 */
	private Collection<V> values = new HashSetImplementation<V>();

	/**
	 * The set of key value pairs
	 */
	private Set<Entry<K, V>> map = new HashSetImplementation<Entry<K, V>>();

	/**
	 * Default constructor with default size of 2048 and max loading factor of 0.75.
	 */
	public HashTable() {
		super();
	}

	/**
	 * Constructor with an input size and a max loading factor of 0.75.
	 * 
	 * @param size Size of the HashTable
	 */
	public HashTable(int size) {
		super(size);
	}

	/**
	 * Constructor with an input size and input loading factor.
	 * 
	 * @param size          Size of the HashTable
	 * @param loadingFactor Max loading factor
	 */
	public HashTable(int size, double loadingFactor) {
		super(size, loadingFactor);
	}

	/**
	 * Returns if the HashTable has the following value
	 * 
	 * @requires value is of type V
	 * @return true if contains this value
	 */
	@Override
	public boolean containsValue(Object value) {
		return this.values.contains(value);
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
			// update sets
			this.keys.add(key);
			this.values.add(value);
			this.map.add(new Tuple<K, V>(key, value));
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
				// update sets
				this.values.remove(currentValue);
				this.map.remove(new Tuple<K, V>(key, currentValue));
				this.values.add(value);
				this.map.add(new Tuple<K, V>(key, value));
				return currentValue; // return the old value
			}
		}
		// otherwise, we've reached the end of the linked list, and simply add a new
		// node to the list
		listIterator.add(new Tuple<K, V>(key, value));
		// update sets
		this.keys.add(key);
		this.values.add(value);
		this.map.add(new Tuple<K, V>(key, value));
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
				// update sets
				this.keys.remove(currentKey);
				this.values.remove(currentValue);
				this.numValues--;
				this.map.remove(new Tuple<K, V>(currentKey, currentValue));
				// return the removed value
				return keyValuePair.getValue();
			}
		}
		// otherwise, we've reached the end of the linked list, and still haven't found
		// it
		return null;
	}

	/**
	 * Returns all keys
	 */
	@Override
	public Set<K> keySet() {
		return this.keys;
	}

	/**
	 * Returns all values
	 */
	@Override
	public Collection<V> values() {
		return this.values;
	}

	/**
	 * Returns all mappings
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		return this.map;
	}

}
