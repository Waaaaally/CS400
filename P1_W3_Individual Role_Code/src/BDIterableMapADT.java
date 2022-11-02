import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BDIterableMapADT <String, IBook> implements IterableMapADT <String, IBook> {


    @Override
    /**
     * Placeholder method for Iterator
     */
    public Iterator <IBook> iterator() {
        ArrayList<IBook> books = new ArrayList<IBook>();
        IBook tBook0 = (IBook) new Book("title0","authors0", "isbn0");
        IBook tBook1 = (IBook) new Book("title1","authors1", "isbn1");
        IBook tBook2 = (IBook) new Book("title2","authors2", "isbn2");
        books.add((IBook)tBook0);
        books.add((IBook)tBook1);
        books.add((IBook)tBook2);
        return books.iterator();
    }

    private class HashtableHelper <String, IBook> {
        private IBook value;
        private String key;
        private HashtableHelper<String, IBook> nextElement;

        /**
         * Constructor that takes key and value
         * @param key
         * @param value
         */
        HashtableHelper(String key, IBook value) {
            this.value = value;
            this.key = key;
        }

        /**
         * getter method for value
         * @return
         */
        public IBook getValue() {
            return this.value;
        }

        /**
         * getter method for key
         * @return
         */
        public String getKey() {
            return this.key;
        }

        /**
         * getter method for next
         * @return
         */
        public HashtableHelper<String, IBook> getNextElement() {
            return this.nextElement;
        }

        /**
         * setter method for next
         * @param nextElement
         */
        public void setNextElement(HashtableHelper<String, IBook> nextElement) {
            this.nextElement = nextElement;
        }

    }

    protected HashtableHelper[] hashTab;
    private int capacity;
    private int size;
    private double ldFactor = 0.7;

    /**
     * Constructor for class where capacity is assigned
     * @param capacity
     */
    public BDIterableMapADT(int capacity) {
        this.capacity = capacity;
        hashTab = new HashtableHelper[capacity];
    }

    /**
     * Constructor for class where capacity is auto-assigned to 15
     */
    public BDIterableMapADT() {
        this.capacity = 15;
        hashTab = new HashtableHelper[capacity];
    }

    /**
     * sets capacity of array
     * @param capacity
     */
    private void setCapacity (int capacity) {
        this.capacity = capacity;
    }

    /**
     * sets size of array
     * @param size
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * returns load factor of array
     * @return
     */
    private double getLdFactor() {
        return ldFactor;
    }

    /**
     * gets capacity of array
     * @return
     */
    private int getCapacity() {
        return capacity;
    }
    private int hashFun(String key) {
        return (Math.abs(key.hashCode())) % getCapacity();
    }


    @Override
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
    public boolean put(String key, IBook value) {
        if (value == null || key == null || containsKey(key)) {
            return false;
        }
        int index = hashFun(key);
        if(hashTab[index] == null) {
            hashTab[index] = new HashtableHelper(key,value);
        }
        else {
            HashtableHelper top = hashTab[index];
            while(top.getNextElement() != null) {
                top = top.getNextElement();
            }
            top.setNextElement(new HashtableHelper(key,value));
        }
        setSize(size() + 1);
        reHashed();
        return true;
    }

    @Override
    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    public IBook get(String key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException();
        }
        int index = hashFun(key);
        if (hashTab[index] == null) {
            throw new NoSuchElementException();
        }
        else {
            HashtableHelper top = hashTab[index];
            if(top.getKey().equals(key)) {
                return (IBook)top.getValue();
            }
            while(top != null) {
                if(top.getKey().equals(key)) {
                    return (IBook)top.getValue();
                }
                top = top.getNextElement();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    public IBook remove(String key) {
        if(!containsKey(key) || key == null) {
            return null;
        }

        IBook answer;
        int index = hashFun(key);
        HashtableHelper top = hashTab[index];
        if(top.getKey().equals(key)) {
            hashTab[index] = top.getNextElement();
            setSize(size() - 1);
            return (IBook)top.getValue();
        }
        while(top.getNextElement() != null) {
            if(top.getNextElement().getKey().equals(key)) {
                answer = (IBook)top.getValue();
                top.setNextElement(top.getNextElement().getNextElement());
                setSize(size() - 1);
                return answer;
            }
            top = top.getNextElement();
        }
        return null;
    }

    @Override
    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    public boolean containsKey(String key) {
        int index = hashFun(key);
        if(hashTab[index] == null) {
            return false;
        }
        else {
            HashtableHelper top = hashTab[index];
            while(top != null) {
                if(top.getKey().equals(key)) {
                    return true;
                }
                top = top.getNextElement();
            }
        }
        return false;
    }

    @Override
    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() {
        // TODO Auto-generated method stub
        for(int i = 0; i < capacity; i++) {
            hashTab[i] = null;
        }
        size = 0;
    }

    /**
     * if Load Factor is met, this method rehashes the array
     */
    private void reHashed() {
        if((double)(size()) / (double)(getCapacity()) >= getLdFactor()) {
            HashtableHelper[] newHashTab = new HashtableHelper[getCapacity()];

            for(int i = 0; i < getCapacity(); i++) {
                newHashTab[i] = hashTab[i];
            }
            setCapacity(getCapacity() * 2);
            hashTab = new HashtableHelper[getCapacity()];
            setSize(0);

            for(int i = 0; i < getCapacity()/2; i++) {
                if(newHashTab[i] != null) {
                    put((String)newHashTab[i].getKey(), (IBook)newHashTab[i].getValue());

                    HashtableHelper top = newHashTab[i];
                    while(top.getNextElement() != null) {
                        top = top.getNextElement();
                        put((String) top.getKey(), (IBook)top.getValue());
                    }
                }
            }
        }
    }
}
