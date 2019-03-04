package hello.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Session {
  @Id
  public ObjectId _id;
  
  public String token;
  
  // Constructors
  public Session() {}
  
  public Session(ObjectId _id, String token, String species, String breed) {
    this._id = _id;
    this.token = token;
  }
  
  // ObjectId needs to be converted to string
  public String get_id() { return _id.toHexString(); }
  public void set_id(ObjectId _id) { this._id = _id; }
  
  public String getToken() { return token; }
  public void setToken(String token) { this.token = token; }
}