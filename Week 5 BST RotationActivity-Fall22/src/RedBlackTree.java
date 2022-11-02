import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;
// --== CS400 Fall 2022 File Header Information ==--
// Name: Pritish Das
// Email: pdas26@wisc.edu
// Team: DN Red
// TA: Rahul Konchada
// Lecturer: Florian Heimerl
// Notes to Grader: 

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a
 * level-order
 * traversal of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree
   * the parent, left, and right child references are always maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public int blackHeight; //0 for red, 1 for black, 2 for double black

    public Node(T data) {
      this.data = data;
      blackHeight = 0; //every new node is red immedietely
    }

    /**
     * @return true when this node has a parent and is the left child of
     * that parent, otherwise return false
     */
    public boolean Child() {
      return parent != null && parent.leftChild == this;
    }

  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input
   * data value to a new node in a leaf position within the tree. After
   * this insertion, no attempt is made to restructure or balance the tree.
   * This tree will not hold null references, nor duplicate data values.
   *
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain
   *                                  equal data references
   */
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException(
              "This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      size++;
      root.blackHeight = 1;
      return true;
    } // add first node to an empty tree
    else {
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      if (returnValue)
        size++;
      else
        throw new IllegalArgumentException(
                "This RedBlackTree already contains that value.");

      root.blackHeight = 1;
      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the
   * position that the newNode should be inserted, and then extend this tree
   * by the newNode in that position.
   *
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the
   *                newNode should be inserted as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      return false;

      // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode); //TODO Not sure if right?
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode); //TODO Not sure if right?
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * The job of this enforceRBTreePropertiesAfterInsert method is to resolve any red-black tree
   * property violations that are introduced by inserting each new new node into a red-black tree.
   *
   * @param childNode
   */
  protected void enforceRBTreePropertiesAfterInsert(Node childNode) {
    Node parent = null;
    Node grandparent = null;
    Node aunt = null;
    boolean triangle = false;

    if (childNode.parent != null)
      parent = childNode.parent;

    //while child & parent are red
    while (childNode.blackHeight == 0 && childNode.parent != null && childNode.parent.blackHeight == 0) {
      if (childNode.parent != null)
        parent = childNode.parent;

      if (childNode.parent.parent != null) //could replace w/ parent.parent
        grandparent = childNode.parent.parent;

      if (grandparent != null && grandparent.leftChild != null && grandparent.leftChild.equals(childNode.parent)) {
        if (grandparent.rightChild != null) {
          aunt = grandparent.rightChild;
        }
      } else if (grandparent != null && grandparent.rightChild != null && grandparent.rightChild.equals(childNode.parent)) {
        if (grandparent.leftChild != null) {
          aunt = grandparent.leftChild;
        }
      }

      //Needed for Triangle Case
      if (grandparent != null) {
        //Triangle formation of C, P, & G.
        /*     G          G
              /            \
             P      Or      P
              \            /
               C          C
         */
        if (grandparent.leftChild.rightChild == childNode || grandparent.rightChild.leftChild == childNode)
          triangle = true;
      }

       /* 4 Cases
        0. Root Case(Turn root to black) - Already Taken Care Of In Insert
        1. ChildNode's Aunt/Uncle = Red
        2. ChildNode's Aunt/Uncle = Black (Triangle)
        3. ChildNode's Aunt/Uncle = Black (Line)
      */

      //Red Aunt Case (1) - Simple Recolor, & then check the Grandparent next
      if (aunt.blackHeight == 0) {
        parent.blackHeight = 1;
        aunt.blackHeight = 1;

        grandparent.blackHeight = 0;

        childNode = grandparent;
      }
      //Triangle Case (2) - Rotate into line formation
      else if (triangle && aunt.blackHeight == 1) {
        //prepping for line case. Curr Child's P becomes the Child when rotated into a line
        Node nextChildRef = parent;
        rotate(childNode, parent);
        childNode = nextChildRef;
      }
      //Line Case (3) - Rotate P & G and Recolor/Swap
      else if (parent.blackHeight == 0 && (aunt == null || aunt.blackHeight == 1)) {
        rotate(parent, grandparent);
        int tempBlackHeight = grandparent.blackHeight; //Basic swap algorithm
        grandparent.blackHeight = parent.blackHeight;
        parent.blackHeight = tempBlackHeight;
        //No more resolutions needed after line case.
      }
    }


  }

  /**
   * Performs the rotation operation on the provided nodes within this tree.
   * When the provided child is a leftChild of the provided parent, this
   * method will perform a right rotation. When the provided child is a
   * rightChild of the provided parent, this method will perform a left rotation.
   * When the provided nodes are not related in one of these ways, this method
   * will throw an IllegalArgumentException.
   *
   * @param child  is the node being rotated from child to parent position
   *               (between these two node arguments)
   * @param parent is the node being rotated from parent to child position
   *               (between these two node arguments)
   * @throws IllegalArgumentException when the provided child and parent
   *                                  node references are not initially
   *                                  (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    Node<T> grandparent = null;

    if (child == null || parent == null) {
      throw new IllegalArgumentException("Neither node is allowed to be null");
    }

    if (parent.parent != null)
      grandparent = parent.parent; // for later

    if (parent.leftChild != null && parent.leftChild.equals(child)) { // perform right rotation
      parent.leftChild = child.rightChild;

      if (child.rightChild != null)
        child.rightChild.parent = parent;

      child.rightChild = parent; // parent now "child's" right child
      // CHILD IS NOW THE "ROOT" OF THIS ROTATION
      child.rightChild.parent = child; // "parent's" parent set to "child"

      if (grandparent != null) {
        child.parent = grandparent;
        if (grandparent.leftChild.equals(parent))
          grandparent.leftChild = child;
        else
          grandparent.rightChild = child;
      } else {
        root = child;
      }
    } else if (parent.rightChild != null && parent.rightChild.equals(child)) { // perform left
      // rotation
      parent.rightChild = child.leftChild;

      if (child.leftChild != null)
        child.leftChild.parent = parent;

      child.leftChild = parent;
      // CHILD IS NOW THE "ROOT" OF THIS ROTATION
      child.leftChild.parent = child;

      if (grandparent != null) {
        child.parent = grandparent;
        if (grandparent.leftChild.equals(parent))
          grandparent.leftChild = child;
        else
          grandparent.rightChild = child;
      } else {
        root = child; // bc theres no grandparent, we at the top
      }

    } else {
      throw new IllegalArgumentException("Give Nodes aren't child and parent");
    }

  }

  /**
   * Get the size of the tree (its number of nodes).
   *
   * @return the number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   *
   * @return true of this.size() return 0, false if this.size() > 0
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   *
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null)
      throw new NullPointerException(
              "This RedBlackTree cannot store null references.");
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks
   * for the value *data*.
   *
   * @param data    the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * This method performs an inorder traversal of the tree. The string
   * representations of each data value within this tree are assembled into a
   * comma separated string within brackets (similar to many implementations
   * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
   * Note that this RedBlackTree class implementation of toString generates an
   * inorder traversal. The toString of the Node class class above
   * produces a level order traversal of the nodes / values of the tree.
   *
   * @return string containing the ordered values of this tree (in-order
   * traversal)
   */
  public String toInOrderString() {
    // generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    sb.append(toInOrderStringHelper("", this.root));
    if (this.root != null) {
      sb.setLength(sb.length() - 2);
    }
    sb.append(" ]");
    return sb.toString();
  }

  private String toInOrderStringHelper(String str, Node<T> node) {
    if (node == null) {
      return str;
    }
    str = toInOrderStringHelper(str, node.leftChild);
    str += (node.data.toString() + ", ");
    str = toInOrderStringHelper(str, node.rightChild);
    return str;
  }

  /**
   * This method performs a level order traversal of the tree rooted
   * at the current node. The string representations of each data value
   * within this tree are assembled into a comma separated string within
   * brackets (similar to many implementations of java.util.Collection).
   * Note that the Node's implementation of toString generates a level
   * order traversal. The toString of the RedBlackTree class below
   * produces an inorder traversal of the nodes / values of the tree.
   * This method will be helpful as a helper for the debugging and testing
   * of your rotation implementation.
   *
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    String output = "[ ";
    if (this.root != null) {
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this.root);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
    }
    return output + " ]";
  }

  public String toString() {
    return "level order: " + this.toLevelOrderString() +
            "\nin order: " + this.toInOrderString();
  }

  // Implement at least 3 boolean test methods by using the method signatures
  // below,
  // removing the comments around them and addind your testing code to them. You
  // can
  // use your notes from lecture for ideas on concrete examples of rotation to
  // test for.
  // Make sure to include rotations within and at the root of a tree in your test
  // cases.
  // If you are adding additional tests, then name the method similar to the ones
  // given below.
  // Eg: public static boolean test4() {}
  // Do not change the method name or return type of the existing tests.
  // You can run your tests by commenting in the calls to the test methods

  // simple right rotation
  // public static boolean test1() {
  // RedBlackTree redBlackTree = new RedBlackTree();
  // redBlackTree.insert(5);
  // redBlackTree.insert(9);
  // redBlackTree.insert(3);
  // redBlackTree.insert(2);
  // redBlackTree.insert(4);

  // redBlackTree.rotate(redBlackTree.root.leftChild, redBlackTree.root);
  // String toLvlOrder = redBlackTree.toLevelOrderString();

  // if (!toLvlOrder.equals("[ 3, 2, 5, 4, 9 ]")) {
  // System.out.println("Rotation Failed");
  // return false;
  // }
  // return true;
  // }

  // // left rotation at root
  // public static boolean test2() {
  // RedBlackTree redBlackTree = new RedBlackTree();
  // redBlackTree.insert(8);
  // redBlackTree.insert(6);
  // redBlackTree.insert(10);
  // redBlackTree.insert(9);
  // redBlackTree.insert(12);

  // redBlackTree.rotate(redBlackTree.root.rightChild, redBlackTree.root);
  // String toLvlOrder = redBlackTree.toLevelOrderString();

  // if (!toLvlOrder.equals("[ 10, 8, 12, 6, 9 ]")) {
  // System.out.println("Rotation Failed");
  // return false;
  // }
  // return true;
  // }

  // // long chain to the right
  // public static boolean test3() {
  // RedBlackTree redBlackTree = new RedBlackTree();
  // redBlackTree.insert(5);
  // redBlackTree.insert(6);
  // redBlackTree.insert(7);
  // redBlackTree.insert(8);
  // redBlackTree.insert(3);

  // redBlackTree.rotate(redBlackTree.root.rightChild.rightChild,
  // redBlackTree.root.rightChild);
  // String toLvlOrder = redBlackTree.toLevelOrderString();

  // if (!toLvlOrder.equals("[ 5, 3, 7, 6, 8 ]")) {
  // System.out.println("Rotation Failed");
  // return false;
  // }

  // return true;
  // }

  /**
   * Main method to run tests. Comment out the lines for each test
   * to run them.
   *
   * @param args
   */
  public static void main(String[] args) {

  }

}
