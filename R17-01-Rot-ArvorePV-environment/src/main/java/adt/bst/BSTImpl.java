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
		if(element != null) {
			if(node.isEmpty()) {
				return null;
			}if(node.getData().equals(element)) {
				return node; 
			}else if(element.compareTo(node.getData()) > 0) {
				return search(element, (BSTNode<T>) node.getRight());
			} else if(element.compareTo(node.getData()) < 0) {
				return search(element, (BSTNode<T>) node.getLeft());
			} else {
				return new BSTNode<T>();
			}
		}
		return null; 
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
			} else if (element.compareTo(node.getData()) > 0) {
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
		if (node.isEmpty()) {
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
			BSTNode<T> node = this.search(element);

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
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element != null) {
			BSTNode<T> node = this.search(element);

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
			} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
				swap(auxNode, node);
			} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> auxNode = (BSTNode<T>) node.getRight();
				swap(auxNode, node);
			} else {
				BSTNode<T> auxNode = minimum((BSTNode<T>) node.getRight());

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
		} else {
			if (node.getParent().getRight().getData().equals(node.getData())) {
				node.getParent().setRight(auxNode);
			} else {
				node.getParent().setLeft(auxNode);
			}
			auxNode.setParent(node.getParent());
		}
	}

	@Override
	public T[] preOrder() {
		T[] list = (T[]) new Comparable[this.size()] ;
		preOrderRecursivo(list, this.root, 0); 
		return list ;
	}

	private int preOrderRecursivo(T[] list, BSTNode<T> node, int indice) {
		if(node.isEmpty()) {
			return indice;
		}
		list[indice] = node.getData();
		indice ++;
		indice = preOrderRecursivo(list, (BSTNode<T>) node.getLeft(), indice );
		indice = preOrderRecursivo(list, (BSTNode<T>) node.getRight(), indice);
		return indice;
	}

	@Override
	public T[] order() {
		T[] list = (T[]) new Comparable[this.size()];
		InorderRecursivo (list, this.root, 0);
		return list;
		
	}

	private int InorderRecursivo(T[] list, BSTNode<T> node, int i) {
		if(node.isEmpty()) {
			return i;
		}
		i = InorderRecursivo(list, (BSTNode<T>) node.getLeft(), i);
		list[i] = node.getData();
		i++;
		i = InorderRecursivo(list, (BSTNode<T>) node.getRight(), i);
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] list = (T[]) new Comparable[this.size()];
		postOrderRecursivo (list, this.root, 0);
		return list;
	}

	private int postOrderRecursivo(T[] list, BSTNode<T> node, int i) {
		if (node.isEmpty()) {
			return i;
		} 
		i = postOrderRecursivo(list, (BSTNode<T>) node.getLeft(), i);
		i = postOrderRecursivo(list, (BSTNode<T>) node.getRight(),i);
		list[i] = node.getData();
		i++;
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
