package SWProject.Model;


import java.util.Date;
import java.util.Vector;

public interface IShoppingCart {

  public Vector myIProduct = null;

  public int getId();

  public int getSessionId();

  public int getCustomerId();

  public void addToCart(int productID,int quantity);

  public void updateQuantity(int cartItemId, int newQuantity);

  public void removeItem(int cartItemId);

  public Product getItem(int productID);

  public int countItems();

  public Date getLastAccessedDate();

}