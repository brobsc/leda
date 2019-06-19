package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> current = this.getHead();
		int size = 0;

		while (!current.isNIL()) {
			size++;
			current = current.getNext();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			SingleLinkedListNode<T> current = this.getHead();

			while (!current.isNIL() && result == null) {
				if (current.getData().equals(element)) {
					result = element;
				}

				current = current.getNext();
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
	  if (element != null) {
			SingleLinkedListNode<T> current = this.getHead();

			while (!current.isNIL()) {
				current = current.getNext();
			}

			current.setData(element);
			current.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> current = this.getHead();

		while (!current.isNIL()) {
		  if (current.getData().equals(element)) {
		  	current.setData(current.getNext().getData());
				current.setNext(current.getNext().getNext());
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = this.size();
		T[] arr = (T[]) new Object[size];

		SingleLinkedListNode<T> current = this.getHead();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = current.getData();
			current = current.getNext();
		}

		return arr;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
