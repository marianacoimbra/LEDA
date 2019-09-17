package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(isEmpty()) {
			return null;
		}
		return this.array[top];
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (this.top == -1) {
			isEmpty = true;
		}
		return isEmpty;
	}

	@Override
	public boolean isFull() {
		boolean isFull = false;
		if (this.top == array.length - 1) {
			isFull = true;
		}
		return isFull;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else {
			this.top++;
			this.array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T aux;
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			aux = array[top];
			this.top --;
		}
		return aux;
	}
}
