/* HashTableChained.java */

package dict;

import list.DList;
import list.DListNode;
import list.InvalidNodeException;
import list.ListNode;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
    private int N;
    private DList[] bucket;
    private int num;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
  private static boolean isPrime(int num) {
      if( num == 2 || num == 3 )
          return true;
      for(int i = 2; i < (int) Math.sqrt(num) + 1;i++ ) {
          if( num % i == 0 )
              return false;
      }
      return true;
  }

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
      num = 0;
      int i = (int)((double)sizeEstimate / 0.75);
      while(true) {
          if(isPrime(i)) {
              N = i;
              break;
          }
          i++;
      }
      bucket = new DList[N];
      for( int j = 0; j < N; j++ ){
          bucket[j] = new DList();
      }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
      this(100);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return (( 3 * code + 5) % 16908799 + 16908799 ) % N;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return num;
  }

  public int bucketSize() {
      return N;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return num ==  0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
      int index = compFunction( key.hashCode() );
      Entry newEntry = new Entry();
      newEntry.key = key;
      newEntry.value = value;
      bucket[index].insertBack(newEntry);
      num++;
      return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
      // Replace the following line with your solution.
      int index = compFunction(key.hashCode());
      try {
          Entry newEntry = new Entry();
          ListNode current = bucket[index].front();
          while( !((Entry)current.item()).key().equals(key) ) {
              current = current.next();
          }
          newEntry.key = ((Entry) current.item()).key();
          newEntry.value = ((Entry) current.item()).value();
          return newEntry;
      }
      catch (InvalidNodeException a) {
          return null;
      }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
      int index = compFunction(key.hashCode());
      try {
          Entry newEntry = new Entry();
          ListNode current = bucket[index].front();
          while( !((Entry)current.item()).key().equals(key) ) {
              current = current.next();
          }
          newEntry.key = ((Entry) current.item()).key();
          newEntry.value = ((Entry) current.item()).value();
          current.remove();
          num--;
          return newEntry;
      }
      catch (InvalidNodeException a) {
          return null;
      }
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
      for( int i = 0; i < N; i++ ) {
          bucket[i] = new DList();
      }
      num = 0;
  }

  public int numberOfCollisions(){
      int count = 0;
      for( int i = 0; i < N; i++ ) {
          if( bucket[i].length() > 1 ) {
              count = count + bucket[i].length() - 1;
          }
      }
      return count;
  }

  public double ExpectedCollisions() {
      double count;
      count = num - N + N * Math.pow( 1 - 1.0/N, num );
      return count;
  }

  public DList[] bucket() {
      return bucket;
  }
}
