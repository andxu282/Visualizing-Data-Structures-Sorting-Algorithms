package trees;

public class BinarySearchTree<T extends Comparable<T>> {
	/**
	 * Invariants: All elements greater than to the current node is on the right,
	 * otherwise, they are on the left.
	 * 
	 * @author andrew
	 *
	 * @param <K>
	 */
	private static class Node<K> {
		K value;
		Node<K> left;
		Node<K> right;

		Node(K value) {
			this.value = value;
		}
	}

	private Node<T> root;
	private int numValues = 0;

	public BinarySearchTree() {

	}

	public BinarySearchTree(T value) {
		this.root = new Node<T>(value);
	}

	public int size() {
		return this.numValues;
	}

	public boolean insert(T value) {
		if (this.find(value)) {
			return false;
		}
		numValues++;
		this.root = insert(this.root, value);
		return true;
	}

	public boolean remove(T value) {
		if (!this.find(value)) {
			return false;
		}
		numValues--;
		this.root = remove(this.root, value);
		return true;
	}

	public boolean find(T value) {
		if (numValues == 0) {
			return false;
		}
		return find(this.root, value) != null;
	}

	public void traverseInOrder() {
		traverseInOrder(this.root);
		System.out.println();
	}

	public void traversePreOrder() {
		traversePreOrder(this.root);
		System.out.println();
	}

	public void traversePostOrder() {
		traversePostOrder(this.root);
		System.out.println();
	}

	private Node<T> insert(Node<T> node, T value) {
		if (node == null) {
			node = new Node<T>(value);
			return node;
		} else if (node.value.compareTo(value) > 0) {
			node.left = insert(node.left, value);
		} else if (node.value.compareTo(value) < 0) {
			node.right = insert(node.right, value);
		} else if (node.value.compareTo(value) == 0) {
			System.err.println("Element already exists, duplicates aren't allowed.");
			return null;
		}
		return node;
	}

	private Node<T> find(Node<T> node, T value) {
		if (node == null) {
			return null;
		} else if (node.value.compareTo(value) == 0) {
			return node;
		} else if (node.value.compareTo(value) > 0) {
			return find(node.left, value);
		} else {
			return find(node.right, value);
		}
	}

	private Node<T> remove(Node<T> node, T value) {
		if (node == null) {
			return node;
		}
		if (node.value.compareTo(value) > 0) {
			node.left = remove(node.left, value);
		} else if (node.value.compareTo(value) < 0) {
			node.right = remove(node.right, value);
		} else {
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}
			node.value = minValue(node.right);
			node.right = remove(node.right, node.value);
		}
		return node;
	}

	private T minValue(Node<T> node) {
		T minv = node.value;
		while (node.left != null) {
			minv = node.left.value;
			node = node.left;
		}
		return minv;
	}

	private void traverseInOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		traverseInOrder(node.left);
		System.out.print(node.value + " ");
		traverseInOrder(node.right);
	}

	private void traversePreOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		traverseInOrder(node.left);
		traverseInOrder(node.right);
	}

	private void traversePostOrder(Node<T> node) {
		if (node == null) {
			return;
		}
		traverseInOrder(node.left);
		traverseInOrder(node.right);
		System.out.print(node.value + " ");
	}
}
