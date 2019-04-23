package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedListImpl<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}

		this.list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = this.head();

		if (result == null) {
			throw new QueueUnderflowException();
		}

		this.list.removeFirst();
		return result;
	}

	@Override
	public T head() {
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
