package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + this.getNext().size();
		}
	}

	@Override
	public T search(T element) {
		T resposta = null;
		if (element != null) {
			if (!isEmpty()) {
				if (this.getData().equals(element)) {
					resposta = this.getData();
				} else {
					resposta = this.getNext().search(element);
				}
			}
		}
		return resposta;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.getData().equals(element)) {
				if (!this.getNext().isEmpty()) {
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());
				} else {
					this.setData(null);
					this.setNext(this.getNext().getNext());
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> array = new ArrayList<T>();
		
		toArray(array);
		return (T[]) array.toArray();
		
	}

	private void toArray(ArrayList<T> array) {
		if(!this.isEmpty()) {
			array.add(this.getData());
			this.getNext().toArray(array);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
