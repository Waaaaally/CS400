// --== CS400 Project One File Header ==--
// Name: Philip Levin
// CSL Username: plevin
// Email: plevin3@wisc.edu
// Lecture #: 003
// Notes to Grader: 

//This class is intended as the node class that has getters and setters that are all used for the chaining element of this project in my HashtableMap.java class
public class Element <KeyType, ValueType> {
  private KeyType key;
  private ValueType value;
  private Element next;
  
  //Returns the key
  public KeyType getKey() {
    return this.key;
  }
  //Sets the key with parameter
  public void setKey(KeyType key) {
    this.key = key;
  }
  //Returns value
  public ValueType getValue() {
    return this.value;
  }
  //Sets value with parameter
  public void setValue(ValueType value) {
    this.value = value;
  }
  //Gets the next Element
  public Element getNext() {
    return this.next;
  }
  //Sets the next Element with parameter
  public void setNext(Element next) {
    this.next = next;
  }
  //Constructor
  public Element(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
    this.next = null;   
  }
}

