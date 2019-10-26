package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public BSTNode<T> recursiveSearch(T searched, BSTNode<T> node) {
		if (searched != null && !node.isEmpty()) {
			if (comparator.compare(searched, node.getData()) > 0) {
				return recursiveSearch(searched, (BSTNode<T>) node.getRight());
			} else if (comparator.compare(searched, node.getData()) < 0) {
				return recursiveSearch(searched, (BSTNode<T>) node.getLeft());
			} else {
				return node;
			}
		}
		return new BSTNode<T>();
	}

	@Override
	public void recursiveInsert(T element, BSTNode<T> node) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (comparator.compare(element, node.getData()) > 0) {
					recursiveInsert(element, (BSTNode<T>) node.getRight());
				} else if (comparator.compare(element, node.getData()) < 0) {
					recursiveInsert(element, (BSTNode<T>) node.getLeft());
				}
			}
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
			while (result != null && comparator.compare(result.getData(), node.getData()) < 0) {
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
		BSTNode<T> result = recursiveMaximum((BSTNode<T>) node.getLeft());
		if (result != null) {
			return result;
		} else {
			result = (BSTNode<T>) node.getParent();
			while (result != null && comparator.compare(result.getData(), node.getData()) > 0) {
				result = (BSTNode<T>) result.getParent();
			}
			return result;
		}
	}

	@Override
	public T[] sort(T[] array) {
		BSTNode<T> newOne = new BSTNode<T>();
		root = newOne;

		for (int i = 0; i < array.length; i++) {
			insert(array[i]);
		}
		T[] order = order();
		return order;
	}

	@Override
	public T[] reverseOrder() {
		T[] array = buildingArray();
		recursiveReverseOrder(array, 0, root);
		return array;
	}

	private int recursiveReverseOrder(T[] array, int i, BSTNode<T> node) {
		if (!node.isEmpty()) {
			i = recursiveReverseOrder(array, i, (BSTNode<T>) node.getRight());
			array[i++] = node.getData();
			i = recursiveReverseOrder(array, i, (BSTNode<T>) node.getLeft());
		}
		return i;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}