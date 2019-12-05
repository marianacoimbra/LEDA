package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balance = 0;
		if (!node.isEmpty() && node != null) {
			balance = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				rightRotation(node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				rightRotation(node);
			} else {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			}
		}

	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		if(this.getRoot() == node) {
			this.root = pivot;
		}
		Util.leftRotation(node);
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		if(this.getRoot() == node) {
			this.root = pivot;
		}
		Util.rightRotation(node);
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if(node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while(parent != null) {
				this.rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
		} else {
			if (node.getData().compareTo(element) < 0) {
				this.insert((BSTNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				this.insert((BSTNode<T>) node.getLeft(), element);
			}

			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		remove(node);
	}

	@Override
	protected BSTNode<T> remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				rebalanceUp(node);
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
			return node;
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
		rebalance(auxNode);
	}

}