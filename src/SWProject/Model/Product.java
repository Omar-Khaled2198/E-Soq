package SWProject.Model;


import java.io.Serializable;
import java.util.Vector;

public class Product implements IProduct,Serializable {


  public String name;

  public int storeID;

  public int productID;

  public int quantity;

  public Double price;

  public String brand;

  public String desciption;

  public Vector  contains;

  public int getProductId() {
  return 0;
  }

  public int getQuantity() {
  return quantity;
  }

  public Double getUnitPrice() {
  return price;
  }

  public Double getTotalCost() {
  return null;
  }

  public void setId(int id) {
  }

  public void setProductId(int id) {
  }

  public void setQuantity(int quantity)
  {
    this.quantity=quantity;
  }

  public void setUnitPrice(Double uPrice)
  {
    this.price=uPrice;
  }

}