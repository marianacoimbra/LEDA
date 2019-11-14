package adt.bst;

import java.lang.reflect.Array;
import java.util.Comparator;

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
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (element == null || node == null) {
			return null;
		} else {
			if (!node.getData().equals(element)) {
				if (node.getData().compareTo(element) > 0) {
					return search(element, (BSTNode<T>) node.getLeft());
				} else if (node.getData().compareTo(element) < 0) {
					return search(element, (BSTNode<T>) node.getRight());
				}
			}
			return node;
		}
	}

	@Override
	public void insert(T element) {
		insert(element, this.root);
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
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> aux = this.root;
		while (!aux.getRight().isEmpty()) {
			aux = (BSTNode<T>) aux.getRight();
		}
		return aux;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		BSTNode<T> aux = this.root;
		while (!node.getLeft().isEmpty()) {
			aux = (BSTNode<T>) node.getLeft();
		}
		return aux;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		BSTNode<T> answer = minimum((BSTNode<T>) node.getRight());
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
		if (node.isEmpty())
			return null;
		BSTNode<T> answer = maximum((BSTNode<T>) node.getLeft());
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
		if (node != null) {
			if (node.isLeaf()) {
				node.setData(null);
			} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getRight();
				swap(auxNode, node);
			} else if (node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
				swap(auxNode, node);
				BSTNode<T> sucessor = sucessor(node.getData());
				T aux = node.getData();
				node.setData(sucessor.getData());
				sucessor.setData(aux);
				remove(sucessor);
			}
		}
	}


	private void swap(BSTNode<T> auxNode, BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (node.getParent() != null) {
			if (parent.getLeft().getData().equals(node.getData())) {
				auxNode.setParent(parent);
				parent.setLeft(auxNode);
			} else if (parent.getRight().getData().equals(node.getData())) {
				auxNode.setParent(parent);
				parent.setRight(auxNode);
			}
		} else {
			this.root = auxNode;
			auxNode.setParent(null);
		}

	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, 0, array);
		return array;
	}

	private void preOrder(BSTNode<T> node, int i, T[] array) {
		if(!node.isEmpty()) {
			array[i] = node.getData();
			preOrder((BSTNode<T>) node.getLeft(), i + 1, array);
			preOrder((BSTNode<T>)node.getRight(), i + 1 + size((BSTNode<T>)node.getLeft()), array);
		}
	}


	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, 0, array);
		return array;
	}

	private int order(BSTNode<T> node, int i, T[] array) {
		if(!node.isEmpty()) {
			i = order((BSTNode<T>) node.getLeft(), i, array);
			array[i++] = node.getData();
			i = order((BSTNode<T>) node.getRight(), i, array);
		}
		return i;
	}

	@Override
	public T[] postOrder() {T[] array = (T[]) new Comparable[this.size()];

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
