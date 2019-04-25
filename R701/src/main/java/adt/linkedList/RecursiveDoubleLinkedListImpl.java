package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;
	protected RecursiveDoubleLinkedListImpl<T> next;

	public RecursiveDoubleLinkedListImpl() {
	}

	@Override
	public void insertFirst(T element) {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> oldHead = new RecursiveDoubleLinkedListImpl<>();
			oldHead.data = this.data;
			oldHead.next = this.next;
			oldHead.previous = this;

			this.data = element;
			this.next = oldHead;
		} else {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
		}

		this.previous = new RecursiveDoubleLinkedListImpl<>();
		this.previous.next = this;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
			this.next.previous = this;
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.data = this.next.data;
			this.next = this.next.next;
		}
	}

	@Override
	public void removeLast() {
		if (this.next.isEmpty()) {
			this.data = null;
			this.next = null;
		} else {
			this.next.removeLast();
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.previous.next = this.next;
				this.next.previous = this.previous;
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return this.next;
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
