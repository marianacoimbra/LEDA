package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if(isEmpty())  {
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
		if(!this.isEmpty()) {
			this.setHead(head.getNext());
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
			if(this.size() == 1) {
				this.setLast((DoubleLinkedListNode<T>) getHead());
			}
		} 
	}

	@Override
	public void removeLast() {
		
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
