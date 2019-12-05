package adt.heap.extended;

import java.util.Comparator;
import java.util.List;

import adt.heap.HeapImpl;

public class HeapMergeImpl extends HeapImpl<Integer> implements HeapMerge {

	public HeapMergeImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer[] mergeArraysAndOrder(List<Integer[]> arrays) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int sumRange(int k1, int k2) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
