package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

   protected DoubleLinkedList<T> list;
   protected int size;

   public StackRecursiveDoubleLinkedListImpl(int size) {
      this.size = size;
      this.list = new RecursiveDoubleLinkedListImpl<T>();
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (isFull()) {
         throw new StackOverflowException();
      }

      this.list.insertFirst(element);
   }

   @Override
   public T pop() throws StackUnderflowException {
      if (isEmpty()) {
         throw new StackUnderflowException();
      }

      T result = this.getList().getHead();
      this.list.removeFirst();

      return result;
   }

   @Override
   public T top() {
      T result = null;

      if (!isEmpty()) {
         result = this.getList().getHead();
      }

      return result;
   }

   @Override
   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   @Override
   public boolean isFull() {
      return this.list.size() == this.size;
   }

   @SuppressWarnings("unchecked")
   private RecursiveDoubleLinkedListImpl<T> getList() {
      return (RecursiveDoubleLinkedListImpl) this.list;
   }
}
