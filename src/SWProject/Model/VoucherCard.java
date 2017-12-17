package SWProject.Model;

import java.io.Serializable;

public class VoucherCard extends Payment implements Serializable{

  private int ID;
  private String userID;
  private int value;

  public String getUserID() {
    return userID;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}