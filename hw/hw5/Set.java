/* Set.java */

import list.*;


/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
    private List setList;
    private int num;

  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
      setList = new DList();
      num = 0;
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return num;
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) throws InvalidNodeException {
    // Your solution here.
      if( setList.isEmpty()){
          setList.insertBack(c);
          num++;
      }
      else {
          ListNode current = setList.front();
          if(c.compareTo(current.item()) == 0)
              return;
          else if(c.compareTo(current.item()) < 0) {
              current.insertBefore(c);
              num++;
          }

          else {
              while(current.next().isValidNode()){
                  if(c.compareTo(current.next().item()) < 0)
                      break;
                  else if(c.compareTo(current.next().item())== 0)
                      return;
                  else
                      current = current.next();
              }
              current.insertAfter(c);
              num++;
          }
      }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) throws InvalidNodeException {
    // Your solution here.
      if( s.cardinality() == 0 )
          return;
      ListNode this_current = setList.front();
      ListNode s_current = s.setList.front();
      while( s_current.isValidNode() ) {
          if( this_current.isValidNode() ) {
              if( ((Comparable) this_current.item()).compareTo(s_current.item()) == 0 ) {
                  this_current = this_current.next();
                  s_current = s_current.next();
              }
              else if ( ((Comparable) this_current.item()).compareTo(s_current.item()) > 0 ) {
                  this_current.insertBefore( s_current.item() );
                  s_current = s_current.next();
                  num++;
              }
              else{
                  this_current = this_current.next();
              }
          }
          else {
              setList.insertBack( s_current.item() );
              s_current = s_current.next();
              num++;
          }
      }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) throws InvalidNodeException {
    // Your solution here.
      if(s.cardinality() == 0 || cardinality() == 0)
          return;
      ListNode s_current = s.setList.front();
      ListNode this_current = setList.front();
      while( this_current.isValidNode() ) {
          if( s_current.isValidNode() ) {
              if( ((Comparable) this_current.item()).compareTo(s_current.item()) == 0 ) {
                  s_current = s_current.next();
                  this_current = this_current.next();
              }
              else if( ((Comparable) this_current.item()).compareTo(s_current.item()) > 0 ) {
                  s_current = s_current.next();
              }
              else {
                  ListNode temp = this_current.prev();
                  this_current.remove();
                  num--;
                  this_current = temp.next();
              }
          }
          else {
              ListNode temp = this_current.next();
              this_current.remove();
              num--;
              this_current = temp;
              }
          }
      }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
      // Replace the following line with your solution.
      ListNode current = setList.front();
      String str = "{  ";
      try {
          while (current.isValidNode()) {
              str = str + current.item() + "  ";
              current = current.next();
          }
      }
      catch(InvalidNodeException a) {
          System.out.println("fuck you");
      }
      return str + "}";
  }


  public static void main(String[] argv) throws InvalidNodeException {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);
    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());

      Set s4 = new Set();
      System.out.println("Set s4 = " + s4);
      s.union(s4);
      System.out.println( "After s.intersect(s4), s must be { 3 5 }: " + s );
      s.intersect(s4);
      System.out.println( "After s.intersect(s4), s must be { 3 5 }: " + s );
      s4.union(s4);
      System.out.println( "After s4.union(s4), s4 must be { }: " + s4 );
      s4.intersect(s4);
      System.out.println("After s4.intersect(s4), s4 must be { }: " + s4 );
      System.out.println("s4.cardinality() = " + s4.cardinality());

      Set s5 = new Set();
      s5.insert(new Integer(1));
      s5.insert(new Integer(2));
      s5.insert(new Integer(3));
      System.out.println("Set s5 = " + s5);
      s.union(s5);
      System.out.println("After s.union(s5), s must be { 1 2 3 5 }: " + s );
      s.intersect(s5);
      System.out.println("After s.intersect(s5), s must be { 1 2 3 }: " + s );
    // You may want to add more (ungraded) test code here.
      System.out.println("s.cardinality() = " + s.cardinality());

  }
}
