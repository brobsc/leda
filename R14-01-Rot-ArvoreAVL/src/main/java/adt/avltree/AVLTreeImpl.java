package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}


	@Override
	public void remove(T element) {
		BSTNode<T> parentNode = (BSTNode<T>) search(element).getParent();
		super.remove(element);
		if (parentNode == null) {
		    rebalanceUp(root);
		} else {
			rebalanceUp(parentNode);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return result;
	}

	private int height(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			result += 2 + Math.max(height((BSTNode<T>) node.getLeft()),
					height((BSTNode<T>) node.getRight()));
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if (Math.abs(balance) > 1) {
			// Unbalanced tree
			if (balance < 0) {
				// Unbalanced to right
				BSTNode<T> rightChild = (BSTNode<T>) node.getRight();

				if (calculateBalance(rightChild) > 0) {
				  // Right child is pending left
					Util.rightRotation(rightChild);
				}
				Util.leftRotation(node);
			} else {
				// Unbalanced to left
				BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();

				if (calculateBalance(leftChild) < 0) {
					// Left child is pending right
					Util.leftRotation(leftChild);
				}
				Util.rightRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance(node);
			BSTNode<T> parent = ((BSTNode<T>) node.getParent());
			if (parent != null) {
				rebalanceUp(parent);
			} else {
				this.root = node;
			}
		}
	}
}
