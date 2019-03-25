package recursao;

import java.util.Arrays;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
	  // From right
//		int result = array[array.length - 1];
//
//		if(array.length > 1) {
//			int[] restante = Arrays.copyOf(array, array.length - 1);
//			result += calcularSomaArray(restante);
//		}
//

		// From left
    int result = array[0];

    if (array.length > 1) {
    	int[] restante = Arrays.copyOfRange(array, 1, array.length);
    	result += calcularSomaArray(restante);
		}

		return result;
	}

	public long calcularFatorial(int n) {
		long result = 1;

		if (n >= 1) {
		  result = n * calcularFatorial(n - 1);
		}

		System.out.printf("%d! = %d\n", n, result);
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;

		if (n > 1) {
			result = calcularFibonacci(n - 2) + calcularFibonacci(n - 1);
		}

		// FIXME: PRINT STATEMENT
		// System.out.printf("%d, ", result);
		return result;
	}

	public int countNotNull(Object[] array) {
		int result = 0;

		if (array.length >= 1) {
			Object testado = array[0];

			if (testado != null) {
				result += 1;
			}

			Object[] restante = Arrays.copyOfRange(array, 1, array.length);
			result += countNotNull(restante);
		}

		return result;
	}

	public long potenciaDe2(int expoente) {
		int result = 1;
		// TODO IMPLEMENTE (USANDO RECURSAO) O CODIGO PARA PRODUZIR A N-ESIMA
		// POTENCIA
		// DE 2
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO ARITMETICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		// TODO IMPLEMENTE SEU CODIGO (USANDO RECURSAO) DE ENCONTRAR O n-ESIMO
		// TERMO
		// DA PROGRESSAO GEOMETRICA, DADO O TERMO INICIAL E A RAZAO
		return result;
	}

	public static void main(String[] args) {
	  MetodosRecursivos metodos = new MetodosRecursivos();
		int[] arrayInt = {1, 2, 3, 4, 5};
		int resultadoSoma = metodos.calcularSomaArray(arrayInt);
		System.out.printf("Resultado da soma e: %d", resultadoSoma);
		System.out.println();

		System.out.println("Fact:");
		metodos.calcularFatorial(5);
		System.out.println("Fib:");
		metodos.calcularFibonacci(7);

		System.out.println("Not null:");
		Object[] arrayObj = {null, "eita", null, "eita", "eita"};
		metodos.countNotNull(arrayObj);
	}
	
}
