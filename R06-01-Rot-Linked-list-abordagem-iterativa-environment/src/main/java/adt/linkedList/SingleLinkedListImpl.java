package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (head.isNIL()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!(aux.isNIL())) {
			size = size + 1;
			aux = aux.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		if (isEmpty()) {
			return null;
		} else {
			while (!(aux.isNIL()) && !(aux.getData() == element)) {
				aux = aux.next;
			}
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>();
			head.setData(element);
			head.setNext(newHead);
			;
		} else {
			while (!(aux.next.isNIL())) {
				aux = aux.next;
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>();
			newNode.setData(element);
			newNode.setNext(aux.getNext());
			aux.setNext(newNode);
		}
	}

	@Override
	public void remove(T element) {
		if (head.getData() == element) {
			head = head.next;
		} else {
			SingleLinkedListNode<T> aux = head;
			SingleLinkedListNode<T> previous = aux;
			while (!aux.isNIL() && aux.getData() != element) {
				previous = aux;
				aux = aux.next;
			}
			if (!aux.isNIL()) {
				previous.next = aux.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		int count = 0;
		T[] array = (T[]) new Comparable[size()];
		SingleLinkedListNode aux = head;
		while(!aux.isNIL()) {
			array[count] = (T) aux.getData();
			aux = aux.getNext();
			count++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
