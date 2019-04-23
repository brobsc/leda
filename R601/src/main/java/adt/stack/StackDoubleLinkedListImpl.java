package adt.stack;

import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedListImpl<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		}

		this.list.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
        T top = this.top();

        if (top == null) {
        	throw new StackUnderflowException();
		}

		this.list.removeFirst();
		return top;
	}

	@Override
	public T top() {
		T result = null;

		if (!this.isEmpty()) {
			result = this.list.getHead().getData();
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
