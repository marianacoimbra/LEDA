package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(isEmpty()) {
			this.setData(element);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
		} else {
			RecursiveDoubleLinkedListImpl<T> newHead = new RecursiveDoubleLinkedListImpl<T>();
			newHead.setData(element);
			newHead.setNext(this);
			this.setPrevious(newHead);
			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
			newHead.setPrevious(newNode);
			newNode.setNext(newNode);

		}
	}

	@Override
	public void removeFirst() {
		if(isEmpty()) {
			this.setData(null);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		} else {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
			((RecursiveDoubleLinkedListImpl<T>)this.getNext()).setPrevious(this);
		}
	}

	@Override
	public void removeLast() {
		if(isEmpty()) {
			this.removeFirst();
		} else {
			if(this.getNext().isEmpty()) {
				this.getPrevious().setNext(this.getNext());
			} else {
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}
	@Override
	public void insert(T element) {

	}

	@Override 
	public void remove(T element) {

	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
