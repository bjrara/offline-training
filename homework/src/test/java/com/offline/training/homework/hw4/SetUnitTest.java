package com.offline.training.homework.hw4;

import java.util.Random;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class SetUnitTest {
	private Set mySet = new Set();
	private TreeSet<Integer> reference = new TreeSet<Integer>();
	private Random rand = new Random();
	
	@Test
	public void testAdd() {
		for (int i = 0; i < 50; i++) {
			int randInt = rand.nextInt(50);
			mySet.insert(randInt);
			reference.add(randInt);
		}
		AssertEquals(mySet, reference);
	}
	
	@Test
	public void testUnion() {
		Set anotherSet = new Set();
		TreeSet<Integer> anotherReference = new TreeSet<Integer>(); 
		for (int i = 0; i < 50; i++) {
			int randInt = rand.nextInt(50);
			mySet.insert(randInt);
			reference.add(randInt);
		}
		for (int i = 0; i < 25; i++) {
			int randInt = rand.nextInt(50);
			anotherSet.insert(randInt);
			anotherReference.add(randInt);
		}
		mySet.union(anotherSet);
		reference.addAll(anotherReference);
		AssertEquals(mySet, reference);
	}
	
	@Test
	public void testInterset() {
		Set anotherSet = new Set();
		TreeSet<Integer> anotherReference = new TreeSet<Integer>(); 
		for (int i = 0; i < 50; i++) {
			int randInt = rand.nextInt(50);
			mySet.insert(randInt);
			reference.add(randInt);
		}
		for (int i = 0; i < 25; i++) {
			int randInt = rand.nextInt(50);
			anotherSet.insert(randInt);
			anotherReference.add(randInt);
		}
		mySet.intersect(anotherSet);
		reference.retainAll(anotherReference);
		AssertEquals(mySet, reference);
	}
	
	private static void AssertEquals(Set mySet, TreeSet<Integer> reference) {
		Assert.assertEquals(mySet.cardinality(), reference.size());
		Integer[] mySetValues = getSetValues(mySet);
		for (int i = 0; i < reference.size(); i++) {
			Assert.assertEquals(reference.pollFirst(), mySetValues[i]);
		}
	}
	
	private static Integer[] getSetValues(Set set) {
		String setString = set.toString();
		String[] splitted = setString.split(" ");
		Integer[] ints = new Integer[set.cardinality()];
		for(int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(splitted[i+1]);
		}
		return ints;
	}
}
