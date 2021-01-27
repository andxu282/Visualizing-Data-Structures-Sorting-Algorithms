package linearDataStructures;

import trees.MaxHeap;

public class Sorter<T extends Comparable<T>> {

	private void swap(int i, int j, T[] arr) {
		T first = arr[i];
		T second = arr[j];
		arr[i] = second;
		arr[j] = first;
	}

	public void bubbleSort(T[] arr) {
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					this.swap(i, j, arr);
				}
			}
		}
	}

	public void insertionSort(T[] arr) {
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			T insert = arr[i];
			for (int j = 0; j < i; j++) {
				if (insert.compareTo(arr[j]) < 0) {
					this.swap(i, j, arr);
				}
			}
		}
	}

	public void selectionSort(T[] arr, T min) {
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			int minIndex = i;
			T minCopy = min;
			for (int j = i; j < size; j++) {
				if (arr[j].compareTo(minCopy) < 0) {
					minCopy = arr[j];
					minIndex = j;
				}
			}
			this.swap(i, minIndex, arr);
		}
	}

	public void heapSort(T[] arr) {
		MaxHeap<T> heap = new MaxHeap<T>();
	}

	public void quickSort(T[] arr) {

	}

	public void mergeSort(T[] arr) {

	}
}
