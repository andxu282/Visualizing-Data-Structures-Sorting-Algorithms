package linearDataStructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class HashTable<K, V> implements Map<K, V> {

	/**
	 * HashTable internally represented by an array of doubly linked lists of
	 * tuples. Default size: 2048.
	 */
	@SuppressWarnings("unchecked")
	private DoublyLinkedList<Tuple<K, V>>[] hashTable = new DoublyLinkedList[2048];

	/**
	 * Total number of key value pairs this table holds.
	 */
	private int numValues = 0;

	/**
	 * The max loading factor of this table. Default: 0.75
	 */
	private double loadingFactor = 0.75;

	/**
	 * The set of keys
	 */
	private Set<K> keys = new HashSet<K>();

	/**
	 * The set of values
	 */
	private Collection<V> values = new HashSet<V>();

	/**
	 * The set of key value pairs
	 */
	private Set<Entry<K, V>> map = new HashSet<Entry<K, V>>();

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
	private int hashFunction(K key) {
		double a = key.hashCode() * ((Math.sqrt(5) + 1) / 2.0 - 1);
		return (int) Math.floor(this.size() * (key.hashCode() * (a - Math.floor(a))));
	}

	/**
	 * Doubles the size of the HashTable when the max loading factor is exceeded.
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		// if the current loading factor is less than the max, then don't do anything
		if (this.calculateLoadingFactor() < this.loadingFactor) {
			return;
		}

		// save the old hash table
		DoublyLinkedList<Tuple<K, V>>[] oldHashTable = this.hashTable;
		// set the new hash table to be one of double the size
		this.hashTable = new DoublyLinkedList[this.size() * 2];
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
	private double calculateLoadingFactor() {
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
