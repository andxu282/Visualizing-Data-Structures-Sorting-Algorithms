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
	}
}
