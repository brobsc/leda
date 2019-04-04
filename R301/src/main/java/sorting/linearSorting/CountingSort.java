package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(sortable(array, leftIndex, rightIndex)) {
			int[] countingArray = countAndAccumulate(array, leftIndex, rightIndex);
			Integer[] aux = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);

			for (int i = aux.length - 1; i >= 0; i--) {
			  int value = aux[i];
				countingArray[value] -= 1;
			  int newIndex = countingArray[value];
				array[newIndex + leftIndex] = value;
			}
		}
	}

	private int[] countAndAccumulate(Integer[] array, int leftIndex, int rightIndex) {
	  int max = getMax(array, leftIndex, rightIndex);

	  // Length is max + 1 so the array is from 0...max (inclusive)
	  int[] result = new int[max + 1];

		for (int i = leftIndex; i <= rightIndex; i++) {
			int value = array[i];
			result[value] += 1;
		}

		for (int i = 1; i < result.length; i++) {
			result[i] += result[i - 1];
		}

		return result;
	}

	private int getMax(Integer[] array, int leftIndex, int rightIndex) {
		int max = 0;

		for (int i = leftIndex; i <= rightIndex; i++) {
			max = Math.max(array[i], max);
		}

		return max;
	}

	private boolean sortable(Integer[] array, int leftIndex, int rightIndex) {
		boolean result = leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex;

		int i = 0;

		while (i < array.length && result) {
			if (array[i] == null) {
				result = false;
			}
			i++;
		}

		return result;
	}
}
