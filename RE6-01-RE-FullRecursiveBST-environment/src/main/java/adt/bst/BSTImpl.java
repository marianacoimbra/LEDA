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

	private int height(BSTNode<T> node) {
		if (this.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getLeft()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (!node.isEmpty() && element != null) {
			if (node.getData().equals(element)) {
				return node;
			} else if (node.getData().compareTo(element) > 0) {
				return search(element, (BSTNode<T>) node.getLeft());
			} else if (node.getData().compareTo(element) < 0) {
				return search(element, (BSTNode<T>) node.getRight());
			}
		}
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		insert(element, this.root);
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
				insert(element, (BSTNode<T>) node.getRight());
			} else if (element.compareTo(node.getData()) < 0) {
				insert(element, (BSTNode<T>) node.getLeft());
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
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
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
		BSTNode<T> answer = (BSTNode<T>) minimum(node).getRight();
		if (answer != null) {
			return answer;
		} else {
			answer = (BSTNode<T>) node.getParent();
			while (answer != null && answer.getData().compareTo(node.getData()) < 0) {
				answer = (BSTNode<T>) answer.getParent();
			}
			return answer;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> answer = this.maximum((BSTNode<T>) node.getLeft());
		if (answer != null) {
			return answer;
		} else {
			answer = (BSTNode<T>) node.getParent();
			while (answer != null && answer.getData().compareTo(node.getData()) > 0) {
				answer = (BSTNode<T>) answer.getParent();
			}
			return answer;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = this.search(element);
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if(!node.isEmpty()) {
			if(node.isLeaf()) {
				node.setData(null);
			} else if(!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
				BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
				swap(node, rightNode);
			} else if(!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
				swap(node, leftNode);
			} else {
				BSTNode<T> sucessor = this.sucessor(node.getData());
				T aux = node.getData();
				node.setData(sucessor.getData());
				sucessor.setData(aux);
				this.remove(sucessor);
			}
		}
	}

	private void swap(BSTNode<T> node, BSTNode<T> aux) {
		if(node.getParent() == null) {
			aux.setParent(null);
			this.root = aux;
		} else if(node.getParent().getLeft().equals(node)) {
			node.getParent().setLeft(aux);
		} else if(node.getParent().getRight().equals(node)) {
			node.getParent().setRight(aux);
		}
		aux.setParent(node.getParent());
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(array, 0, this.root);
		return array;
	}

	private int preOrder(T[] array, int i, BSTNode<T> node) {
		if(!node.isEmpty()) {
			array[i++] = node.getData();
			i = preOrder(array, i, (BSTNode<T>) node.getLeft());
			i = preOrder(array, i, (BSTNode<T>) node.getRight());
		}
		return i;
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
