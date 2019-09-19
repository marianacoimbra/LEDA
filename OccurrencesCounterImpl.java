package problems;

/**
 *
 * Dado um array ordenado de elementos comparaveis e um outro elemento
 * comparavel, implemente o metodo que conte as ocorrências do elemento no
 * array.
 *
 * Restricoes: - a complexidade esperada é O (log.n). Soluções com tempo O(n) ou
 * superiores serão desconsideradas. - voce nao pode usar memoria extra - voce
 * nao pode usar metodos prontos da bilbioteca de arrays (exceto o metodo
 * length) - Dica: tente pensar numa forma eficiente (em log n) de descobrir a
 * posicao de um elemento no array e use essa ideia para contar as ocorrencias
 * desse elemento no array
 *
 * @author campelo
 *
 * @param <T>
 */
public class OccurrencesCounterImpl<T extends Comparable<T>> {
	/*
	 * Se elem está presente no array[], retorna a quantidade de ocorrências de
	 * elem. Caso contrário, retorna 0.
	 */
	public int count(T[] array, T elem) {
		return conta(array, elem, 0, array.length - 1);
	}

	private int conta(T[] array, T elem, int leftIndex, int rightIndex) {
		int ceil = binarySearchCeil(array, elem, leftIndex, rightIndex);
		int floor = binarySearchFloor(array, elem, leftIndex, rightIndex);
		int cont = ceil - floor - 1;
		return cont;
	}

	public int binarySearchFloor(T[] array, T valor, int left, int right) {
		int meio = (left + right) / 2;
		if (left > right) {
			return right;
		}
		if (array[meio].compareTo(valor) >= 0) {
			return binarySearchFloor(array, valor, left, meio - 1);
		} else {
			return binarySearchFloor(array, valor, meio + 1, right);
		}
	}

	public int binarySearchCeil(T[] array, T valor, int left, int right) {
		int meio = (left + right) / 2;
		if (left > right) {
			return left;
		}
		if (array[meio].compareTo(valor) > 0) {
			return binarySearchCeil(array, valor, left, meio - 1);
		} else {
			return binarySearchCeil(array, valor, meio + 1, right);
		}
	}

}