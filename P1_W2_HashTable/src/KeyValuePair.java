// --== CS400 Project One File Header ==--
// Name: Pritish Das
// CSL Username: pritish
// Email: pdas26@wisc.edu
// Lecture #: 003 @2:25pm
// Notes to Grader: Objects and how they're Oriented in my Program make me smile

public class KeyValuePair <KeyType, ValueType>{
  private KeyType key;
  private ValueType value;

  public KeyValuePair(KeyType key, ValueType value){
    this.key = key;
    this.value = value;
  }

  public KeyType getKey() {
    return key;
  }

  public ValueType getValue() {
    return value;
  }

  public void setKey(KeyType key) {
    this.key = key;
  }

  public void setValue(ValueType value) {
    this.value = value;
  }
}
