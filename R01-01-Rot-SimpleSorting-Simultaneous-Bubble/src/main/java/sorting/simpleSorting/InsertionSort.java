package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			this.insert(array, i);
		}
	}

	private void insert(T[] array, int index) {
	  int i = index - 1;
		while (i >= 0 && array[i].compareTo(array[i+1]) > 0) {
			util.Util.swap(array, i, i + 1);
			i -= 1;
		}
	}
}
