package treesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import linearDataStructures.HashSetImplementation;
import trees.BinarySearchTree;

class BinarySearchTreeTest {

	@Test
	void basicTest() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(3);
		assertEquals(true, tree.find(3));
	}

	@Test
	void testInsert() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(3);
		tree.insert(1);
		tree.insert(5);
		tree.insert(0);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		for (int i = 0; i < 7; i++) {
			assertEquals(true, tree.find(i));
		}
	}

	@Test
	void testRemove() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(3);
		tree.insert(1);
		tree.insert(5);
		tree.insert(0);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		for (int i = 0; i < 7; i++) {
			assertEquals(true, tree.find(i));
		}
		tree.remove(3);
		tree.remove(5);
		assertEquals(5, tree.size());
	}

	@Test
	void testRemove1() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(3);
		tree.insert(1);
		tree.insert(5);
		tree.insert(0);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		for (int i = 0; i < 7; i++) {
			assertEquals(true, tree.find(i));
		}
		tree.remove(3);
		tree.remove(2);
		assertEquals(5, tree.size());
	}

	@Test
	void testAddRemove() {
		for (int j = 0; j < 100; j++) {
			BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
			Random r = new Random();
			Set<Integer> hashSet = new HashSetImplementation<Integer>();
			Set<Integer> hashSet1 = new HashSet<Integer>();
			for (int i = 0; i < 50; i++) {
				int num = r.nextInt(1000);
				hashSet.add(num);
				hashSet1.add(num);
			}
			Object[] nums = hashSet.toArray();
			Object[] nums1 = hashSet1.toArray();
			ArrayList<Integer> numsList = new ArrayList<Integer>();
			ArrayList<Integer> numsList1 = new ArrayList<Integer>();
			for (int i = 0; i < nums.length; i++) {
				tree.insert((Integer) nums[i]);
				numsList.add((Integer) nums[i]);
				numsList1.add((Integer) nums1[i]);
			}

			for (int i = 0; i < nums.length; i++) {
				assertEquals(true, tree.find((Integer) nums[i]));
				assertEquals(true, tree.find((Integer) nums1[i]));
			}

			ArrayList<Integer> numsListCopy = new ArrayList<Integer>(numsList);
			Collections.sort(numsListCopy);

			Collections.shuffle(numsList);
			for (int i = 0; i < numsList.size() / 2; i++) {
				tree.remove(numsList.get(i));
				numsListCopy.remove(numsList.get(i));
			}

			for (int i = 0; i < numsList.size() / 2; i++) {
				assertEquals(false, tree.find(numsList.get(i)));
			}

			for (int i = (numsList.size() / 2) + 1; i < numsList.size(); i++) {
				assertEquals(true, tree.find(numsList.get(i)));
			}
		}


	}

}
