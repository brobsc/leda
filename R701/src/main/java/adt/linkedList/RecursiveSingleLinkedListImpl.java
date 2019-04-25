package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int size = 0;

		if (!isEmpty()) {
			size = 1 + this.getNext().size();
		}

		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
	  if (!isEmpty()) {
	  	if (this.data.equals(element)) {
	  		result = element;
			} else {
	  		result = this.getNext().search(element);
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
	  if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
			} else {
			  this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
		  if (this.data.equals(element)) {
		  	this.data = this.next.data;
		  	this.next = this.next.next;
			} else {
		  	this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] a = (T[]) new Object[this.size()];

		return toArray(a, 0);
	}

	private T[] toArray(T[] a, int index) {
		if (!isEmpty()) {
			a[index] = this.data;
			a = this.getNext().toArray(a, index + 1);
		}

		return a;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
