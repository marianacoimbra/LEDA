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
		if(!this.isEmpty()) {
			return recursiveHeight(this.root);
		}
		return -1;
	}

	private int recursiveHeight(BSTNode<T> node) {
		if(node.isEmpty()) {
			return 0;
		}
		return 1 + Math.max(recursiveHeight((BSTNode<T>)node.getLeft()), recursiveHeight((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		if(!isEmpty()) { 
			return recursiveSearch(this.root, element);
		}
		return null;
	}

	private BSTNode<T> recursiveSearch(BSTNode<T> node, T element) {
		BSTNode <T> answer = null;
		if(element != null) {
			if(node.isEmpty() || node.getData().equals(element)) {
				answer =  node;
			} 
			if(node.getData().compareTo(element) < 0) {
				return recursiveSearch((BSTNode<T>)node.getRight(), element);
			} else {
				return recursiveSearch((BSTNode<T>)node.getLeft(), element);
			}
		}
		return answer;
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if(element != null) {
			if(node.isEmpty()) {	
				node.setData(element);
				node.setLeft(new BTNode<>());
				node.setRight(new BTNode<>());
			}
			if(element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> answer = null;
		if(!node.isEmpty()) {
			answer = maximum((BSTNode<T>)node.getRight()); 
		}
		return answer;
	}
	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> answer = null;
		if(!node.isEmpty()) {
			answer = minimum((BSTNode<T>)node.getLeft()); 
		}
		return answer;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
			+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
