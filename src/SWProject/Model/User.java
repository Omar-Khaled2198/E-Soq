package SWProject.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class User implements IUser,Serializable {

  public String userID;

  public String userName;

  public String password;

  public int age;

  public String gender;

  public String email;

  public Date lastSession;

  public Vector myNormalUser;

  public int getUserId() {
  return 0;
  }

  public String getUserName() {
  return null;
  }

  public String getUserPassword() {
  return null;
  }

  public Date getUserLastSession() {
  return null;
  }

  public void login() {
  }

  public void logout() {
  }

  public void register(String userid, String password) {
  }

}