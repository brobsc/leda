package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
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
		return floor(array, 0, array.length - 1, x);
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[]{4,6,6,6,8,10};

		FloorCeilBinarySearch f = new FloorCeilBinarySearch();

		System.out.println(f.floor(a, 7));
		System.out.println(f.ceil(a, 7));
		System.out.println(f.floor(a, 8));
		System.out.println(f.ceil(a, 8));
		System.out.println(f.floor(a, 12));
		System.out.println(f.ceil(a, 12));
		System.out.println(f.floor(a, 2));
		System.out.println(f.ceil(a, 2));
		System.out.println(f.floor(a, 5));
		System.out.println(f.ceil(a, 5));
	}

	private Integer floor(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		Integer result = null;

		if (leftIndex == rightIndex) {
			result = array[leftIndex];
		} else if (searchable(array, leftIndex, rightIndex)) {
			int med = (leftIndex + rightIndex) / 2;

			if (array[med].equals(x) || array[med + 1].equals(x)) {
				result = x;
			} else if (array[med + 1].compareTo(x) < 0) {
				result = floor(array, med + 1, rightIndex, x);
			} else {
				result = floor(array, leftIndex, med, x);
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return ceil(array, 0, array.length - 1, x);
	}

	private Integer ceil(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		Integer result = null;

		if (leftIndex == rightIndex) {
			result = array[leftIndex];
		} else if (searchable(array, leftIndex, rightIndex)) {
			int med = (leftIndex + rightIndex) / 2;

			if (array[med].equals(x) || array[med + 1].equals(x)) {
				result = x;
			} else if (array[med].compareTo(x) > 0) {
				result = ceil(array, leftIndex, med, x);
			} else {
				result = ceil(array, med + 1, rightIndex, x);
			}
		}

		return result;
	}

	private boolean searchable(Integer[] array, int leftIndex, int righIndex) {
		return leftIndex >= 0 && righIndex < array.length && leftIndex < righIndex;
	}
}
