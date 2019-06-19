package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {
	@Override
	public void insert(T element) {
		super.insert(element);
		this.removeDuplicates();
	}

	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> current = this.getHead();

		while (!current.isNIL()) {
			SingleLinkedListNode<T> next = current.getNext();

			while (!next.isNIL()) {
				if (next.getData().equals(current.getData())) {
					next.setData(next.getNext().getData());
					next.setNext(next.getNext().getNext());
				} else {
					next = next.getNext();
				}
			}

			current = current.getNext();
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedList<T> result = new SetLinkedListImpl<>();

		result.concatenate(this);
		result.concatenate(otherSet);

		return result;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> current = this.getHead();
		SetLinkedList<T> result = new SetLinkedListImpl<>();

		while (!current.isNIL()) {
			if (otherSet.search(current.getData()) != null) {
				result.insert(current.getData());
			}

			current = current.getNext();
		}

		return result;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
	  T[] others = otherSet.toArray();

		for (T other: others) {
			this.insert(other);
		}

		this.removeDuplicates();
	}
}
