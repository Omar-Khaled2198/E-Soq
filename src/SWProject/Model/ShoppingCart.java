package SWProject.Model;


import javafx.scene.control.Alert;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import static SWProject.Control.SharedController.database;

public class ShoppingCart implements IShoppingCart,Serializable {

  public String userID;

  public double totalCost;
  public ArrayList<Product>items;


  public ShoppingCart()
  {
    items=new ArrayList<>();
    totalCost=0;
  }




  public int getId() {
  return 0;
  }

  public int getSessionId() {
  return 0;
  }

  public int getCustomerId() {
  return 0;
  }

  public void addToCart(int productID,int quantity)
  {
    if(items.size()==0)
      totalCost=0;
    Product temp=database.loadProduct(productID);;
    Product item=new Product();
    if (temp == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Product doesn't Exists");
      alert.showAndWait();
    }
    else
    {
      item.productID=temp.productID;
      item.storeID=temp.storeID;
      item.quantity=quantity;
      item.price=temp.price;
      item.name=temp.name;
      item.brand=temp.brand;
      if(quantity<=temp.quantity)
      {
        items.add(item);
        totalCost+=item.quantity*item.price;
        try {
          database.updateShoppingCarts(this);
        } catch (IOException e) {
          e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Done");
        alert.showAndWait();
      }
      else
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Product Quantity not enough!");
        alert.showAndWait();
      }
    }
  }

  public void updateQuantity(int cartItemId, int newQuantity)
  {

  }

  public void removeItem(int productid)
  {
    for(int i=0;i<items.size();i++)
    {
      if(items.get(i).productID==productid)
      {
        totalCost-=items.get(i).price*items.get(i).quantity;
        items.remove(i);
        try {
          database.updateShoppingCarts(this);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }
    }
  }

  public Product getItem(int productID)
  {
    for(int i=0;i<items.size();i++)
    {
       if(items.get(i).productID==productID)
       {
         return items.get(i);
       }
    }
    return null;
  }

  public int countItems() {
  return items.size();
  }


  public double getTotalCost()
  {
    return totalCost;
  }
  public Date getLastAccessedDate() {
  return null;
  }

  public void ShoppingCart(int id, int customerId, int sessionId) {
  }

}