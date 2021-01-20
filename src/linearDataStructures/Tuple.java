package linearDataStructures;

import java.util.Map.Entry;

public class Tuple<K, V> implements Entry<K, V> {
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

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	public int hashCode() {
		return key.hashCode() + value.hashCode();
	}
}
