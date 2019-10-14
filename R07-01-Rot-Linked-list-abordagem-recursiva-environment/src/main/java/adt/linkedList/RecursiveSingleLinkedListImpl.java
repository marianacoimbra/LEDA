package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return (this.getData() == null);
	}

	@Override
	public int size() {
		if (!isEmpty()) {
			return 1 + this.next.size();
		}
		return 0;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()) {
			if (this.getData().equals(element)) {
				result = this.data;
			} else {
				result = this.getNext().search(element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<T>());
		} else {
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.getData().equals(element)) {
				this.data = next.getData();
				this.setNext(getNext().getNext());
			} else {

				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> array = new ArrayList<>();
		return toArray(array);
	}

	private T[] toArray(ArrayList<T> array) {
		if (!isEmpty()) {
			array.add(getData());
			return this.getNext().toArray(array);
		}
		return (T[]) array.toArray();
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
