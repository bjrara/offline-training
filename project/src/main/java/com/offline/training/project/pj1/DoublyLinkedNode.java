package com.offline.training.project.pj1;

public class DoublyLinkedNode {
	private int red;
	private int green;
	private int blue;
	private int runLength;

	private DoublyLinkedNode previous;
	private DoublyLinkedNode next;

	public DoublyLinkedNode(int runLength, int red, int green, int blue) {
		this.runLength = runLength;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public void Increment() {
		runLength++;
	}

	public void Decrement() {
		runLength--;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public int getRunLength() {
		return runLength;
	}

	public void setValue(int red, int green, int blue, int runLength) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.runLength = runLength;
	}

	public void setPrevious(DoublyLinkedNode previous) {
		this.previous = previous;
	}

	public DoublyLinkedNode getPrevious() {
		return previous;
	}

	public void setNext(DoublyLinkedNode next) {
		this.next = next;
	}

	public DoublyLinkedNode getNext() {
		return next;
	}
}