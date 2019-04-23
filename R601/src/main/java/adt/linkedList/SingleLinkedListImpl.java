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
		SingleLinkedListNode<T> current = this.getHead();
		T result = null;

		while (!current.isNIL() && result == null) {
			if (current.getData() == element) {
				result = element;
			}

			current = current.getNext();
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
		SingleLinkedListNode<T> prev = null;
		boolean removed = false;

		while(!current.isNIL() && !removed) {
			if (current.getData() == element) {
				if (prev == null) {
					this.setHead(current.getNext());
				} else {
					prev.setNext(current.getNext());
				}
				removed = true;
			}
			prev = current;
			current = current.getNext();
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> current = this.getHead();

		int i = 0;
		while(!current.isNIL()) {
			array[i] = current.getData();
			current = current.getNext();
			i++;
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
