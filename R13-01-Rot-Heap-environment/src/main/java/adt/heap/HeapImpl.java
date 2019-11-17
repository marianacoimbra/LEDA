package adt.heap;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse comparator.
	 * Assim os metodos da heap não precisam saber se vai funcionar como max-heap
	 * ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		this.heapify(this.heap, position);
	}

	private void heapify(T[] heap, int position) {
		int left = this.left(position);
		int right = this.right(position);
		int maior;
		if (isValidIndex(left) && comparator.compare(heap[left], heap[position]) > 0) {
			maior = left;
		} else {
			maior = position;
		}
		if (isValidIndex(right) && comparator.compare(heap[right], heap[maior]) > 0) {
			maior = right;
		}

		if (maior != position) {
			Util.swap(heap, position, maior);
			this.heapify(maior);
		}
	}

	@Override
	public void insert(T element) {
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		if (element != null) {
			this.index++;
			this.heap[index] = element;

			int i = this.index;
			while (i > 0 && this.comparator.compare(heap[parent(i)], heap[i]) < 0) {
				Util.swap(heap, parent(i), i);
				i = this.parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = array.length - 1;

		if (array != null) {
			for (int i = this.parent(index); i >= 0; i--) {
				this.heapify(i);
			}
		}
	}

	// O tail da arvore vira a nova raiz e o heapify eh aplicado
	// Para manter o invariante
	@Override
	public T extractRootElement() {
		if (!this.isEmpty()) {
			T max = heap[0];
			heap[0] = heap[this.index];
			this.heap[this.index] = null;
			this.index--;
			heapify(0);
			return max;
		}
		return null;
	}

	@Override
	public T rootElement() {
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array != null) {
			HeapImpl<T> heap = new HeapImpl<T>((o1, o2) -> o1.compareTo(o2));
			heap.buildHeap(array);
			T[] aux = (T[]) new Comparable[array.length];
			for(int i = array.length - 1; i >= 0; i--) {
				aux[i] = heap.extractRootElement();
			}
			return aux;
		}
		return null;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

	private boolean isLeaf(int i) {
		boolean isLeaf = false;
		if (i > parent(this.index) && i <= index) {
			isLeaf = true;
		}
		return isLeaf;
	}

	private boolean isValidIndex(int index) {
		boolean isValid = false;
		if (index <= this.index && index >= 0) {
			isValid = true;
		}
		return isValid;
	}
}
