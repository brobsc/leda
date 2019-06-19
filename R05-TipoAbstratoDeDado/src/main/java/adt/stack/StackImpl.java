package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		this.array = (T[]) new Object[size];
		this.top = -1;
	}

	@Override
	public T top() {
		T result = null;
		if (top >= 0) {
			result = array[top];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if ((top + 1) >= array.length) {
				throw new StackOverflowException();
			}
			top += 1;
			array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (top == -1) {
			throw new StackUnderflowException();
		}
		T result = array[top];
		top -= 1;
		return result;
	}
}
