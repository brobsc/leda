package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

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
		rebalance(node);
	}


	@Override
	public void remove(T element) {
		BSTNode<T> parentNode = (BSTNode<T>) search(element).getParent();
		super.remove(element);
		rebalanceUp(parentNode);
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
					rotateRight(rightChild);
				}
				rotateLeft(node);
			} else {
				// Unbalanced to left
				BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();

				if (calculateBalance(leftChild) < 0) {
					// Left child is pending right
					rotateLeft(leftChild);
				}
				rotateRight(node);
			}
		}
	}

	private void rotateRight(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> newLeft = (BSTNode<T>) left.getRight();
		BSTNode<T> oldRight = (BSTNode<T>) node.getRight();
		T data = node.getData();

		node.setData(left.getData());
		node.setLeft(left.getLeft());
		node.setRight((BSTNode<T>) new BSTNode.Builder<T>()
				.data(data)
				.right(oldRight)
				.left(newLeft)
				.parent(node)
				.build());
	}

	private void rotateLeft(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		BSTNode<T> newRight = (BSTNode<T>) right.getLeft();
		BSTNode<T> oldLeft = (BSTNode<T>) node.getLeft();
		T data = node.getData();

		node.setData(right.getData());
		node.setRight(right.getRight());
		node.setLeft((BSTNode<T>) new BSTNode.Builder<T>()
				.data(data)
				.right(newRight)
				.left(oldLeft)
				.parent(node)
				.build());
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = ((BSTNode<T>) node.getParent());
			rebalance(node);
			if (parent != null) {
				rebalanceUp(parent);
			}
		}
	}
}
