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
		if (leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex) {
			return;
		}

		int med = (leftIndex + rightIndex) / 2;
		sort(array, leftIndex, med);
		sort(array, med + 1, rightIndex);
		merge(array, leftIndex, med, rightIndex);
	}

	private void merge(T[] array, int start, int med, int end) {
		T[] aux = Arrays.copyOf(array, array.length);

		int i = start;
		int j = med + 1;
		int k = start;

		while (i <= med && j <= end) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i += 1;
			} else {
				array[k] = aux[j];
				j += 1;
			}

			k += 1;
		}

		while (j <= end) {
			array[k] = aux[j];
			j += 1;
			k += 1;
		}

		while (i <= med) {
			array[k] = aux[i];
			i += 1;
			k += 1;
		}
	}
}
