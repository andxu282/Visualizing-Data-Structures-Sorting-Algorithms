package trees;

public class BinarySearchTree {
	private static class Node {
		int value;
		Node left;
		Node right;

		Node(int value) {
			this.value = value;
		}
	}

	public Node root;

	public BinarySearchTree(int value) {
		root = new Node(value);
	}

	private Node insert(Node node, int value) {
		if (node == null) {
			return new Node(value);
		} else if (node.value > value) {
			node.left = insert(node.left, value);
		} else if (node.value < value) {
			node.right = insert(node.right, value);
		} else {
			System.out.println("Value already exists. Duplicates not allowed");
			return node;
		}
		return node;
	}

	public void insert(int value) {
		this.root = insert(this.root, value);
	}

	private Node find(Node node, int value) {
		if (node == null) {
			return null;
		}

		if (value < node.value) {
			return find(node.left, value);
		} else if (value == node.value) {
			return node;
		} else if (value > node.value) {
			return find(node.right, value);
		} else {
			return null;
		}
	}

	private Node remove(Node node, int value) {
		if (node == null) {
			return null;
		}
		if (value == node.value) {
			if (node.left == null && node.right == null) {
				return null;
			} else if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			} else {
				if (node.left.value < node.right.value) {
					node.left.right = node.right;
					return node.left;
				} else {
					node.right.left = node.left;
					return node.right;
				}
			}
		} else if (value < node.value) {
			node.left = remove(node.left, value);
			return node;
		} else if (value > node.value) {
			node.right = remove(node.right, value);
			return node;
		} else {
			return null;
		}
	}

	public void remove(int value) {
		this.root = remove(this.root, value);
	}

	public boolean find(int value) {
		if (find(this.root, value) == null) {
			return false;
		}
		return true;
	}

	public void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			System.out.print(node.value + " ");
			traverseInOrder(node.right);
		}
	}

	public void traversePreOrder(Node node) {
		if (node != null) {
			System.out.print(node.value + " ");
			traversePreOrder(node.left);
			traversePreOrder(node.right);
		}
	}

	public void traversePostOrder(Node node) {
		if (node != null) {
			traversePostOrder(node.left);
			traversePostOrder(node.right);
			System.out.print(node.value + " ");
		}
	}
}
