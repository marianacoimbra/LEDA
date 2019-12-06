package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T extends Comparable<T>> extends SingleLinkedListImpl<T>
		implements OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public T minimum() {
		if (this.isEmpty()) {
			return null;
		} else {
			SingleLinkedListNode<T> aux = this.head;
			T menor = aux.getData();
			while(!aux.getNext().isNIL()) {
				if(aux.getData().compareTo(aux.getNext().getData()) > 0) {
					menor = aux.getNext().getData();
				}
				aux = aux.getNext();
			}
			return menor;
		}
	}

	@Override
	public T maximum() {
		if(this.isEmpty()) {
			return null;
		} else {
			SingleLinkedListNode<T> aux = this.head;
			T maior = aux.getData();
			while(!aux.getNext().isNIL()) {
				if(aux.getNext().getData().compareTo(aux.getData()) > 0) {
					maior = aux.getNext().getData();
				}
				aux = aux.getNext();
			}
			return maior;
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
}
