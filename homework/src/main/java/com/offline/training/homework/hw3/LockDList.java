package com.offline.training.homework.hw3;

public class LockDList extends DList {

	@Override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}

	@Override
	public void remove(DListNode node) {
		LockDListNode lockNode = (LockDListNode) node;
		if (lockNode.isLocked())
			return;
		else
			super.remove(node);
	}

	public void lockNode(DListNode node) {
		LockDListNode lockNode = (LockDListNode) node;
		lockNode.lock();
	}
}
