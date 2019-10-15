package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		T saida = this.top();
		this.top.removeLast();
		return saida;
	}

	@Override
	public T top() {
		T[] array = this.top.toArray();
		T saida = null;
		if (array.length > 0) {
			saida = array[array.length - 1];
		}
		return saida;
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (this.top.isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	@Override
	public boolean isFull() {
		boolean isFull = false;
		if (this.top.size() == this.size) {
			isFull = true;
		}
		return isFull;
	}
}
