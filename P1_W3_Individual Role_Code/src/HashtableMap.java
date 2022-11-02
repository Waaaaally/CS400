// --== CS400 Project One File Header ==--
// Name: Philip Levin
// CSL Username: plevin
// Email: plevin3@wisc.edu
// Lecture #: 003
// Notes to Grader: 

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;

public class HashtableMap <KeyType, ValueType> implements MapADT <KeyType, ValueType>{

  Element [] hashTable;
  private int size = 0;
  private int capacity;
  
  //Initializes the Hashtable with a default capacity of 15
  public HashtableMap() { 
    this.capacity = 15;
    hashTable = new Element [15];
  }
  //Initializes the Hashtable with a capacity that is passed in the parameter
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    hashTable = new Element [capacity];
  }
  //Returns the capacity
  public int getCapacity() {
    return this.capacity;
  }
  //Checks to see if the size of the hashtable is more than 70% filled. If so, it doubles the hashtable and rehashes the values in the hashtable
  public void growth() {
    if ((float)size/(float)hashTable.length >= 0.7) { ////if size/length > 0.7, then double the capacity
      Element [] copyHash = new Element [capacity];
      for (int i = 0; i < capacity; i++) {
        copyHash [i] = hashTable [i];
      }
      hashTable = new Element[hashTable.length * 2]; //reinitialized new hashtable [hashtable.legnth * 2]
      capacity = capacity*2;
      for (int i = 0; i < copyHash.length; i++) { //traverse through copied array, skipped null values and rehashed
        if (copyHash[i] == null) {
          continue;
        }
        int index = Math.abs(copyHash[i].hashCode() )% hashTable.length;
        hashTable[index] = copyHash[i];
      }
    }   
  }
  
    /**
     * Inserts a new (key, value) pair into the map if the map does not
     * contain a value mapped to key yet.
     * 
     * @param key the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map,
     *         false if a mapping for key already exists and the
     *         new (key, value) pair could not be inserted
     */
     //@Override //
     public boolean put(Object key, Object value) {
      Element <KeyType, ValueType> newElement = new Element (key, value);
      Element <KeyType, ValueType> traverse = null; //Uses this element to traverse through the hashtable and determine where there is a space open
      if (key == null || this.containsKey((KeyType) key)) { //makes sure that parameter input is not null and that the hashtable doesn't already contain the key
        return false; 
      }
      int index = Math.abs(key.hashCode() )% hashTable.length; //gets correct index using hashCode()
      if (hashTable[index]==null) { //puts in value if the index is empty and updates size
        hashTable[index] = newElement;
        size++; 
      } else {
      traverse = hashTable [index];
        while (traverse.getNext() != null) { //traverses through the index's linkedlist and finds an open spot to put value and updates size
          traverse = traverse.getNext();
        }
        traverse.setNext(newElement); 
        size++;
      } 
      growth(); //checks to see the capacity needs to be doubled
      return true;
    }
    
    
    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     * 
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    public ValueType get(KeyType key) throws NoSuchElementException{
      Element <KeyType, ValueType> traverse = null;
      int index = Math.abs(key.hashCode())% hashTable.length;
      traverse = hashTable [index]; //finds the correct index to search
      
      while (traverse != null) { //traverses through the linkedlist while staying inbounds 
        if (traverse.getKey().equals(key)) { //continually checks to see if keys are equal
          return traverse.getValue(); //returns equal key
        }
        traverse = traverse.getNext();
      }
      throw new NoSuchElementException(); //assumes that it traverses through the linkedlist with no key equal so exception is throw
    }   
  
    /**
     * Removes a key and its value from the map. 
     * 
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    public ValueType remove(KeyType key) {
      Element <KeyType, ValueType> traverse = null;
      Element <KeyType, ValueType> preTraverse = null;
      ValueType rvalue;
      int index = Math.abs(key.hashCode())% hashTable.length;
      traverse = hashTable [index];
      if (traverse == null) { //checks to see if key exists
        return null;
      }
      if (traverse.getKey().equals(key)) { //checks to see if the first node in the index is equal to key, sets the index to null and adjusts size if needed
        rvalue = traverse.getValue();
        hashTable[index] = null;
        size--;
        return rvalue;
      }
      preTraverse = hashTable[index - 1]; //uses preTraverse to keep tabs on the node before the traverse for later
      while (traverse != null && preTraverse != null && !traverse.getKey().equals(key)) { //traverses through linkedlist while staying in bounds and making sure that the target key is not passed
        traverse = traverse.getNext(); //updates the traverse and pretraverse Elements in order to traverse
        preTraverse = preTraverse.getNext();
      }
        if (traverse.getKey() == key) { //if key is found, sets value to rvalue for return, sets that node to null and reconnects the linked list through the preTraverse and the traverse.getNext() and adjusts size
          rvalue = traverse.getValue();
          traverse = null;
          preTraverse.setNext(traverse.getNext());
          size--;
          return rvalue;
        }
      return null; //assumes that no key is found if it gets here so it returns null
      }
    
    /**
     * Checks if a key is stored in the map.
     * 
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    public boolean containsKey(KeyType key) {
      Element <KeyType, ValueType> traverse = null;
      int index = Math.abs(key.hashCode())% hashTable.length;
      traverse = hashTable [index];
      
      while (traverse != null) { //similar to the get method however instead of returning the valueType of the key, it simply returns true if it is found and false otherwise
        if (traverse.getKey().equals(key)) {
          return true;
        }
        traverse = traverse.getNext();
      }
      return false;
    }  
    
    /**
     * Returns the number of (key, value) pairs stored in the map.
     * 
     * @return the number of (key, value) pairs stored in the map
     */
    public int size() {
      return size;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() { //reinializes empty hashtable of same capacity and sets size to 0
      hashTable = new Element [hashTable.length];
      this.size = 0;
    }


    
}
