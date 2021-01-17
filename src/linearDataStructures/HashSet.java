package linearDataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashSet<T> implements Set<T> {
	HashTable<T, T> set = new HashTable<T, T>();

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return set.containsValue(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		Collection<T> setValues = set.values();
		return setValues.toArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray(Object[] a) {
		Collection<T> setValues = set.values();
		return setValues.toArray(a);
	}

	@Override
	public boolean add(T e) {
		if (set.containsValue(e)) {
			return false;
		}
		return set.put(e, e) == null;
	}

	@Override
	public boolean remove(Object o) {
		return set.remove(o) != null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (!this.contains(cIterator.next())) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean elementsAdded = false;
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (this.add((T) cIterator.next())) {
				elementsAdded = true;
			}
		}
		return elementsAdded;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean elementsRemoved = false;
		Object[] setArray = this.toArray();
		for (Object o : setArray) {
			if (!c.contains(o)) {
				this.remove(o);
				elementsRemoved = true;
			}
		}
		return elementsRemoved;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean elementsRemoved = false;
		Iterator<?> cIterator = c.iterator();
		while (cIterator.hasNext()) {
			if (this.remove((T) cIterator.next())) {
				elementsRemoved = true;
			}
		}
		return elementsRemoved;
	}

	@Override
	public void clear() {
		set.clear();
	}

}
