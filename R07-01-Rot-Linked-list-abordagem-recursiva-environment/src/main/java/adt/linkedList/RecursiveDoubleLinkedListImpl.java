package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			} else {
				RecursiveDoubleLinkedListImpl<T> newHead = new RecursiveDoubleLinkedListImpl<T>();
				newHead.setData(this.getData());
				newHead.setNext(this.getNext());
				newHead.setPrevious(this);
				
				this.setData(element);
				this.setNext(newHead);
				newHead.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>)newHead.getNext()).setPrevious(newHead);
				
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty() && this.getPrevious() == null) {
				this.setData(null);
				this.setPrevious(null);
				this.setNext(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
			}
		}

	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			this.removeFirst();
		} else {
			if (this.getNext().isEmpty()) {
				this.getPrevious().setNext(this.getNext());
			} else {
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {

			if (this.getData().equals(element)) {
				if (this.getPrevious() == null && this.getNext().isEmpty()) {
					this.removeFirst();
				} else {
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());

					if (this.getNext() != null) {
						((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
					}
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
