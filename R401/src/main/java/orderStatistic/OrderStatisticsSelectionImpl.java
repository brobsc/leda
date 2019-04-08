package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
	  return selectMin(array, k);
	}

	private T selectMin(T[] array, int k) {
		T min = null;
		if (k > 0 && k <= array.length) {
			min = array[0];

			for (int i = 1; i < array.length; i++) {
				min = this.min(min, array[i]);
			}

			min = selectMin(array, k - 1, min);
		}

		return min;
	}

	private T selectMin(T[] array, int k, T min) {
		if (k > 0) {
			T nextMin = null;

			for (T element : array) {
				if (element != null && element.compareTo(min) > 0) {
					if (nextMin == null) {
						nextMin = element;
					} else {
						nextMin = this.min(element, nextMin);
					}
				}
			}

			min = selectMin(array, k - 1, nextMin);
		}


		return min;
	}

	private T min(T a, T b) {
		T min = a;
		if (b.compareTo(a) < 0) {
			min = b;
		}

		return min;
	}
}
