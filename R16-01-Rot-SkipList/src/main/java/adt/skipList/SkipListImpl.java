package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
	    SkipListNode<T> current = search(key);

	    if (current == null) {
	    	if (height >= maxHeight) {
	    		throw new RuntimeException();
			}

			SkipListNode<T> insertion = new SkipListNode<>(key, height, newValue);

			for (int i = 0; i < height; i++) {
			    SkipListNode<T> node = root;

			    while (node.getForward(i).getKey() < key) {
			    	node = node.getForward(i);
				}

			    SkipListNode<T> next = node.getForward(i);
			    node.getForward()[i] = insertion;
			    insertion.getForward()[i] = next;
			}
		} else {
	    	current.setValue(newValue);
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T> node = search(key);

		if (node != null) {
			int height = node.height();

			for (int i = 0; i < height; i++) {
				SkipListNode<T> current = root;

				while (current.getForward(i) != node) {
					current = current.getForward(i);
				}

				current.forward[i] = node.getForward(i);
			}
		}
	}

	@Override
	public int height() {
		int height = maxHeight;
		boolean found = false;

		while (height > 0 && !found) {
			height--;

			if (root.getForward(height) != NIL) {
				found = true;
				height++;
			}
		}

		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> result = null;
		int height = maxHeight - 1;

		while (result == null && height >= 0) {
			SkipListNode<T> node = root;

			while (node.getForward(height).getKey() <= key) {
			    node = node.getForward(height);

			    if (node.getKey() == key) {
			    	result = node;
				}
			}
			height--;
		}

		return result;
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> node = root;

		while (node.getForward(0) != NIL) {
			node = node.getForward(0);
			size++;
		}

		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size() + 2;
		SkipListNode<T>[] result = new SkipListNode[size];
		SkipListNode<T> node = root;

		for (int i = 0; i < size; i++) {
			result[i] = node;
			node = node.getForward(0);
		}

		return result;
	}

}
