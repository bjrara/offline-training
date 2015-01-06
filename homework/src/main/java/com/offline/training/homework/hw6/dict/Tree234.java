/* Tree234.java */

package com.offline.training.homework.hw6.dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   *
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   **/
  Tree234Node root;

  /**
   *  Tree234() constructs an empty 2-3-4 tree.
   *
   *  You may change this constructor, but you may not change the fact that
   *  an empty Tree234 contains no nodes.
   */
  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.  The test code depends on it.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      /* Most of the work is done by Tree234Node.toString(). */
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      /* Most of the work is done by Tree234Node.printSubtree(). */
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) {
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
	  if (root == null)
		  root = new Tree234Node(null, key);
	  else
		  insert(key, root);
  }

  public void insert(int key, Tree234Node node) {
	  Tree234Node targetNode = node;
	  // split if full while traversing
	  while (!targetNode.isLeafNode()) {
		  if (targetNode.isFull()) {
			  targetNode = split(targetNode);
		  }
		  targetNode = goNext(targetNode, key);
		  if (targetNode == null)
			  return;
	  }

	  // split leaf node if full
	  if (targetNode.isFull()) {
		  targetNode = split(targetNode);
	  }
	  while (!targetNode.isLeafNode()) {
		  targetNode = goNext(targetNode, key);
		  if (targetNode == null)
			  return;
	  }

	  if (key < targetNode.key1) {
		  targetNode.key3 = targetNode.key2;
		  targetNode.key2 = targetNode.key1;
		  targetNode.key1 = key;
	  } else if (key == targetNode.key1) {
		  return;
	  } else if(targetNode.keys == 1 || key < targetNode.key2){
		  targetNode.key3 = targetNode.key2;
		  targetNode.key2 = key;
	  } else if (key == targetNode.key2) {
		  return;
	  } else if (key == targetNode.key3) {
		  return;
	  } else {
		  targetNode.key3 = key;
	  }
	  targetNode.keys++;
  }

  private Tree234Node goNext(Tree234Node currentNode, int key) {
	  if (key < currentNode.key1) {
		  return currentNode.child1;
	  } else if (key == currentNode.key1) {
		  return null;
	  } else if ((currentNode.keys == 1) || (key < currentNode.key2)) {
		  return currentNode.child2;
	  } else if (key == currentNode.key2) {
		  return null;
	  } else if ((currentNode.keys == 2) || (key < currentNode.key3)) {
		  return currentNode.child3;
	  } else if (key == currentNode.key3) {
		  return null;
	  } else {
		  return currentNode.child4;
	  }
  }

  private Tree234Node split(Tree234Node node) {
	  int key = node.key2;
	  Tree234Node child1 = new Tree234Node(null, node.key1);
	  child1.child1 = node.child1;
	  child1.child2 = node.child2;
	  Tree234Node child2 = new Tree234Node(null, node.key3);
	  child2.child1 = node.child3;
	  child2.child2 = node.child4;
	  if (!node.isLeafNode()) {
		  node.child1.parent = child1;
		  node.child2.parent = child1;
		  node.child3.parent = child2;
		  node.child4.parent = child2;
	  }

	  if (node.isRootNode()) {
		  root = new Tree234Node(null, node.key2);
		  child1.parent = root;
		  child2.parent = root;
		  root.child1 = child1;
		  root.child2 = child2;
		  return root;
	  }

	  // here parent is guaranteed to be not full
	  Tree234Node parent = node.parent;
	  switch (parent.keys) {
		case 1: {
			if (parent.child1 == node)
				insertFront(parent, key, child1, child2);
			else
				insertBack(parent, key, child1, child2);
			break;
		}
		case 2: {
			if (parent.child1 == node)
				insertFront(parent, key, child1, child2);
			else if (parent.child2 == node)
				insertMiddle(parent, key, child1, child2);
			else
				insertBack(parent, key, child1, child2);
			break;
		}
	  }
	  return parent;
  }

  private void insertBack(Tree234Node mergingNode, int key, Tree234Node leftChild, Tree234Node rightChild) {
	  leftChild.parent = mergingNode;
	  rightChild.parent = mergingNode;
	  switch(mergingNode.keys) {
		  case 1: {
			  mergingNode.key2 = key;
			  mergingNode.child2 = leftChild;
			  mergingNode.child3 = rightChild;
			  break;
		  }
		  case 2: {
			  mergingNode.key3 = key;
			  mergingNode.child3 = leftChild;
			  mergingNode.child4 = rightChild;
			  break;
		  }
	  }
	  mergingNode.keys++;
  }

  private void insertFront(Tree234Node mergingNode, int key, Tree234Node leftChild, Tree234Node rightChild) {
	  leftChild.parent = mergingNode;
	  rightChild.parent = mergingNode;
	  if (mergingNode.keys == 2) {
		  mergingNode.key3 = mergingNode.key2;
		  mergingNode.child4 = mergingNode.child3;
	  }
	  mergingNode.key2 = mergingNode.key1;
	  mergingNode.key1 = key;
	  mergingNode.child3 = mergingNode.child2;
	  mergingNode.child2 = rightChild;
	  mergingNode.child1 = leftChild;
	  mergingNode.keys++;
  }

  private void insertMiddle(Tree234Node mergingNode, int key, Tree234Node leftChild, Tree234Node rightChild) {
	  leftChild.parent = mergingNode;
	  rightChild.parent = mergingNode;
	  if (mergingNode.keys != 2)
		  throw new RuntimeException("insert to middle requires 2 keys in merging node!");
	  mergingNode.key3 = mergingNode.key2;
	  mergingNode.child4 = mergingNode.child3;
	  mergingNode.key2 = key;
	  mergingNode.child3 = rightChild;
	  mergingNode.child2 = leftChild;
	  mergingNode.keys++;
  }

  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    testDuplicate(t);

    System.out.println("\nFinal tree:");
    t.printTree();
  }

  private static void testDuplicate(Tree234 t) {
	  String origin = t.toString();
	  System.out.println("\nDuplicating 80, 50, 37, 1.");
	  t.insert(80);
	  t.insert(50);
	  t.insert(37);
	  t.insert(1);
	  t.testHelper(origin);
  }

}
