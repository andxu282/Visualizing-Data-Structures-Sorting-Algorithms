package linearDataStructures;

import java.util.Arrays;

public class DoublyLinkedListClient {
	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		System.out.println(Arrays.toString(list.toArray()));
		DoublyLinkedList<Integer> subList = (DoublyLinkedList<Integer>) list.subList(0, 5);
		System.out.println(Arrays.toString(subList.toArray()));
	}
}
