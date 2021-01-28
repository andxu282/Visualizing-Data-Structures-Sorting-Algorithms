package linearDataStructures;

import java.util.Arrays;

import trees.Heap;
import trees.MinHeap;

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
		Heap<T> heap = new MinHeap<T>();
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] = heap.pop();
		}
	}

	public void quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private void quickSort(T[] arr, int lowIndex, int highIndex) {
		if (highIndex - lowIndex == 1) {
			return;
		}

		int pivotIndex = lowIndex;
		int leftPointer = lowIndex;
		int rightPointer = highIndex;
		while (leftPointer < rightPointer) {
			while (arr[leftPointer].compareTo(arr[pivotIndex]) < 0) {
				leftPointer++;
			}
			while (arr[rightPointer].compareTo(arr[pivotIndex]) > 0) {
				rightPointer--;
			}
			this.swap(leftPointer, rightPointer, arr);
		}

		quickSort(arr, lowIndex, rightPointer + 1);
		quickSort(arr, leftPointer + 1, highIndex);
	}

	public void mergeSort(Integer[] arr) {
		Integer[] newArray = mergeSortRec(arr);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = newArray[i];
		}
	}

	private Integer[] mergeSortRec(Integer[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		int midIndex = arr.length / 2;
		return merge((Integer[]) mergeSortRec(Arrays.copyOfRange(arr, 0, midIndex)),
				(Integer[]) mergeSortRec(Arrays.copyOfRange(arr, midIndex, arr.length)));
	}

	private Integer[] merge(Integer[] left, Integer[] right) {
		Integer[] mergedArray = new Integer[left.length + right.length];
		int rightIndex = 0;
		int currentIndex = 0;
		for (int leftIndex = 0; leftIndex < left.length; leftIndex++) {
			if (rightIndex >= right.length) {
				for (int i = leftIndex; i < left.length; i++) {
					mergedArray[currentIndex] = left[i];
					currentIndex++;
				}
				break;
			}
			if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
				mergedArray[currentIndex] = left[leftIndex];
				currentIndex++;
			} else {
				mergedArray[currentIndex] = right[rightIndex];
				currentIndex++;
				rightIndex++;
				leftIndex--;
			}
		}
		for (int i = rightIndex; i < right.length; i++) {
			mergedArray[currentIndex] = right[i];
			currentIndex++;
		}
		return mergedArray;
	}
}
