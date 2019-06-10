package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeight(this.getRbRoot());
   }

   private int blackHeight(RBNode<T> node) {
      int result = 0;

      if (!node.isEmpty()) {
         if (node.getColour() == Colour.BLACK) {
            result++;
         }

         result += Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
      }

      return result;
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes(getRbRoot());
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean valid = true;

      if (!node.isEmpty()) {
         RBNode<T> left = ((RBNode<T>) node.getLeft());
         RBNode<T> right = ((RBNode<T>) node.getLeft());

         if (node.getColour() == Colour.RED) {
            valid = left.getColour() == Colour.BLACK && right.getColour() == Colour.BLACK;
         }

         if (valid) {
            valid = verifyChildrenOfRedNodes(left) && verifyChildrenOfRedNodes(right);
         }
      }

      return valid;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return verifyBlackHeight(getRbRoot());
   }

   private boolean verifyBlackHeight(RBNode<T> node) {
      boolean valid = true;
      if (!node.isEmpty()) {
         RBNode<T> left = ((RBNode<T>) node.getLeft());
         RBNode<T> right = ((RBNode<T>) node.getLeft());

         valid = blackHeight(left) == blackHeight(right);

         if (valid) {
            valid = verifyBlackHeight(left) && verifyBlackHeight(right);
         }
      }

      return valid;
   }

   @Override
   public void insert(T value) {
      if (value != null) {
         insert(this.getRbRoot(), value);
      }
   }

   private void insert(RBNode<T> node, T value) {
      if (node.isEmpty()) {
         node.setData(value);
         node.setColour(Colour.RED);

         RBNode<T> left = new RBNode<>();
         left.setParent(node);
         left.setColour(Colour.BLACK);

         RBNode<T> right = new RBNode<>();
         right.setParent(node);
         right.setColour(Colour.BLACK);

         node.setLeft(left);
         node.setRight(right);

         fixUpCase1(node);
      } else {
         if (value.compareTo(node.getData()) > 0) {
            insert((RBNode<T>) node.getRight(), value);
         } else {
            insert((RBNode<T>) node.getLeft(), value);
         }
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] result = (RBNode<T>[]) new RBNode[this.size()];

      rbPreOrder(result, getRbRoot(), 0);

      return result;
   }

   private int rbPreOrder(RBNode<T>[] array, RBNode<T> node, int i) {
      if (!node.isEmpty()) {
         array[i] = node;
         i++;
         i = rbPreOrder(array, (RBNode<T>) node.getLeft(), i);
         i = rbPreOrder(array, (RBNode<T>) node.getRight(), i);
      }

      return i;
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.equals(getRbRoot())) {
         node.setColour(Colour.BLACK);
      } else {
         fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
         fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> parent = ((RBNode<T>) node.getParent());
      RBNode<T> grandParent = ((RBNode<T>) parent.getParent());
      RBNode<T> uncle;

      if (parent.getData().compareTo(grandParent.getData()) < 0) {
         // parent is left child of gp
         uncle = ((RBNode<T>) grandParent.getRight());
      } else {
         // parent is right child
         uncle = ((RBNode<T>) grandParent.getLeft());
      }

      if (uncle.getColour() == Colour.RED) {
         uncle.setColour(Colour.BLACK);
         parent.setColour(Colour.BLACK);
         grandParent.setColour(Colour.RED);
         fixUpCase1(grandParent);
      } else {
         fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> next = node;
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();

      if (node.getData().compareTo(parent.getData()) < 0 && parent.getData().compareTo(grandParent.getData()) > 0) {
         // node is left child and parent is right child
         Util.leftRotation(parent);
         next = parent;
      } else if (node.getData().compareTo(parent.getData()) > 0
            && parent.getData().compareTo(grandParent.getData()) < 0) {
         // node is right child and parent is left child
         Util.rightRotation(parent);
         next = parent;
      }

      fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandParent = (RBNode<T>) parent.getParent();

      parent.setColour(Colour.BLACK);
      grandParent.setColour(Colour.RED);

      if (node.getData().compareTo(parent.getData()) < 0) {
         // node is left child
         Util.rightRotation(grandParent);
      } else {
         Util.leftRotation(grandParent);
      }
   }

   private RBNode<T> getRbRoot() {
      return (RBNode<T>) this.root;
   }
}
