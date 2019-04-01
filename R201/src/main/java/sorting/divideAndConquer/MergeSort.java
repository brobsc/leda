package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < 0 || rightIndex > array.length || leftIndex >= rightIndex) {
			return;
		}

		int med = (leftIndex + rightIndex) / 2;
		sort(array, leftIndex, med);
		sort(array, med + 1, rightIndex);
		merge(array, leftIndex, med, med + 1, rightIndex);
	}

	private void merge(T[] array, int from1, int to1, int from2, int to2) {
		if (from1 >= to1 || from2 >= to2) {
			return;
		}

		T[] aux = Arrays.copyOf(array, array.length);
		if (array[from1].compareTo(array[from2]) < 0) {

		}
	}
}
