/* LockDListNode.java */

 

/**
 *  A LockDListNode is a node in a LockDList (doubly-linked list).
 */

public class LockDListNode extends DListNode{

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the LockDList.
   *  next references the next node in the LockDList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */
	public boolean locked;

  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  LockDListNode(Object i, DListNode p, DListNode n) {
	super(i,p,n);
    locked = false;
  }
}
