package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}


	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isFull()) {
				throw new HashtableOverflowException();
			}

			int i = 0;
			boolean inserted = false;


			while (i < this.table.length && !inserted) {
				int hash = this.hash(element, i);
				if (this.table[hash] == null || this.table[hash] instanceof DELETED ||
						this.table[hash].equals(element)) {
					this.table[hash] = element;
					inserted = true;
					elements++;
				} else {
					COLLISIONS++;
				}
				i++;
			}
		}
	}

	@Override
	public void remove(T element) {
		int index = this.indexOf(element);

		if (index != -1) {
			this.table[index] = new DELETED();
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (this.indexOf(element) != -1) {
			result = element;
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int i = 0;
		int hash = this.hash(element, i);
		int result = -1;

		while (i < this.table.length && this.table[hash] != null && (this.table[hash] instanceof DELETED || !this.table[hash].equals(element))) {
			i++;
			hash = this.hash(element, i);
		}

		if (this.table[hash] != null && this.table[hash].equals(element)) {
			result = hash;
		}

		return result;
	}

	private int hash(T element, int probing) {
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probing);
	}
}
