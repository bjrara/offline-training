package com.offline.training.homework.hw3;

public class LockDListNode extends DListNode {

	private boolean isLocked = false;

	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
	}

	public void lock() {
		isLocked = true;
	}

	public boolean isLocked() {
		return isLocked;
	}
}
