package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado array
	 * de dados comparaveis. Primeiro ele escolhe um elemento como o pivot e
	 * particiona os dados em duas partes baseado no pivot (exatemente da mesma
	 * forma que o quicksort). Depois disso, ele chama recursivamente o mesmo
	 * algoritmo em apenas uma das metades (a que contem o k-esimo menor elemento).
	 * Isso reduz a completixade de O(n.log n) para O(n).
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja fora
	 * do tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array o array de dados a procurar o k-esimo menor elemento.
	 * @param k     a ordem do elemento desejado. 1 significa primeiro menor
	 *              elemento, 2 significa segundo menor elemento e assim por diante
	 * @return
	 */
	public T quickSelect(T[] array, int k) {
		if (k > array.length || k < 0 || array == null) {
			return null;
		} else {
			return quickSelect(array, 0 , array.length - 1, k - 1);
		}
	}

	private T quickSelect(T[] array, int left, int right, int k) {
		T answer = null; 
		
		if(left <= right) {
			int n = partition(array, k, left, right);
			
			if(n == k) {
				return array[n];
			} else if(k < n) {
				answer = quickSelect(array, left, n - 1, k);
			} else if (k > n) {
				answer = quickSelect(array, n + 1, right, k); 
			}
		} 
		return answer;
	}

	private int partition(T[] array, int k, int left, int right) {
		T pivot = array[left];
		int i = left;

		for (int j = i + 1; j < array.length; j++) {
			if (array[j].compareTo(pivot) < 0) {
				i++;
				T aux = array[i];
				array[i] = array[j];
				array[j] = aux;
			}
		}

		T aux = array[i];
		array[i] = pivot;
		array[left] = aux;

		return i;
	}
	
	public static void main(String[] args) {
		QuickSelect<Integer> quickSelect = new QuickSelect<Integer>();
		
		Integer[] array = {6, 2, 70, 22, 64, 0, 5};
		
		System.out.println(quickSelect.quickSelect(array, 3));
	}
}


