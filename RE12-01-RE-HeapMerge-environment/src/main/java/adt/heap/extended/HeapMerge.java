package adt.heap.extended;

import java.util.List;

import adt.heap.Heap;

/**
 * Estensao de heap (de inteiros) para fazer o merge ordenado de diversos arrays e para 
 * somar numeros da heap compreendidos entre duas estatisticas de ordem. A heap possui 
 * um comparator interno que pode ser ComparatorMaxHeap e ComparatorMinHeap. Apesar da heap
 * aceitar qualquer comparator, voce pode considerar apenas esses dois tipos de comparators.
 * Os metodos devem funcionar independente do comparator. 
 *  
 * @author Adalberto
 *
 */
public interface HeapMerge extends Heap<Integer>{
	/**
	 * Faz o merge dos arrays contidos na lista de arrays recebida como parametro.
	 * Os arrays dentro da lista podem estar desordenados. A saida deve estar ordenada
	 * em ordem ascendente. Para saber se sua heap eh max ou min, olhe para o comparator 
	 * interno.
	 * 
	 * Restricoes:
	 * - Nao precisa se preocupar se a heap ja contem ou nao elementos antes do merge
	 * - Voce pode usar apenas memoria extra em O(n) - tamanho do array interno da heap.
	 * - Voce pode modificar a heap
	 * - Procure usar os metodos insert e extractRootElement ha heap para resolver o problema
	 * 
	 * @param arrays uma lista de arrays de inteiros.
	 * @return
	 */
	public Integer[] mergeArraysAndOrder(List<Integer[]> arrays);
	
	/**
	 * Retorna a soma dos elementos compreendidos entre a k1-esima estatistica de 
	 * ordem (exclusive) e k2-esima estatistica de ordem (exclusive) na heap.
	 * Exemplo: suponha uma min heap onde foram previsamente inseridos os 
	 * elementos [1,3,5,7,9,11,13,15,17]
	 * Seja k1 = 3 (o terceiro menor elemento eh 5)
	 * Seja k2 = 8 (o sexto menor elemento eh 15)
	 * A soma seria entao: 7+9+11+13 = 40
	 * Obs: a forma/quantidade de extracoes muda dependendo se a heap eh min ou max.
	 * Tente enxergar isso em sua resolucao.
	 * 
	 * Restricoes:
	 * - Procure fazer seu codigo usando (em termos de metodos da heap) apenas o 
	 *   metodo extractRootElement().
	 * - Voce nao pode usar memoria extra (estruturas), exceto variaveis simples.
	 * - Voce pode modificar a heap
	 * 
	 * @param k1  o k1-esimo menor elemento
	 * @param k2  o k2-esimo menor elemento
	 * @return
	 */
	public int sumRange(int k1, int k2);
}
