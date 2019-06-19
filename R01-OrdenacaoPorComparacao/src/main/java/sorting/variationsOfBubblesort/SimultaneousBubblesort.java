package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
	  int j = rightIndex;

		for (int i = leftIndex; i < j; i++) {
		  bubble(array, i, j);
		  bubbleBack(array, i, j - 1);
		  j -= 1;
		}
	}

	private void bubble(T[] array, int start, int end) {
			for (int i = start; i < end; i++) {
				if(array[i].compareTo(array[i + 1]) > 0) {
					util.Util.swap(array, i, i + 1);
				}
			}
	}

	private void bubbleBack(T[] array, int start, int end) {
		for (int i = end; i > start; i--) {
			if(array[i].compareTo(array[i - 1]) < 0) {
				util.Util.swap(array, i, i - 1);
			}
		}
	}
}
