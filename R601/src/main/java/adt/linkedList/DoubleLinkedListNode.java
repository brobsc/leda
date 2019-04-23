package adt.linkedList;

public class DoubleLinkedListNode<T> extends SingleLinkedListNode<T> {
	protected DoubleLinkedListNode<T> previous;
	protected DoubleLinkedListNode<T> next;

	public DoubleLinkedListNode() {
	}

	public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next,
			DoubleLinkedListNode<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	public DoubleLinkedListNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleLinkedListNode<T> previous) {
		this.previous = previous;
	}

	@Override
	public DoubleLinkedListNode<T> getNext() {
		return this.next;
	}

    public void setNext(DoubleLinkedListNode<T> next) {
	    this.next = next;
    }
}
