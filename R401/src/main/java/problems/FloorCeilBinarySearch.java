package problems;

import java.util.Arrays;

/**
 * Calcula o floorOrCeil e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 *
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 *
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {
	@Override
	public Integer floor(Integer[] array, Integer x) {
		return floorOrCeil(array, 0, array.length - 1, x, 1);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return floorOrCeil(array, 0, array.length - 1, x, 0);
	}

	private Integer floorOrCeil(Integer[] array, int leftIndex, int rightIndex, Integer x, int compareOffset) {
		Integer result = null;

		if (leftIndex == rightIndex) {
			result = array[leftIndex];
		} else if (searchable(array, leftIndex, rightIndex)) {
			int med = (leftIndex + rightIndex) / 2;

			if (array[med].equals(x) || array[med + 1].equals(x)) {
				result = x;
			} else if (array[med + compareOffset].compareTo(x) < 0) {
				result = floorOrCeil(array, med + 1, rightIndex, x, compareOffset);
			} else {
				result = floorOrCeil(array, leftIndex, med, x, compareOffset);
			}
		}

		return result;
	}

	private boolean searchable(Integer[] array, int leftIndex, int righIndex) {
		return leftIndex >= 0 && righIndex < array.length && leftIndex < righIndex;
	}
}
