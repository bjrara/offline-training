package com.offline.training.project.pj1;

public class DoublyLinkedList {
	transient DoublyLinkedNode head;
	transient DoublyLinkedNode tail;
	private int size;

	public DoublyLinkedList(int[] red, int[] green, int[] blue, int[] runLengths) {
		for (int i = 0; i < runLengths.length; i++) {
			add(runLengths[i], red[i], green[i], blue[i]);
		}
	}

	public DoublyLinkedList() {
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(int runLength, int red, int green, int blue) {
		if (!(validateColor(red) && validateColor(green) && validateColor(blue))) {
			throw new RuntimeException("Invalid pixel value found!");
		}
		DoublyLinkedNode appendObj = new DoublyLinkedNode(new Run(red, green, blue, runLength));
		if (head == null) {
			head = tail = appendObj;
		} else {
	appendObj.setPrevious(tail);
			tail.setNext(appendObj);
			tail = tail.getNext();
		}
		size++;
	}

	private static boolean validateColor(int color) {
		if (color >= 0 && color <= 255) {
			return true;
		}
		return false;
	}
}