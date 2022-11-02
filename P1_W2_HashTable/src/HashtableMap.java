import com.sun.jdi.Value;

import java.util.NoSuchElementException;
import java.util.LinkedList;

// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader:

public class HashtableMap <KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    protected LinkedList<KeyValuePair>[] hashtableMap;
    private int size;
    //This was endorsed by an instructor on piazza so idno. Better than iterating or figuring out
    //THE OPTIMAL HASH SIZE METHODOLOGY for my brain. also gotta go fast

    public HashtableMap(){ // with default capacity = 15
        hashtableMap = (LinkedList<KeyValuePair>[]) new LinkedList[15];
        for(int i = 0; i < 15; i++){
            hashtableMap[i] = new LinkedList<KeyValuePair>();
        }
        size = 0;
    }
    public HashtableMap(int capacity){
        hashtableMap = (LinkedList<KeyValuePair>[]) new LinkedList[capacity];
        for(int i = 0; i < capacity; i++){
            hashtableMap[i] = new LinkedList<KeyValuePair>();
        }
        size = 0;
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
    public boolean put(Object key, Object value) {
        if(key == null)
            return false;

        int objectKey = key.hashCode(); //preparing to hash
        int index = Math.abs(objectKey) % hashtableMap.length;
        KeyValuePair kvp = new KeyValuePair(key, value);

        for(KeyValuePair kvps : hashtableMap[index]){ //check for redundant key
            if(kvps != null && kvps.getKey().equals(key)) //could use containsKey() but wrote 1st
                return false;
        }

        size++;
        double loadFactor = ((double)size())/(double)hashtableMap.length;
        if(loadFactor >= 0.7) {
            hashtableMap = rehash();
        }

        return hashtableMap[index].add(kvp);
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping for the key
     */
    public Object get(Object key) throws NoSuchElementException {
        if(key == null)
            throw new NoSuchElementException("Key dne bc null");

        int objectKey = key.hashCode(); //get hashcode
        int index = Math.abs(objectKey) % hashtableMap.length;

        for(KeyValuePair kvps : hashtableMap[index]){ //check for matching key
            if(kvps != null && kvps.getKey().equals(key)) //key matched
                return kvps.getValue();
        }

        throw new NoSuchElementException("Key never found");
    }

    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    public Object remove(Object key) {
        if(key == null)
            return null;

        int objectKey = key.hashCode(); //get hashcode
        int index = Math.abs(objectKey) % hashtableMap.length;

        for(KeyValuePair kvps : hashtableMap[index]){
            if(kvps != null && kvps.getKey().equals(key)){
                Object value = kvps.getValue(); //temp value to return bc no more after removed
                hashtableMap[index].remove(kvps);
                size--;
                return value;
            }
        }
        return null;
    }

    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    public boolean containsKey(Object key) {
        if(key == null)
            return false;

        int objectKey = key.hashCode(); //getting hashcode
        int index = Math.abs(objectKey) % hashtableMap.length;

        for(KeyValuePair kvps : hashtableMap[index]){ //check for redundant key @ index of LLs
            if(kvps != null && kvps.getKey().equals(key))
                return true;
        }
        return false;
    }

    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    public int size() {
//        int counter = 0; I
//        for(int i = 0; i < hashtableMap.length; i++){
//            if(hashtableMap[i].getFirst() != null){ //hmm?
//                for(KeyValuePair kvps : hashtableMap[i]){
//                    counter++;
//                }
//            }
//        }
        return size;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() {
        for (int i = 0; i < hashtableMap.length; i++){
            hashtableMap[i].clear(); //we love inbuilt methods. LLs collapse on themselves
            //like dominos so just clear from the head
        }
        size = 0;
    }

    private LinkedList<KeyValuePair>[] rehash(){
        LinkedList<KeyValuePair>[] hashtableMapNew =
                (LinkedList<KeyValuePair>[]) new LinkedList[hashtableMap.length*2]; //declare

        for(int i = 0; i < hashtableMapNew.length; i++){
            hashtableMapNew[i] = new LinkedList<KeyValuePair>();
        }

        for(int i = 0; i < hashtableMap.length; i++) { //iterate through entire table
            //probably a top 1% quartile method to not have to but im not there mentally
                for(KeyValuePair kvp: hashtableMap[i]){

                    int keyHashCode = kvp.getKey().hashCode(); //rehash
                    int index = Math.abs(keyHashCode) % hashtableMapNew.length;

                    hashtableMapNew[index].add(kvp);
                }
        }
        return hashtableMapNew;
    }
}
