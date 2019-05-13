package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int size;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		this.size = size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		if (element != null) {
			if (head == -1) {
				head = 0;
			}

			tail = getIncTail();
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = array[getHead()];

		if (getIncHead() == getTail()) {
			head = -1;
			tail = -1;
		} else {
			head = getIncHead();
		}

		return result;
	}

	@Override
	public T head() {
	    T result = null;
	    if (!isEmpty()) {
	    	result = array[getHead()];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return getHead() == -1;
	}

	@Override
	public boolean isFull() {
		return getIncTail() == getHead();
	}

	private int getHead() {
		int result = -1;
		if (size > 0) {
			result = head % size;
		}
		return result;
	}

	private int getTail() {
		int result = -1;
		if (size > 0) {
			result = tail % size;
		}
		return result;
	}

	private int getIncTail() {
		int result = -1;
		if (size > 0) {
			result = (tail + 1) % size;
		}

		return result;
	}

	private int getIncHead() {
		int result = -1;
		if (size > 0) {
			result = (head + 1) % size;
		}

		return result;
	}
}
