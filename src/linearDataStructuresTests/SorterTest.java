package linearDataStructuresTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import linearDataStructures.Sorter;

class SorterTest {

	@Test
	void bubbleTest() {
		Sorter<Integer> sorter = new Sorter<Integer>();
		Integer[] arr = new Integer[] { 2, 81, 38, 58, 8, 3 };
		Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
		sorter.bubbleSort(arr);
		List<Integer> list = Arrays.asList(arrCopy);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), arr[i]);
		}

	}

	@Test
	void insertionTest() {
		Sorter<Integer> sorter = new Sorter<Integer>();
		Integer[] arr = new Integer[] { 2, 81, 38, 58, 8, 3 };
		Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
		sorter.insertionSort(arr);
		List<Integer> list = Arrays.asList(arrCopy);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), arr[i]);
		}

	}

	@Test
	void selectionTest() {
		Sorter<Integer> sorter = new Sorter<Integer>();
		Integer[] arr = new Integer[] { 2, 81, 38, 58, 8, 3 };
		Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
		sorter.selectionSort(arr, Integer.MAX_VALUE);
		List<Integer> list = Arrays.asList(arrCopy);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), arr[i]);
		}

	}

}
