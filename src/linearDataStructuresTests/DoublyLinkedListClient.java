package linearDataStructuresTests;

import java.util.Arrays;
import java.util.ListIterator;

import linearDataStructures.DoublyLinkedList;

public class DoublyLinkedListClient {
	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		ListIterator<Integer> listIterator = list.listIterator();
		for (int i = 0; i < 10; i++) {
			listIterator.add(i);
		}
		System.out.println(Arrays.toString(list.toArray()));

		for (int i = 0; i < 10; i++) {
			System.out.println(list.get(i));
		}

		System.out.println();
		ListIterator<Integer> listIterator1 = list.listIterator();
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(list.toArray()));
			listIterator1.add(i);
		}

		System.out.println();
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(list.size());

		ListIterator<Integer> listIterator2 = list.listIterator(5);
		for (int i = 0; i < 10; i++) {
			listIterator2.add(i);
		}

		System.out.println();
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(list.size());
	}
}
