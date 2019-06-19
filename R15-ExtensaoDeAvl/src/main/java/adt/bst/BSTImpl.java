package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

  protected BSTNode<T> root;

  public BSTImpl() {
    root = new BSTNode<T>();
  }

  public BSTNode<T> getRoot() {
    return this.root;
  }

  @Override
  public boolean isEmpty() {
    return root.isEmpty();
  }

  @Override
  public int height() {
    return height(root);
  }

  private int height(BSTNode<T> node) {
    int result = -1;
    if (!node.isEmpty()) {
      result += 2 + Math.max(height((BSTNode<T>) node.getLeft()),
          height((BSTNode<T>) node.getRight()));
    }
    return result;
  }

  @Override
  public BSTNode<T> search(T element) {
    BSTNode<T> result = (BSTNode<T>) new BSTNode.Builder<T>().build();
    if (element != null) {
      result = search(root, element);
    }
    return result;
  }

  private BSTNode<T> search(BSTNode<T> node, T element) {
    BSTNode<T> result = (BSTNode<T>) new BSTNode.Builder<T>().build();
    if (!node.isEmpty()) {
      int comparison = node.getData().compareTo(element);
      if (comparison == 0) {
        result = node;
      } else if (comparison < 0) {
        result = search((BSTNode<T>) node.getRight(), element);
      } else {
        result = search((BSTNode<T>) node.getLeft(), element);
      }
    }
    return result;
  }


  @Override
  public void insert(T element) {
    if (element != null) {
      insert(root, element);
    }
  }

  private void insert(BSTNode<T> node, T element) {
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
      if (element.compareTo(node.getData()) > 0) {
        insert((BSTNode<T>) node.getRight(), element);
      } else {
        insert((BSTNode<T>) node.getLeft(), element);
      }
    }
  }

  @Override
  public BSTNode<T> maximum() {
    BSTNode<T> result = null;
    if (!isEmpty()) {
      result = maximum(root);
    }
    return result;
  }

  private BSTNode<T> maximum(BSTNode<T> node) {
    BSTNode<T> result = node;
    if (!node.getRight().isEmpty()) {
      result = maximum((BSTNode<T>) node.getRight());
    }
    return result;
  }

  @Override
  public BSTNode<T> minimum() {
    BSTNode<T> result = null;
    if (!isEmpty()) {
      result = minimum(root);
    }
    return result;
  }

  private BSTNode<T> minimum(BSTNode<T> node) {
    BSTNode<T> result = node;
    if (!node.getLeft().isEmpty()) {
      result = minimum((BSTNode<T>) node.getLeft());
    }
    return result;
  }

  @Override
  public BSTNode<T> sucessor(T element) {
    BSTNode<T> result = null;
    if (element != null) {
      BSTNode<T> node = search(element);
      result = sucessor(node);
    }
    return result;
  }

  private BSTNode<T> sucessor(BSTNode<T> node) {
    BSTNode<T> result = null;
    if (!node.isEmpty()) {
      if (!node.getRight().isEmpty()) {
        result = minimum((BSTNode<T>) node.getRight());
      } else {
        BSTNode<T> parent = (BSTNode<T>) node.getParent();

        while (parent != null && result == null) {
          if (parent.getData().compareTo(node.getData()) > 0) {
            result = parent;
          }
          parent = (BSTNode<T>) parent.getParent();
        }
      }
    }
    return result;
  }

  @Override
  public BSTNode<T> predecessor(T element) {
    BSTNode<T> result = null;
    if (element != null) {
      BSTNode<T> node = search(element);
      result = predecessor(node);
    }
    return result;
  }

  private BSTNode<T> predecessor(BSTNode<T> node) {
    BSTNode<T> result = null;
    if (!node.isEmpty()) {
      if (!node.getLeft().isEmpty()) {
        result = maximum((BSTNode<T>) node.getLeft());
      } else {
        BSTNode<T> parent = (BSTNode<T>) node.getParent();

        while (parent != null && result == null) {
          if (parent.getData().compareTo(node.getData()) < 0) {
            result = parent;
          }
          parent = (BSTNode<T>) parent.getParent();
        }
      }
    }
    return result;
  }

  @Override
  public void remove(T element) {
    if (element != null) {
      BSTNode<T> node = search(element);
      remove(node);
    }
  }

  private void remove(BSTNode<T> node) {
    if (!node.isEmpty()) {
      if (node.isLeaf()) {
        node.setData(null);
      } else {
        BSTNode<T> replacement;
        if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
          replacement = sucessor(node);
          node.setData(replacement.getData());
          remove(replacement);
        } else {
          if (node.getRight().isEmpty()) {
            replacement = (BSTNode<T>) node.getLeft();
          } else {
            replacement = (BSTNode<T>) node.getRight();
          }
          node.setData(replacement.getData());
          node.setRight(replacement.getRight());
          node.setLeft(replacement.getLeft());
        }
      }
    }
  }

  @Override
  public T[] preOrder() {
    T[] arr = (T[]) new Comparable[this.size()];
    int i = 0;

    preOrder(root, arr, i);

    return arr;
  }

  private int preOrder(BSTNode<T> node, T[] arr, int i) {
    if (!node.isEmpty()) {
      arr[i] = node.getData();
      i += 1;
      i = preOrder((BSTNode<T>) node.getLeft(), arr, i);
      i = preOrder((BSTNode<T>) node.getRight(), arr, i);
    }
    return i;
  }

  @Override
  public T[] order() {
    T[] arr = (T[]) new Comparable[this.size()];
    int i = 0;

    order(root, arr, i);

    return arr;
  }

  private int order(BSTNode<T> node, T[] arr, int i) {
    if (!node.isEmpty()) {
      i = order((BSTNode<T>) node.getLeft(), arr, i);
      arr[i] = node.getData();
      i += 1;
      i = order((BSTNode<T>) node.getRight(), arr, i);
    }
    return i;
  }

  @Override
  public T[] postOrder() {
    T[] arr = (T[]) new Comparable[this.size()];
    int i = 0;

    postOrder(root, arr, i);

    return arr;
  }

  private int postOrder(BSTNode<T> node, T[] arr, int i) {
    if (!node.isEmpty()) {
      i = postOrder((BSTNode<T>) node.getLeft(), arr, i);
      i = postOrder((BSTNode<T>) node.getRight(), arr, i);
      arr[i] = node.getData();
      i += 1;
    }
    return i;
  }

  /**
   * This method is already implemented using recursion. You must understand
   * how it work and use similar idea with the other methods.
   */
  @Override
  public int size() {
    return size(root);
  }

  private int size(BSTNode<T> node) {
    int result = 0;
    // base case means doing nothing (return 0)
    if (!node.isEmpty()) { // indusctive case
      result = 1 + size((BSTNode<T>) node.getLeft())
          + size((BSTNode<T>) node.getRight());
    }
    return result;
  }

}
