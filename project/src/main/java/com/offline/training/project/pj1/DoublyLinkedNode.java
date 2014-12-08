package com.offline.training.project.pj1;

public class DoublyLinkedNode {
	private Run value;
	private DoublyLinkedNode previous;
	private DoublyLinkedNode next;

	public DoublyLinkedNode(Run value) {
		this.value = value;
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
	
	public Run getValue() {
		return value;
	}
}