package em.au.basemvpjava.data.model;

/**
 * Created by Wageesha Chinthaka on 18/02/2019
 */
public class User {

  public int id;
  public String token;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
