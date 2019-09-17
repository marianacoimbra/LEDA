package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */

public class FloorCeilBinarySearch implements FloorCeil {
//	public static void main(String[] args) {
//		Integer[] array = { 3, 4, 5, 6, 7 };
//		FloorCeilBinarySearch f = new FloorCeilBinarySearch();
//		System.out.println(f.buscaBinaria(array, 7, 0, array.length - 1));
//	}

	@Override
	public Integer floor(Integer[] array, Integer x) {
		return buscaBinariaFloor(array, x, 0, array.length - 1);

	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return buscaBinariaCeil(array, x, 0, array.length - 1);
	}

	private Integer buscaBinariaFloor(Integer[] array, Integer valor, int left, int right) {
		int meio = (left + right) / 2;
		if (left > right) {
			if(right < 0) {
				return null;
			}
			return array[right];
		}

		if (valor == array[meio]) {
			return array[meio];

		} else if (valor < array[meio]) {
			return buscaBinariaFloor(array, valor, left, meio - 1);
		} else {
			return buscaBinariaFloor(array, valor, meio + 1, right);
		}
	}

	private Integer buscaBinariaCeil(Integer[] array, Integer valor, int left, int right) {
		int meio = (left + right) / 2;
		if (left > right) {
			if(left > array.length - 1) {
				return null;
			}
			return array[left];
		}

		if (valor == array[meio]) {
			return array[meio];

		} else if (valor < array[meio]) {
			return buscaBinariaCeil(array, valor, left, meio - 1);
		} else {
			return buscaBinariaCeil(array, valor, meio + 1, right);
		}
	}

}
