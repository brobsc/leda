package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> head;
	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = this.head;
	}

	@Override
	public void insert(T element) {
	    if (element != null) {
	        DoubleLinkedListNode<T> inserted = new DoubleLinkedListNode<>(element, null, null);
	        inserted.setNext(new DoubleLinkedListNode<>(null, null, inserted));

	    	if (isEmpty()) {
				this.setHead(inserted);
			} else {
				this.getLast().setNext(inserted);
				inserted.setPrevious(this.getLast());
			}

			this.setLast(inserted);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			DoubleLinkedListNode<T> current = this.getHead();
			boolean removed = false;

			while (!current.isNIL() && !removed) {
				if (current.getData().equals(element)) {
					DoubleLinkedListNode<T> prev = current.getPrevious();
					DoubleLinkedListNode<T> next = current.getNext();

					prev.setNext(next);
					next.setPrevious(prev);
					removed = true;
				}

				current = current.getNext();
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.insert(element);
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, null, null);

				DoubleLinkedListNode<T> oldHead = this.getHead();
				newHead.setNext(oldHead);
				oldHead.setPrevious(newHead);

				this.setHead(newHead);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.setHead(this.getHead().getNext());
			this.getHead().setPrevious(null);
		}
	}

	@Override
	public void removeLast() {
	    if (!isEmpty()) {
	    	DoubleLinkedListNode<T> oldLast = this.getLast();
	    	oldLast.getPrevious().setNext(new DoubleLinkedListNode<>(null, null, oldLast.getPrevious()));
		}
	}

	private DoubleLinkedListNode<T> getLast() {
		return last;
	}

	private void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public DoubleLinkedListNode<T> getHead() {
		return this.head;
	}

	private void setHead(DoubleLinkedListNode<T> head) {
		this.head = head;
	}
}
