package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			BSTNode<T> left = (BSTNode<T>) new BSTNode.Builder<T>()
					.parent(node)
					.build();

			BSTNode<T> right = (BSTNode<T>) new BSTNode.Builder<T>()
					.parent(node)
					.build();

			node.setLeft(left);
			node.setRight(right);
		} else {
			if (this.comparator.compare(element, node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public T[] sort(T[] array) {
		this.root = new BSTNode<>();

		for (T element : array) {
			this.insert(element);
		}

		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] arr = (T[]) new Comparable[this.size()];
		int i = 0;

		reverseOrder(root, arr, i);

		return arr;
	}

	private int reverseOrder(BSTNode<T> node, T[] arr, int i) {
		if (!node.isEmpty()) {
			i = reverseOrder((BSTNode<T>) node.getRight(), arr, i);
			arr[i] = node.getData();
			i += 1;
			i = reverseOrder((BSTNode<T>) node.getLeft(), arr, i);
		}
		return i;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
