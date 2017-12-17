package SWProject.Model;


import java.io.Serializable;
import java.lang.String;

public class Brand implements Serializable {

  public String name;
  public int brandID;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBrandID() {
    return brandID;
  }

  public void setBrandID(int brandID) {
    this.brandID = brandID;
  }
}