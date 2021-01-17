package linearDataStructures;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class HashTable<K, V> implements Map<K, V> {
	private DoublyLinkedList<Tuple<K, V>>[] hashTable;
	private int numValues = 0;

	@SuppressWarnings("unchecked")
	public HashTable() {
		hashTable = new DoublyLinkedList[2048];
	}

	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		hashTable = new DoublyLinkedList[size];
	}

	private int hashFunction(K key) {
		double a = key.hashCode() * ((Math.sqrt(5) + 1) / 2.0 - 1);
		return (int) Math.floor(this.size() * (key.hashCode() * (a - Math.floor(a))));
	}

	private void resize() {

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
		// TODO Auto-generated method stub
		return false;
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
		ListIterator<Tuple<K, V>> listIterator = hashedLinkedList.listIterator();
		while (listIterator.hasNext()) {
			Tuple<K, V> keyValuePair = listIterator.next();
			if (keyValuePair.getKey().equals(key)) {
				listIterator.set(new Tuple<K, V>(key, value));
				return keyValuePair.getValue();
			}
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

}
