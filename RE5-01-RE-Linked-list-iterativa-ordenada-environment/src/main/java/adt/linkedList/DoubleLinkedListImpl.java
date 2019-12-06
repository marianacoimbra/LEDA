package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			this.setHead(newNode);
			this.setLast(newNode);
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
		} else {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			((DoubleLinkedListNode<T>) this.head).setPrevious(newNode);
			newNode.setNext(head);
			this.setHead(newNode);
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.setHead(head.getNext());
		} else if (this.head.isNIL()) {
			this.last = (DoubleLinkedListNode<T>) head;
		}
		((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			this.setLast(this.getLast().getPrevious());
			this.getLast().setNext(new DoubleLinkedListNode<>());
		}
		if (this.getLast().isNIL()) {
			head = last;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.getHead().equals(element)) {
					this.removeFirst();
				} else if (this.getLast().equals(element)) {
					this.removeLast();
				} else {
					DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
					while (!aux.next.isNIL()) {
						if (aux.getNext().getData().equals(element)) {
							aux.setNext(aux.getNext().getNext());
							((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux);
						}
					}
				}
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
			newNode.setData(element);
			if(!this.isEmpty()) {
				this.getLast().setNext(newNode);
				newNode.setPrevious(last);
				this.setLast(newNode);
			} else {
				this.setHead(newNode);
				newNode.setNext(new DoubleLinkedListNode<>());
				newNode.setPrevious(new DoubleLinkedListNode<>());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
