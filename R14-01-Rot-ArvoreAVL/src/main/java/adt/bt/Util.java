package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = ((BSTNode<T>) node.getParent());
		BSTNode<T> result = ((BSTNode<T>) node.getRight());
		BSTNode<T> oldLeft = ((BSTNode<T>) result.getLeft());

		result.setLeft(node);
		node.setParent(result);
		node.setRight(oldLeft);

		changeParents(node, result, oldLeft, parent);

		return result;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = ((BSTNode<T>) node.getParent());
		BSTNode<T> result = ((BSTNode<T>) node.getLeft());
		BSTNode<T> oldRight = ((BSTNode<T>) result.getRight());

		result.setRight(node);
		node.setParent(result);
		node.setLeft(oldRight);

		changeParents(node, result, oldRight, parent);

		return result;
	}

	private static <T extends Comparable<T>> void changeParents(BSTNode<T> node, BSTNode<T> replacement,
														 BSTNode<T> oldChild, BSTNode<T> parent) {
		if (oldChild != null) {
			oldChild.setParent(node);
		}

		if (parent != null) {
			if (parent.getLeft().equals(node)) {
				parent.setLeft(replacement);
			} else {
				parent.setRight(replacement);
			}
		}

		replacement.setParent(parent);
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
