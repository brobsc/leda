package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
		ArrayList<T> auxArray = new ArrayList<>();

		while (!isEmpty()) {
			T data = this.root.getData();
			auxArray.add(data);
			this.remove(data);
		}

		auxArray.addAll(Arrays.asList(array));
		auxArray.sort(Comparator.naturalOrder());

		int i = 0;
		int j = auxArray.size();
		int m = (i + j) / 2;

		while (m >= 0) {
		  this.insert(auxArray.get(m));

		}
	}
}
