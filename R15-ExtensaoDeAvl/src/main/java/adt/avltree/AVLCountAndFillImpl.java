package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.*;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
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
          this.RLcounter++;
        } else {
					this.RRcounter++;
				}
        Util.leftRotation(node);
      } else {
        // Unbalanced to left
        BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();

        if (calculateBalance(leftChild) < 0) {
          // Left child is pending right
          Util.leftRotation(leftChild);
					this.LRcounter++;
        } else {
					this.LLcounter++;
				}
        Util.rightRotation(node);
      }
    }
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		ArrayList<T> auxArray = new ArrayList<>(Arrays.asList(array));

		while (this.size() > 0) {
			List<T> leaves = new ArrayList<>();
			getLeavesOrder(this.root, leaves);

			for (T leave : leaves) {
				this.remove(leave);
				auxArray.add(leave);
			}
		}

		auxArray.sort(Comparator.naturalOrder());
		ArrayList<List<T>> inserter = new ArrayList<>();
		inserter.add(auxArray);
		fill(inserter);
	}

	private void fill(List<List<T>> array) {
		List<T> toInsert = array.remove(0);

		if (!toInsert.isEmpty()) {
			int m = toInsert.size() / 2;
			T el = toInsert.get(m);

			this.insert(el);

			array.add(toInsert.subList(0, m));
			array.add(toInsert.subList(m + 1, toInsert.size()));
			fill(array);
		}
	}

	private void getLeavesOrder(BSTNode<T> node, List<T> result) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result.add(node.getData());
			} else {
				getLeavesOrder((BSTNode<T>) node.getLeft(), result);
				getLeavesOrder((BSTNode<T>) node.getRight(), result);
			}
		}
	}
}
