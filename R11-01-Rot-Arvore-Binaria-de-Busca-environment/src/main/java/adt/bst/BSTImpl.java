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
		if (node.isEmpty()) {
			return 0;
		} else {
			return 1 + Math.max(this.height((BSTNode<T>) node.getRight()), this.height(((BSTNode<T>) node.getLeft())));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (element != null) {
			if (!node.isEmpty() && !element.equals(node.getData())) {
				if (element.compareTo(node.getData()) > 0) {
					return search((BSTNode<T>) node.getRight(), element);
				} else if (element.compareTo(node.getData()) < 0) {
					return search((BSTNode<T>) node.getLeft(), element);
				}
			}
		} else {
			return node;
		}
		return new BSTNode<T>();
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
			if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
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
		BSTNode<T> auxNode = this.search(element);
		if (auxNode.isEmpty())
			return null;
		BSTNode<T> answer = minimum((BSTNode<T>) auxNode.getRight());
		if (answer != null) {
			return answer;
		} else {
			answer = (BSTNode<T>) auxNode.getParent();
			while (answer != null && auxNode.getData().compareTo(answer.getData()) < 0) {
				answer = (BSTNode<T>) answer.getParent();
			}
			return answer;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if(node.isEmpty()) {
			return null;
		}
		BSTNode<T> answer = maximum((BSTNode<T>) node.getLeft());
		if(answer != null) {
			return answer;
		} else {
			answer = (BSTNode<T>) node.getParent();
			while(answer.isEmpty() && answer.getData().compareTo(node.getData()) > 0) {
				answer = (BSTNode<T>) answer.getParent();
			}
			return answer;
		}
	}

	@Override
	public void remove(T element) {
		if(element != null) {
			BSTNode<T> node = search(element);
			if(node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			}  else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				if (parent != null) {
					if(!node.getLeft().isEmpty()) {
						if(parent.getLeft().equals(node.getData())) {
							parent.setLeft(node.getLeft()); 
						} else if(parent.getRight().equals(node.getData())) {
							parent.setRight(node.getLeft());
						}  
						node.getLeft().setParent(parent);
					} else {
						if(!node.getRight().isEmpty()) {
							if(parent.getLeft().equals(node.getData())) {
								parent.setLeft(node.getRight()); 
							} else if(parent.getRight().equals(node.getData())) {
								parent.setRight(node.getRight());
							}  
							node.getRight().setParent(parent);
						}
					}
				}
			} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();
				T sucessor = sucessor(node.getData()).getData();
				if (parent != null) {
					remove(sucessor);
					node.setData(sucessor);
				}
			}
		}
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
