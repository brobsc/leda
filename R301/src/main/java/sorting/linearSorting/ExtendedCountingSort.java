package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(sortable(array, leftIndex, rightIndex)) {
			int[] maxAndMin = getMaxAndMin(array, leftIndex, rightIndex);
			int min = maxAndMin[0];
			int max = maxAndMin[1];
		}
	}

	private int[] getMaxAndMin(Integer[] array, int leftIndex, int rightIndex) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = leftIndex; i <= rightIndex; i++) {
			max = Math.max(array[i], max);
			min = Math.min(array[i], min);
		}

		return new int[]{min, max};
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
