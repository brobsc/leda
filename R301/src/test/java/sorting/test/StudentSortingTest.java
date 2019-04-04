package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;

	public AbstractSorting<Integer>[] implementations;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		this.implementations = new AbstractSorting[]{
		        new CountingSort()
            // new ExtendedCountingSort()
		};
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array, Integer[] arrayExpected) {
		for (AbstractSorting implementation : this.implementations) {
			Integer[] arrayToBeSorted = Arrays.copyOf(array, array.length);

			implementation.sort(arrayToBeSorted);
			Assert.assertArrayEquals(arrayExpected, arrayToBeSorted);
		}
	}

	private void genericTest(Integer[] originalArray) {
		Integer[] arrayExpected = Arrays.copyOf(originalArray, originalArray.length);
		Arrays.sort(arrayExpected);
		genericTest(originalArray, arrayExpected);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */

	@Test
	public void testSortUniqueElement() { genericTest(new Integer[]{1}); }

	@Test
	public void testSortSorted() { genericTest(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}); }

	@Test
	public void testSortHasNull() {
	    Integer[] originalArray = new Integer[]{1, 2, null, 0};
		Integer[] arrayExpected = Arrays.copyOf(originalArray, originalArray.length);
		genericTest(originalArray, arrayExpected);
	}

	@Test
	public void testSortHasAllNull() {
		Integer[] originalArray = new Integer[]{null, null, null, null};
		Integer[] arrayExpected = Arrays.copyOf(originalArray, originalArray.length);
		genericTest(originalArray, arrayExpected);
	}


	@Test
	public void testSortLeftIndexSmallerThanZero() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = Arrays.copyOf(this.vetorTamPar, this.vetorTamPar.length);
			implementation.sort(arrayToBeSorted, -1, 1);
			Assert.assertArrayEquals(this.vetorTamPar, arrayToBeSorted);
		}
	}

	@Test
	public void testSortLeftIndexBiggerThanLength() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = Arrays.copyOf(this.vetorTamPar, this.vetorTamPar.length);
			implementation.sort(arrayToBeSorted, arrayToBeSorted.length + 1, arrayToBeSorted.length - 1);
			Assert.assertArrayEquals(this.vetorTamPar, arrayToBeSorted);
		}
	}

	@Test
	public void testSortLeftIndexBiggerThanRightIndex() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = Arrays.copyOf(this.vetorTamPar, this.vetorTamPar.length);
			implementation.sort(arrayToBeSorted, 3, 2);
			Assert.assertArrayEquals(this.vetorTamPar, arrayToBeSorted);
		}
	}

	@Test
	public void testSortRightIndexSmallerThanArrayLength() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = Arrays.copyOf(this.vetorTamPar, this.vetorTamPar.length);
			implementation.sort(arrayToBeSorted, 0, arrayToBeSorted.length + 1);
			Assert.assertArrayEquals(this.vetorTamPar, arrayToBeSorted);
		}
	}

	@Test
	public void testSortUsingLeftIndex() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = new Integer[]{5, 3, 2, 1, 6, 4};
			Integer[] arrayExpected = new Integer[]{5, 3, 2, 1, 4, 6};
			implementation.sort(arrayToBeSorted, 3, arrayToBeSorted.length - 1);
			Assert.assertArrayEquals(arrayExpected, arrayToBeSorted);
		}
	}

	@Test
	public void testSortUsingRightIndex() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = new Integer[]{5, 3, 2, 1, 6, 4};
			Integer[] arrayExpected = new Integer[]{2, 3, 5, 1, 6, 4};
			implementation.sort(arrayToBeSorted, 0, 2);
			Assert.assertArrayEquals(arrayExpected, arrayToBeSorted);
		}
	}

	@Test
	public void testSortUsingLeftIndexAndRightIndex() {
		for (AbstractSorting implementation : implementations) {
			Integer[] arrayToBeSorted = new Integer[]{5, 3, 2, 1, 6, 4};
			Integer[] arrayExpected = new Integer[]{5, 3, 1, 2, 6, 4};
			implementation.sort(arrayToBeSorted, 2, 4);
			Assert.assertArrayEquals(arrayExpected, arrayToBeSorted);
		}
	}

}
