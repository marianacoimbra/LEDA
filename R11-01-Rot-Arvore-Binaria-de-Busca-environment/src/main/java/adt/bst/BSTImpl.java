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
		return height(root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		return Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return seacrh(element, root);
	}

	public BSTNode<T> seacrh(T searched, BSTNode<T> node) {
		if (searched != null && !node.isEmpty()) {
			if (searched.compareTo(node.getData()) > 0) {
				return seacrh(searched, (BSTNode<T>) node.getRight());
			} else if (searched.compareTo(node.getData()) < 0) {
				return seacrh(searched, (BSTNode<T>) node.getLeft());
			} else {
				return node;
			}
		}
		return new BSTNode();
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
		return recursiveMinimum(root);
	}

	private BSTNode<T> recursiveMinimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return recursiveMinimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		BSTNode<T> result = recursiveMinimum((BSTNode<T>) node.getRight());
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
		if (node.isEmpty())
			return null;
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

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf())
				node.setData(null);
			else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
				swap(auxNode, node);
			} else if ((node.getLeft().isEmpty() && !node.getRight().isEmpty())) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getRight();
				swap(auxNode, node);
			} else {
				BSTNode<T> auxNode = recursiveMinimum((BSTNode<T>) node.getRight());

				T aux = node.getData();

				node.setData(auxNode.getData());
				auxNode.setData(aux);

				remove(auxNode);
			}
		}
	}

	private void swap(BSTNode<T> auxNode, BSTNode<T> node) {
		if (node.getParent() == null) {
			auxNode.setParent(null);
			this.root = auxNode;
		} else {
			if (node.getParent().getLeft().getData().equals(node.getData())) {
				node.getParent().setLeft(auxNode);
			} else {
				node.getParent().setRight(auxNode);
			}
			auxNode.setParent(node.getParent());
		}
	}

	

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		recursivePreOrder(array, 0, root);
		return array;
	}

	private int recursivePreOrder(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[i++] = node.getData();
			i = recursivePreOrder(array, i, (BSTNode<T>) node.getLeft());
			i = recursivePreOrder(array, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		recursiveOrder(array, 0, root);
		return array;
	}

	private int recursiveOrder(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = recursiveOrder(array, i, (BSTNode<T>) node.getLeft());
			array[i++] = node.getData();
			i = recursiveOrder(array, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		recursivePostOrder(array, 0, root);
		return array;
	}

	private int recursivePostOrder(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = recursivePostOrder(array, i, (BSTNode<T>) node.getLeft());
			i = recursivePostOrder(array, i, (BSTNode<T>) node.getRight());
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