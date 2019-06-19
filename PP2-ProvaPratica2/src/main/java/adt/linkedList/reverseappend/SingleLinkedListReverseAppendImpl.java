package adt.linkedList.reverseappend;

import adt.linkedList.SingleLinkedListNode;

/**
 *
 * @see SingleLinkedListReverseAppend
 *
 * @author campelo
 *
 * @param <T>
 */
public class SingleLinkedListReverseAppendImpl<T> implements SingleLinkedListReverseAppend<T> {

	/*
	 * Nao remover esse atributo nem criar outros
	 */
	protected SingleLinkedListNode<T> head;

	/*
	 * Nao modifique o construtor
	 * @param head
	 */
	public SingleLinkedListReverseAppendImpl() {
		head = new SingleLinkedListNode<T>();
	}

	/* (non-Javadoc)
	 * @see adt.linkedList.reverseappend.SingleLinkedListReverseAppend#doIt(java.lang.Object)
	 *
	 * Implemente apenas este metodo de acordo com os comentários da interface.
	 *
	 */
	@Override
	public void doIt(T elem) {
		SingleLinkedListNode<T> current = this.head;

		// Invert
		while (!current.isNIL()) {
			SingleLinkedListNode<T> last = current.getNext();
			SingleLinkedListNode<T> aux = current.getNext();

			while (!aux.isNIL()) {
				last = aux;
				aux = aux.getNext();
			}

			T data = last.getData();

			if (data != null) {
				T currentData = current.getData();
				current.setData(data);

				SingleLinkedListNode<T> next = current.getNext();

				while (!next.isNIL()) {
					T old = next.getData();
					next.setData(currentData);
					currentData = old;
					next = next.getNext();
				}
			}

			current = current.getNext();
		}

		// Insert
		SingleLinkedListNode<T> oldHead = this.head;
		this.head = new SingleLinkedListNode<>(elem, oldHead);
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
     */
	@Override
    public String toString() {
    	String retorno = "";
    	SingleLinkedListNode<T> currentNode = this.head;
    	while (currentNode!=null) {
    		if (!retorno.equals("")) {
    			retorno += " ";
    		}
    		retorno += currentNode;
    		currentNode = currentNode.getNext();
    	}
		return retorno;
    }

}