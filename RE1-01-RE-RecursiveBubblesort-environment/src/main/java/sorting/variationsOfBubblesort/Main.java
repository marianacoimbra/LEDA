package sorting.variationsOfBubblesort;
import java.lang.reflect.Array;
import java.util.Arrays;

import sorting.variationsOfBubblesort.RecursiveBubbleSort;

public class Main {
	public static void main(String[] args) {
		Integer[] array = {7, 2, 9 , 11, 10, 88};
		
		RecursiveBubbleSort<Integer> bubble = new RecursiveBubbleSort<Integer>();
		bubble.sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
		
	}
}
