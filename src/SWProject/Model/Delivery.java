package SWProject.Model;

import java.io.Serializable;

public class Delivery extends Payment implements Serializable {

  public String address;

  public void setAddress(String address)
  {
     this.address=address;
  }

}