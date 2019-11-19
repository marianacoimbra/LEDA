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
		return this.height(this.root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		}
		return Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (element != null && !node.isEmpty()) {
			if (node.getData().equals(element)) {
				return node;
			} else {
				if (element.compareTo(node.getData()) > 0) {
					return this.search(element, (BSTNode<T>) node.getRight());
				} else if (element.compareTo(node.getData()) < 0) {
					return this.search(element, (BSTNode<T>) node.getLeft());
				}
			}
			return new BSTNode<T>();
		}

		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) > 0) {
				this.insert(element, (BSTNode<T>) node.getRight());
			} else if (element.compareTo(node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		} else {
			if (node.getRight().isEmpty()) {
				return node;
			} else {
				return maximum((BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (isEmpty()) {
			return null;
		} else {
			if (node.getLeft().isEmpty()) {
				return node;
			} else {
				return minimum((BSTNode<T>) node.getLeft());
			}
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			BSTNode<T> answer = null;
			if (!node.isEmpty()) {
				if (!node.getRight().isEmpty()) {
					answer = minimum((BSTNode<T>) node.getRight());
				} else {
					answer = (BSTNode<T>) node.getParent();
					while (answer != null && answer.getData().compareTo(node.getData()) < 0) {
						answer = (BSTNode<T>) answer.getParent();
					}
				}
			}
			return answer;
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			BSTNode<T> answer = null;
			if (!node.isEmpty()) {
				if (!node.getRight().isEmpty()) {
					answer = maximum((BSTNode<T>) node.getLeft());
				} else {
					answer = (BSTNode<T>) node.getParent();
					while (answer != null && answer.getData().compareTo(node.getData()) > 0) {
						answer = (BSTNode<T>) answer.getParent();
					}
				}
			}
			return answer;
		}
		return null;
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
			if (node.isLeaf()) {
				node.setData(null);
			} else if (!node.getLeft().isEmpty() || !node.getRight().isEmpty()) {
				if (node.getRight().isEmpty()) {
					BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
					swap(auxNode, node);
				} else if (node.getLeft().isEmpty()) {
					BSTNode<T> auxNode = (BSTNode<T>) node.getRight();
					swap(auxNode, node);
				}
			} else {
				BSTNode<T> sucessor = this.sucessor(node.getData());
				T aux = node.getData();

				node.setData(sucessor.getData());
				sucessor.setData(aux);

				this.remove(aux);
			}
		}
	}

	private void swap(BSTNode<T> auxNode, BSTNode<T> node) {
		if (node.getParent().isEmpty()) {
			this.root = auxNode;
			auxNode.setParent(null);
		} else if (node.getParent().getRight().equals(node)) {
			node.getParent().setRight(auxNode);
		} else if (node.getParent().getLeft().equals(node)) {
			node.getParent().setLeft(auxNode);
		}
		auxNode.setParent(node.getParent());
	}

	@Override
	public T[] preOrder() {
		T[] list = (T[]) new Comparable[this.size()];

		preOrder(list, this.root, 0);
		return list;
	}

	private int preOrder(T[] list, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			list[i++] = node.getData();
			i = preOrder(list, (BSTNode<T>) node.getLeft(), i);
			i = preOrder(list, (BSTNode<T>) node.getRight(), i);
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] list = (T[]) new Comparable[this.size()];
		order(list, this.root, 0);
		return list;
	}

		
	private int order(T[] list, BSTNode<T> node, int i) {
		if(!node.isEmpty()) {
			i = order(list, (BSTNode<T>) node.getLeft(), i);
			list[i++] = node.getData();
			i = order(list, (BSTNode<T>) node.getRight(), i);
		}
		return i;
	}	

	@Override
	public T[] postOrder() {
		T[] list = (T[]) new Comparable[this.size()];
		postOrder(list, this.root, 0);
		return list;
	}

	private int postOrder(T[] list, BSTNode<T> node, int i) {
		if(!node.isEmpty()) {
			i = order(list, (BSTNode<T>) node.getLeft(), i);
			list[i++] = node.getData();
			i = order(list, (BSTNode<T>) node.getRight(), i);
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
