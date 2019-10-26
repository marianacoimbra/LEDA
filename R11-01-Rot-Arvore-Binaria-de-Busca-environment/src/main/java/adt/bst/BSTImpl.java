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
		if(node.isEmpty()) {
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
		if(element != null) {
			if(!node.isEmpty() || !node.equals(element)) {
				if(element.compareTo(node.getData()) > 0) {
					answer = search((BSTNode<T>)node.getRight(), element);
				} else {
					answer = search((BSTNode<T>)node.getRight(), element);
				}
			}
			return answer;
		}
		//If the key does not exist the methods returns a NIL (empty) node.
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		insert(element, this.root);
	}

	private void insert(T element, BSTNode<T> node) {
		if(element != null) {
			if(node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BTNode<T>());
				node.setRight(new BTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if(element.compareTo(node.getData()) > 0) {
					insert(element,(BSTNode<T>) node.getRight());
				} else if(element.compareTo(node.getData()) < 0) {
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
		if(isEmpty()) {
			return null;
		} else if(node.getRight().isEmpty()) {
				return node;
		} else {
			return maximum((BSTNode<T>)node.getRight());
			
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.isEmpty()) {
			return null;
		} else if(node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>)node.getLeft());
		}
	}
	
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if(node.isEmpty()) {
			return null;
		} 
		BSTNode<T> answer = minimum((BSTNode<T>) node.getRight());
		if(answer != null) {
			
		}
		
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
