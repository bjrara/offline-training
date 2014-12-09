package com.offline.training.project.pj1;

public class DoublyLinkedList {
	transient DoublyLinkedNode head;
	transient DoublyLinkedNode tail;
	private int size;

	public DoublyLinkedList() {
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Run run) {
		DoublyLinkedNode appendObj = new DoublyLinkedNode(run);
		if (head == null) {
			head = tail = appendObj;
		} else {
			appendObj.setPrevious(tail);
			tail.setNext(appendObj);
			tail = tail.getNext();
		}
		size++;
	}

	public void remove(DoublyLinkedNode curr) {
		if (curr == null)
			return;
		if (curr == head) {
			head = curr.getNext();
			head.setPrevious(null);
		} else {
			curr.getPrevious().setNext(curr.getNext());
			if (curr.getNext() != null)
				curr.getNext().setPrevious(curr.getPrevious());
		}
		size--;
	}

}