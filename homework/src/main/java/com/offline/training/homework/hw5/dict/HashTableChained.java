/* HashTableChained.java */

package com.offline.training.homework.hw5.dict;

import com.offline.training.homework.hw5.list.SList;

/**
 * HashTableChained implements a Dictionary as a hash table with chaining. All objects used as keys must have a valid
 * hashCode() method, which is used to determine which bucket of the hash table an entry is stored in. Each object's
 * hashCode() is presumed to return an int between Integer.MIN_VALUE and Integer.MAX_VALUE. The HashTableChained class
 * implements only the compression function, which maps the hash code to a bucket in the table's range.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 * Place any data fields here.
	 **/
	private static final float LOAD_FACTOR = 0.72f;
	private transient int buckets[];
	private transient SList<Entry>[] table;
	private int count;

	/**
	 * Construct a new empty hash table intended to hold roughly sizeEstimate entries. (The precise number of buckets is
	 * up to you, but we recommend you use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/

	public HashTableChained(int sizeEstimate) {
		int initSize = HashHelper.getPrime(sizeEstimate / LOAD_FACTOR);
		buckets = new int[initSize];
		table = new SList[initSize];
	}

	/**
	 * Construct a new empty hash table with a default size. Say, a prime in the neighborhood of 100.
	 **/

	public HashTableChained() {
		int initSize = HashHelper.getPrime(100);
		buckets = new int[initSize];
		table = new SList[initSize];
	}

	/**
	 * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE to a value in the range 0...(size of hash
	 * table) - 1.
	 *
	 * This function should have package protection (so we can test it), and should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		int m = buckets.length;
		return (code % HashHelper.getPrime(m)) % m;
	}

	/**
	 * Returns the number of entries stored in the dictionary. Entries with the same key (or even the same key and
	 * value) each still count as a separate entry.
	 * 
	 * @return number of entries in the dictionary.
	 **/

	public int size() {
		return count;
	}

	/**
	 * Tests if the dictionary is empty.
	 *
	 * @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Create a new Entry object referencing the input key and associated value, and insert the entry into the
	 * dictionary. Return a reference to the new entry. Multiple entries with the same key (or even the same key and
	 * value) can coexist in the dictionary.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the key by which the entry can be retrieved.
	 * @param value
	 *            an arbitrary object.
	 * @return an entry containing the key and value.
	 **/

	public Entry insert(Object key, Object value) {
		Entry newEntry = new Entry();
		newEntry.key = key;
		newEntry.value = value;
		int targetBucket = compFunction(key.hashCode());
		if (buckets[targetBucket] == 0) {
			table[targetBucket] = new SList<Entry>();
		}
		table[targetBucket].insertFront(newEntry);
		buckets[targetBucket]++;
		count++;
		return newEntry;
	}

	/**
	 * Search for an entry with the specified key. If such an entry is found, return it; otherwise return null. If
	 * several entries have the specified key, choose one arbitrarily and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if no entry contains the specified key.
	 **/

	public Entry find(Object key) {
		int targetBucket = compFunction(key.hashCode());
		if (buckets[targetBucket] == 0)
			return null;
		SList<Entry> slist = table[targetBucket];
		for (int i = 0; i < slist.length(); i++) {
			Entry entry = slist.nth(i);
			if (entry.key.equals(key))
				return entry;
		}
		return null;
	}

	/**
	 * Remove an entry with the specified key. If such an entry is found, remove it from the table and return it;
	 * otherwise return null. If several entries have the specified key, choose one arbitrarily, then remove and return
	 * it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if no entry contains the specified key.
	 */

	public Entry remove(Object key) {
		int targetBucket = compFunction(key.hashCode());
		if (buckets[targetBucket] == 0)
			return null;
		SList<Entry> slist = table[targetBucket];
		for (int i = 0; i < slist.length(); i++) {
			Entry entry = slist.nth(i);
			if (entry.key.equals(key)) {
				slist.remove(i);
				count--;
				return entry;
			}
		}
		return null;
	}

	/**
	 * Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = 0;
			table[i] = null;
		}
		count = 0;
	}

}
