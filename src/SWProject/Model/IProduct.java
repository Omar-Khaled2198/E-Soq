package SWProject.Model;

import java.util.Vector;

public interface IProduct {

  public int getProductId();

  public int getQuantity();

  public Double getUnitPrice();

  public Double getTotalCost();

  public void setId(int id);

  public void setProductId(int id);

  public void setQuantity(int quantity);

  public void setUnitPrice(Double uPrice);

}