package adt.btree;

import java.util.LinkedList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int result = -1;

		if (!node.isEmpty()) {
			result++;

			if (!node.isLeaf()) {
				result += height(node.getChildren().get(0));
			}
		}

		return result;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] result = (BNode<T>[]) new BNode[nodeCount(this.getRoot())];

		depthLeftOrder(result, this.getRoot(), 0);

		return result;
	}

	private int nodeCount(BNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result++;
			for (BNode<T> child : node.getChildren()) {
				result += nodeCount(child);
			}
		}
		return result;
	}

	private int depthLeftOrder(BNode<T>[] result, BNode<T> node, int i) {
		if (!node.isEmpty()) {
			result[i] = node;
			i++;
			for (BNode<T> child : node.getChildren()) {
				i = depthLeftOrder(result, child, i);
			}
		}

		return i;
	}

	@Override
	public int size() {
	    return size(this.getRoot());
	}

	private int size(BNode<T> node) {
		int size = 0;

		if (!node.isEmpty()) {
			size += node.size();

			for (BNode<T> child : node.getChildren()) {
				size += size(child);
			}
		}

		return size;
	}


	@Override
	public BNodePosition<T> search(T element) {
		BNodePosition<T> result = null;
		if (element != null) {
			result = search(element, this.getRoot());
		}
		return result;
	}

	private BNodePosition<T> search(T element, BNode<T> node) {
		BNodePosition<T> result = null;
		if (!node.isEmpty()) {
			int index = findIndex(node, element);

			if (index < node.size()) {
				if (node.getElementAt(index).equals(element)) {
					result = new BNodePosition<>(node, index);
				}
			} else {
				result = search(element, node.getChildren().get(index));
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			BNode<T> node = getRoot();
			insert(element, node);
		}
	}

	private void insert(T element, BNode<T> node) {
		if (node.isFull()) {
			split(node);
			node = node.getParent();
		}

		if (node.isLeaf()) {
			node.addElement(element);
		} else {
			int index = findIndex(node, element);
			node = node.getChildren().get(index);
			insert(element, node);
		}
	}

	private void split(BNode<T> node) {
		promote(node);

		LinkedList<T> elements = node.getElements();
		int median = elements.size() / 2;
		BNode<T> parent = node.getParent();

		parent.removeChild(node);
		createChild(parent, elements.subList(0, median));
		createChild(parent, elements.subList(median, elements.size()));
	}

	private void createChild(BNode<T> parent, List<T> elements) {
		BNode<T> child = new BNode<>(this.order);

		child.setElements(new LinkedList<>(elements));
		T element = child.getElementAt(0);

		int index = findIndex(parent, element);
		index = Math.min(index, parent.getChildren().size());
		parent.addChild(index, child);
	}

	private int findIndex(BNode<T> node, T element) {
		int index = 0;
		boolean found = false;
		while (!found && index < node.size()) {
			if (element.compareTo(node.getElementAt(index)) < 0) {
				found = true;
			} else {
				index++;
			}
		}
		return index;
	}

	private void promote(BNode<T> node) {
		int median = node.getElements().size() / 2;
		T promotee = node.getElements().get(median);

		BNode<T> parent;
		if (node.equals(this.getRoot())) {
			parent = new BNode<>(this.order);
			parent.addChild(0, node);
			this.root = parent;
		} else {
			parent = node.getParent();
		}

		parent.addElement(promotee);
		node.removeElement(promotee);
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
