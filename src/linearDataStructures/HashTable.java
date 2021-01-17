package linearDataStructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class HashTable<K, V> implements Map<K, V> {
	@SuppressWarnings("unchecked")
	private DoublyLinkedList<Tuple<K, V>>[] hashTable = hashTable = new DoublyLinkedList[2048];
	private int numValues = 0;
	private double loadingFactor = 0.75;
	private Set<K> keys = new HashSet<K>();
	private Collection<V> values = new HashSet<V>();
	private Set<Entry<K, V>> map = new HashSet<Entry<K, V>>();

	public HashTable() {
	}

	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		hashTable = new DoublyLinkedList[size];
	}

	@SuppressWarnings("unchecked")
	public HashTable(int size, double loadingFactor) {
		hashTable = new DoublyLinkedList[size];
		this.loadingFactor = loadingFactor;
	}

	private int hashFunction(K key) {
		double a = key.hashCode() * ((Math.sqrt(5) + 1) / 2.0 - 1);
		return (int) Math.floor(this.size() * (key.hashCode() * (a - Math.floor(a))));
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		if (this.calculateLoadingFactor() < this.loadingFactor) {
			return;
		}

		DoublyLinkedList<Tuple<K, V>>[] oldHashTable = this.hashTable;
		this.hashTable = new DoublyLinkedList[this.size() * 2];
		for (int i = 0; i < oldHashTable.length; i++) {
			DoublyLinkedList<Tuple<K, V>> currentLinkedList = oldHashTable[i];
			if (currentLinkedList == null) {
				continue;
			}
			ListIterator<Tuple<K, V>> listIterator = currentLinkedList.listIterator();
			while (listIterator.hasNext()) {
				Tuple<K, V> keyValuePair = listIterator.next();
				K key = keyValuePair.getKey();
				V value = keyValuePair.getValue();
				this.put(key, value);
			}
		}
	}

	private double calculateLoadingFactor() {
		return (this.numValues / 1.0) / (this.size() / 1.0);
	}

	@SuppressWarnings("unchecked")
	private void updateMap() {
		Object[] keys = this.keys.toArray();
		for (int i = 0; i < keys.length; i++) {
			K key = (K) keys[i];
			map.add(new Tuple<K, V>(key, this.get(key)));
		}
	}

	@Override
	public int size() {
		return this.hashTable.length;
	}

	@Override
	public boolean isEmpty() {
		return numValues == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(Object key) {
		int hashedIndex = this.hashFunction((K) key);
		DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
		ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
		while (listIterator.hasNext()) {
			if (listIterator.next().getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return this.values().contains(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		int hashedIndex = this.hashFunction((K) key);
		DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
		ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
		while (listIterator.hasNext()) {
			Tuple<K, V> keyValuePair = listIterator.next();
			if (keyValuePair.getKey().equals(key)) {
				return keyValuePair.getValue();
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		int hashedIndex = this.hashFunction((K) key);
		DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
		if (hashedLinkedList == null) {
			hashTable[hashedIndex] = new DoublyLinkedList<Tuple<K, V>>(new Tuple<K, V>(key, value));
			return null;
		}
		ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
		while (listIterator.hasNext()) {
			Tuple<K, V> keyValuePair = listIterator.next();
			if (keyValuePair.getKey().equals(key)) {
				listIterator.set(new Tuple<K, V>(key, value));
				this.values.remove(keyValuePair.getValue());
				this.values.add(value);
				return keyValuePair.getValue();
			}
		}
		listIterator.add(new Tuple<K, V>(key, value));
		this.keys.add(key);
		this.values.add(value);
		this.numValues++;
		this.resize();
		this.updateMap();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		int hashedIndex = this.hashFunction((K) key);
		DoublyLinkedList<Tuple<K, V>> hashedLinkedList = hashTable[hashedIndex];
		ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
		while (listIterator.hasNext()) {
			Tuple<K, V> keyValuePair = listIterator.next();
			if (keyValuePair.getKey().equals(key)) {
				listIterator.remove();
				this.keys.remove(keyValuePair.getKey());
				this.values.remove(keyValuePair.getValue());
				this.numValues--;
				this.updateMap();
				return keyValuePair.getValue();
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		Set<? extends K> keys = m.keySet();
		Collection<? extends V> values = m.values();
		Iterator<? extends K> keysIterator = keys.iterator();
		Iterator<? extends V> valuesIterator = values.iterator();
		while (keysIterator.hasNext()) {
			this.put(keysIterator.next(), valuesIterator.next());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		int size = this.size();
		hashTable = new DoublyLinkedList[size];
	}

	@Override
	public Set<K> keySet() {
		return this.keys;
	}

	@Override
	public Collection<V> values() {
		return this.values;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return this.map;
	}

}
