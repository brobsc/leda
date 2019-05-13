package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++) {
			int min = select(array, i);
			if (min != i) {
				util.Util.swap(array, min, i);
			}
		}
	}


	private int select(T[] array, int start) {
		int minIndex = start;

		for (int i = start + 1; i < array.length; i++) {
		  if (array[i].compareTo(array[minIndex]) < 0) {
		  	minIndex = i;
			}
		}

		return minIndex;
	}
}
