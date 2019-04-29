package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size (or the given size, if it is already prime). 
    * For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number.
    * If the given number is prime, it is returned. 
    * You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
      int result = number + 1;

      if (!util.Util.isPrime(result)) {
         result = getPrimeAbove(result);
      }

      return result;
   }

   @Override
   public void insert(T element) {
     if (element != null && this.indexOf(element) == -1) {
        int hash = this.getHashFunction().hash(element);
        LinkedList<T> ll = this.getLinkedList(hash);

        if (ll == null) {
           this.setLinkedList(hash);
           ll = this.getLinkedList(hash);
        } else {
           COLLISIONS++;
        }


        ll.add(element);

        elements++;
     }
   }

   @Override
   public void remove(T element) {
      int index = this.indexOf(element);

      if (index != -1) {
         LinkedList<T> ll = this.getLinkedList(index);
         ll.remove(element);
         this.elements--;
      }
   }

   @Override
   public T search(T element) {
      int index = this.indexOf(element);
      T result = null;

      if (index != -1) {
         result = element;
      }

      return result;
   }

   @Override
   public int indexOf(T element) {
      int hash = this.getHashFunction().hash(element);

      if (this.table[hash] == null) {
         hash = -1;
      } else {
         LinkedList<T> ll = this.getLinkedList(hash);
         int internal = ll.indexOf(element);

         if (internal == -1) {
            hash = -1;
         }
      }

      return hash;
   }

   @Override
   public HashFunctionClosedAddress<T> getHashFunction() {
      return (HashFunctionClosedAddress<T>) this.hashFunction;
   }

   private LinkedList<T> getLinkedList(int hash) {
      return (LinkedList<T>) this.table[hash];
   }

   private void setLinkedList(int hash) {
      this.table[hash] = new LinkedList<T>();
   }
}
