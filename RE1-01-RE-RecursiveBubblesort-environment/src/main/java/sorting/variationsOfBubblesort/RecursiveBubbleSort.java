package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

public class RecursiveBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do algoritmo
	 * e depois o caso indutivo, que reduz o problema para uma entrada menor em uma
	 * chamada recursiva. Seu algoritmo deve ter complexidade quadrática O(n^2).
	 */

	// Recursion Idea.
	//
	// Base Case: If array size is 1, return.
	// Do One Pass of normal Bubble Sort. This pass fixes last element of current
	// subarray.
	// Recur for all elements except last of current subarray.

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex == 1)
			return;
		for (int j = leftIndex; j < rightIndex; j++) {
			if (array[j].compareTo(array[j + 1]) > 0) {
				T aux = array[j];
				array[j] = array[j + 1];
				array[j + 1] = aux;
			}
		}
		sort(array, leftIndex, rightIndex - 1);
	}
}
