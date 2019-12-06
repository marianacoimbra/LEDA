package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		boolean answer = false;
		if (this.head.isNIL()) {
			answer = true;
		}
		return answer;
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.getNext().isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.head;
		if (this.isEmpty()) {
			return null;
		} else {
			while (!aux.getNext().isNIL()) {
				if (aux.getData().equals(element)) {
					return aux.getData();
				} else {
					aux = aux.getNext();
				}
			}
			return null;
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.head.isNIL()) {
				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
				this.head = newNode;
			}
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
			aux.setNext(newNode);
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.getNext().isNIL()) {
				if(aux.getNext().getData().equals(element)) {
					aux.setNext(aux.getNext().getNext());
				}
				aux = aux.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size()];
		
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.getNext().isNIL()) {
			array[i] = aux.getData();
			i++;
			aux = aux.getNext();
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
