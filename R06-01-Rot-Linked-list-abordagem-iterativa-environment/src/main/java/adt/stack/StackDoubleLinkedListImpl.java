package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			top.insert(element);
		}
	}


	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}

		T element = this.top();
		top.removeLast();

		return element;
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) top).getLast().getData();
	}

	@Override
	public boolean isEmpty() {
		return (top.isEmpty());
	}

	@Override
	public boolean isFull() {
		return (top.size() == this.size);
	}

}
