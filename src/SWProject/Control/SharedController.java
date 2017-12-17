package SWProject.Control;

import SWProject.Model.*;
import SWProject.View.ViewController;


import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SharedController
{
   public static Database database=new Database();
   public static ShopOwner sharedShopOwner=null;
   public static NormalUser sharedNormalUser=null;
   public static Admin sharedAdmin=null;
   public static ShoppingCart sharedshoppingCart;
   ViewController viewController=new ViewController();

   public void readData() throws IOException, ClassNotFoundException {
      database.read("Users");
      database.read("Products");
      database.read("Stores");
      database.read("Carts");
      database.read("Transactions");
      database.read("Brands");
      database.read("Suggests");
      database.read("Vouchers");
   }

}
