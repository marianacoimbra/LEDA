package adt.bst;

import adt.bt.BTNode;

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
		int answer = -1;
		if (node.isEmpty()) {
			return answer;
		} else {
			answer = 1 + Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight()));
		}
		return answer;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> answer = node;
		if (element != null) {
			if (!node.isEmpty() || !node.equals(element)) {
				if (element.compareTo(node.getData()) > 0) {
					answer = search((BSTNode<T>) node.getRight(), element);
				} else {
					answer = search((BSTNode<T>) node.getRight(), element);
				}
			}
			return answer;
		}
		// If the key does not exist the methods returns a NIL (empty) node.
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BTNode<T>());
				node.setRight(new BTNode<T>());
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
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (isEmpty()) {
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
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> answer = (BSTNode<T>) this.minimum(node).getRight();
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
		BSTNode<T> answer = (BSTNode<T>) this.minimum(node).getRight();
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
			BSTNode<T> node = search(element);
			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
		} else {
			if (!node.getParent().isEmpty()) {
				if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
					BSTNode<T> aux = (BSTNode<T>) node.getParent();
					if (aux.getLeft().equals(node)) {
						aux.setLeft(node.getLeft());
						node.getLeft().setParent(aux);
					} else if (aux.getRight().equals(node)) {
						aux.setLeft(node.getRight());
						node.getLeft().setParent(aux);
					}
				}
			} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> nodeSucessor = (BSTNode<T>) this.sucessor(node.getData());
				BSTNode<T> nodeParent = (BSTNode<T>) node.getParent();
				if (nodeParent.getLeft().equals(node)) {
					nodeParent.setLeft(nodeSucessor);
					nodeSucessor.setParent(nodeParent);
				} else if (nodeParent.getRight().equals(node)) {
					nodeParent.setRight(nodeSucessor);
					nodeSucessor.setParent(nodeParent);
				}

			} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> nodeSucessor = (BSTNode<T>) this.sucessor(node.getData());
				BSTNode<T> nodeParent = (BSTNode<T>) node.getParent();

				if (nodeParent.getLeft().equals(node)) {
					node.setData(nodeSucessor.getData());
					nodeSucessor.getParent().getLeft().setData(null);
					this.remove(nodeSucessor);

				} else if (nodeParent.getRight().equals(node)) {
					node.setData(nodeSucessor.getData());
					nodeSucessor.getParent().setLeft(null);
					this.remove(nodeSucessor);
				}
			}

		}

		T sucessor = sucessor(node.getData()).getData();
		remove(sucessor);
		node.setData(sucessor);
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		recursivePreOrder(this.root, 0, array);
		return array;
	}

	private void recursivePreOrder(BSTNode<T> node, int index,T[] array) {
		if(!node.isEmpty()) {
			
			array[index] = node.getData();
			recursivePreOrder((BSTNode<T>) node.getLeft(), index + 1, array);
			recursivePreOrder((BSTNode<T>) node.getRight(), index + 1 + size((BSTNode<T>)node.getLeft()), array);
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];

		order(this.root, 0, array);

		return array;
	}

	private int order (BSTNode<T> node, int index, T[] array) {
		if (!node.isEmpty()) {
			index = order((BSTNode<T>) node.getLeft(), index, array);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), index, array);
		}

		return index;
	}


	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		postOrder(this.root, 0, array);

		return array;
	}

	private int postOrder(BSTNode<T> node, int index, T[] array) {
		if (!node.isEmpty()) {
			index = postOrder((BSTNode<T>) node.getLeft(), index, array);
			index = postOrder((BSTNode<T>) node.getRight(), index, array);
			array[index++] = node.getData();
		}

		return index;
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
