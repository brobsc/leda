package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (sortable(array, leftIndex, rightIndex)) {
			int part = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, part);
			sort(array, part + 1, rightIndex);
		}
	}

	private boolean sortable(T[] array, int leftIndex, int rightIndex) {
		boolean result = leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex;

		for (T obj : array) {
			if (obj == null) {
				result = false;
			}
		}

		return result;
	}

	private int partition(T[] array, int pivot, int end) {
		int i = pivot;

		for (int j = pivot + 1; j <= end; j++) {
			if (array[j].compareTo(array[pivot]) < 0) {
				i += 1;
				util.Util.swap(array, j, i);
			}
		}

		util.Util.swap(array, i, pivot);
		return i;
	}
}
