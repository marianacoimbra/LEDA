package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		return Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (element != null && !node.isEmpty()) {
			if (node.getData().equals(element)) {
				return node;
			} else if (element.compareTo(node.getData()) > 0) {
				return search(element, (BSTNode<T>) node.getRight());
			} else if (element.compareTo(node.getData()) < 0) {
				return search(element, (BSTNode<T>) node.getLeft());
			}
			return new BSTNode<T>();
		}
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		insert(element, root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert(element, (BSTNode<T>) node.getRight());
				} else if (element.compareTo(node.getData()) < 0) {
					insert(element, (BSTNode<T>) node.getLeft());
				}
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> result = minimum((BSTNode<T>) node.getRight());
		if (result != null) {
			return result;
		} else {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) < 0) {
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> result = maximum((BSTNode<T>) node.getLeft());
		if (result != null) {
			return result;
		} else {
			result = (BSTNode<T>) node.getParent();
			while (result != null && result.getData().compareTo(node.getData()) > 0) {
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			remove(node);
		}
	}

	protected void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
			} else if (node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
				swap(auxNode, node);
			} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getRight();
				swap(auxNode, node);
			} else {
				BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
				T aux = node.getData();
				node.setData(sucessor.getData());
				sucessor.setData(aux);
				this.remove(sucessor);
			}
		}
	}

	private void swap(BSTNode<T> auxNode, BSTNode<T> node) {
		if (node.getParent() == null) {
			auxNode.setParent(null);
			this.root = auxNode;
		} else if (node.getParent().getLeft().equals(node)) {
			node.getParent().setLeft(auxNode);
		} else if (node.getParent().getRight().equals(node)) {
			node.getParent().setRight(auxNode);
		}
		auxNode.setParent(node.getParent());
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, 0, this.root);
		return array;
	}

	private int preOrder(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[i++] = node.getData();
			i = preOrder(array, i, (BSTNode<T>) node.getLeft());
			i = preOrder(array, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(array, 0,this.root);
		return array;
	}

	private int order(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = order(array, i, (BSTNode<T>) node.getLeft());
			array[i++] = node.getData();
			i = order(array, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, 0, this.root);
		return array;
	}

	private int postOrder(T[] array, int i, BSTNode<T> node) {
		if(!node.isEmpty()) {
			i = postOrder(array, i, (BSTNode<T>) node.getLeft());
			i = postOrder(array, i, (BSTNode<T>) node.getLeft());
			array[i++] = node.getData();
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}