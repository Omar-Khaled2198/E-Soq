package SWProject.Model;


import java.io.Serializable;
import java.util.Date;

public class Visa extends Payment implements Serializable {

  public int cardId;

  public int code;

  public Date expDate;

  public int secretWord;

  public void setData(int cardid, Date expDate, int secWord) {
  }

  public boolean validateData() {
  return false;
  }

  public boolean takePrice() {
  return false;
  }

}