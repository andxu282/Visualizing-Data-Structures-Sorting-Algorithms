package linearDataStructures;

public class Tuple<K, V> {
	private K key;
	private V value;

	public Tuple(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}
}
