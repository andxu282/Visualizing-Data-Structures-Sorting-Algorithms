package trees;

import java.util.NoSuchElementException;

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

	public void insert(T value) {
		this.root = insert(this.root, value);
		return;
	}

	public void remove(T value) {
		if (numValues == 0) {
			throw new NoSuchElementException();
		}
		this.root = remove(this.root, value);
		return;
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
			numValues++;
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
			return null;
		} else if (node.value.compareTo(value) == 0) {
			numValues--;
			if (node.left == null && node.right == null) {
				node = null;
			} else if (node.left == null && node.right != null) {
				node = node.right;
			} else if (node.left != null && node.right == null) {
				node = node.left;
			} else if (node.left != null && node.right != null) {
				Node<T> currentParent = node.left;
				Node<T> currentNode = node.left;
				int i = 0;
				while (currentNode.right != null) {
					currentParent = currentNode;
					currentNode = currentNode.right;
					i++;
				}
				if (i == 0) {
					currentNode.right = node.right;
				} else {
					currentNode.left = node.left;
					currentNode.right = node.right;
					currentParent.right = null;
				}
				node = currentNode;
			}
		} else if (node.value.compareTo(value) > 0) {
			node.left = remove(node.left, value);
		} else {
			node.right = remove(node.right, value);
		}
		return node;
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
