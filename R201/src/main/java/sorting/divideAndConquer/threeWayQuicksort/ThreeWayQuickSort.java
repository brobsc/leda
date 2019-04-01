package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex) {
			return;
		}

		int smallerPart = partition(array, leftIndex, rightIndex);
		int biggerPart = partitionBig(array, smallerPart, rightIndex);
		sort(array, leftIndex, smallerPart);
		sort(array, biggerPart, rightIndex);
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

	private int partitionBig(T[] array, int pivot, int end) {
	    int i = pivot;

		for (int j = pivot + 1; j <= end; j++) {
			if (array[j].compareTo(array[pivot]) == 0) {
				i += 1;
				util.Util.swap(array, j, i);
			}
		}

		return i + 1;
	}
}
