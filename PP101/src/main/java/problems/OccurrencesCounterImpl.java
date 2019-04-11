package problems;

/**
 *
 * Dado um array ordenado de elementos comparaveis e um outro elemento comparavel, 
 * implemente o metodo que conte as ocorrências do elemento no array. 
 *
 * Restricoes:
 * - a complexidade esperada é O (log.n). Soluções com tempo O(n) ou superiores serão desconsideradas.
 * - voce nao pode usar memoria extra
 * - voce nao pode usar metodos prontos da bilbioteca de arrays (exceto o metodo length)
 * - Dica: tente pensar numa forma eficiente (em log n) de descobrir a posicao de um 
 *   elemento no array e use essa ideia para contar as ocorrencias desse elemento no array
 *
 * @author campelo
 *
 * @param <T>
 */
public class OccurrencesCounterImpl<T extends Comparable<T>> {
   /*
    * Se elem está presente no array[], retorna a quantidade de ocorrências de elem.
    * Caso contrário, retorna 0.
    */
   public int count(T[] array, T elem) {
      int index = boundBinarySearch(array, elem, 0, array.length - 1);
      int count = 0;

      if (index != -1) {
         boolean foundLeft = true;
         int lastLeft = index;

         // find first on left
         while (foundLeft) {
            int newSearch = boundBinarySearch(array, elem, 0, lastLeft - 1);
            if (newSearch == -1) {
               foundLeft = false;
            } else {
               lastLeft = newSearch;
            }
         }

         // find first on right
         int lastRight = index;
         boolean foundRight = true;

         while (foundRight) {
            int newSearch = boundBinarySearch(array, elem, lastRight + 1, array.length - 1);
            if (newSearch == -1) {
               foundRight = false;
            } else {
               lastRight = newSearch;
            }
         }

         count = lastRight - lastLeft + 1;
      }

      return count;
   }

   private int boundBinarySearch(T[] array, T elem, int start, int end) {
      int index = -1;

      int left = start;
      int right = end;

      while (left <= right && index == -1) {
         int med = (left + right) / 2;
         T currentElem = array[med];

         if (elem.equals(currentElem)) {
            index = med;
         } else if (elem.compareTo(currentElem) > 0) {
            left = med + 1;
         } else {
            right = med - 1;
         }
      }

      return index;
   }
}