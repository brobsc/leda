package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FamilyBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FamilyBST<T>{
	@Override
	public boolean primosPrimeiroGrau(T elem1, T elem2) {
		BSTNode<T> node1 = search(elem1);
		BSTNode<T> node2 = search(elem2);

		return primosPrimeiroGrau(node1, node2);
	}

	private boolean primosPrimeiroGrau(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;

		if (node1 != null && node2 != null) {
			result = irmao((BSTNode<T>) node1.getParent(),
					(BSTNode<T>) node2.getParent());
		}

		return result;
	}


	@Override
	public boolean primosSegundoGrau(T elem1, T elem2) {
		BSTNode<T> node1 = search(elem1);
		BSTNode<T> node2 = search(elem2);
		return primosSegundoGrau(node1, node2);
	}

	private boolean primosSegundoGrau(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;

		if (node1 != null && node2 != null) {
			result = primosPrimeiroGrau((BSTNode<T>) node1.getParent(), node2) ||
					primosPrimeiroGrau(node1, (BSTNode<T>) node2.getParent());
		}

		return result;
	}

	private boolean irmao(BSTNode<T> node1, BSTNode<T> node2) {
		boolean result = false;

		if (node1 != null && node2 != null && !node1.equals(node2) &&
				node1.getParent() != null && node2.getParent() != null) {
			result = node1.getParent().equals(node2.getParent());
		}

		return result;
	}
	
	
	/**
	 * NAO ALTERAR OS METODOS ABAIXO PORQUE SERAO UTULIZADOS PELOS TESTES
	 */
	@Override
	public void insert(T element) {
		insert(root, element);
	}

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>)node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>)node.getRight(), element);
			}
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	protected BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> result = node;
		if (element != null) {
			if (!node.isEmpty()) {
				if (element.compareTo(node.getData()) == 0) {
					result = node;
				} else if (element.compareTo(node.getData()) < 0) {
					result = search((BSTNode<T>)node.getLeft(), element);
				} else {
					result = search((BSTNode<T>)node.getRight(), element);
				}
			}
		}

		return result;
	}
}
