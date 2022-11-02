// --== CS400 Project One File Header ==--
// Name: Philip Levin
// CSL Username: plevin
// Email: plevin3@wisc.edu
// Lecture #: 003
// Notes to Grader: 

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList; // import the ArrayList class


public class IteratorHashTable<KeyType, ValueType> extends HashtableMap <KeyType, ValueType> implements IterableMapADT<KeyType,ValueType>{
    int index = 0;
    
    Element <KeyType, ValueType> node = hashTable[index]; //Hashtable is array of linkedNodes
    public Iterator<ValueType> iterator() {  //Constructor for iterator
      Iterator <ValueType> iterator = null;  //new Iterator<ValueType>();
      return iterator;
    }
        public boolean hasNext() {
          if (size() == 0) { //Checks to see if Hashtable has anything in it
            return false;
          }
          if (node != null) { //First index has something so checks to see if it's next has something, if so returns true
            if (node.getNext() != null) {
              return true;
            }
           }
           while (index < getCapacity()) { //If first index is empty, traverses Hashtable to find an index with something making sure that index doesn't exceed capacity
            if (node == null) {
             index++;
            }
            return true;
           }
           return false;
        }

        public ValueType next() throws NoSuchElementException{ 
          if (hasNext()) { //Checks to see if there is a next
            if (node != null) { //Checks to see if first index is empty
              if (node.getNext() != null) { //Assuming first index is not empty, checks to see if it's next has a value, if so returns that value
                Element <KeyType, ValueType> rValue = node.getNext(); //Sets a temp return value, makes the current node the next for further traversal and than returns the return value
                node = node.getNext();
                return (ValueType) rValue.getNext().getValue(); 
              }
            }
            while (index < getCapacity()) { //Similar to hasNext(), traverses Hashtable looking for index that is not empty and returns a return value if found
             while (node == null) {
             index++;
             node = hashTable[index];
             }
             Element <KeyType, ValueType> rValue = node;
             node = node.getNext();
             return (ValueType) rValue.getValue(); 
            }
          }
            throw new NoSuchElementException(); //Throws no such element exception if you are at the end of the Hashtable, or the Hashtable doesn't have a next
          }
          
        }

 
 
