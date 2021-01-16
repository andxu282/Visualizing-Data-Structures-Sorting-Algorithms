package linearDataStructures;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class DoublyLinkedListClient {
	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		LinkedList<Integer> javaList = new LinkedList<Integer>();
		ArrayList<Integer> supplementaryList = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			int num = r.nextInt(10);
			list.add(num);
			javaList.add(num);
		}

		for (int i = 0; i < 10; i++) {
			int num = r.nextInt(10);
			supplementaryList.add(num);
		}
		System.out.println(supplementaryList);
		list.addAll(5, supplementaryList);
		javaList.addAll(5, supplementaryList);
		for (int i = 0; i < 15; i++) {
			System.out.print(list.get(i) + " ");
			System.out.println(javaList.get(i));
		}
	}
}
