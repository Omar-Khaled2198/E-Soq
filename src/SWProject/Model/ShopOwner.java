package SWProject.Model;


import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

import static SWProject.Control.SharedController.database;

public class ShopOwner extends User {

  public void addStore(Store store) throws IOException {
     store.ownerid=userID;

      if(database.getStores().size()==0)
          store.storeID=0;
      else
          store.storeID=database.getStores().get(database.getStores().size()-1).getStoreID()+1;
     database.addStore(store);
  }

  public void removeStore(int storeID) throws IOException {
    for(int i=0;i<database.getProducts().size();i++)
    {
       if(database.getProducts().get(i).storeID==storeID)
       {
         database.removeProduct(database.getProducts().get(i).productID);
       }
    }
    database.removeStore(storeID);
    database.write("Products");

  }

  public void addProduct(Product product,int storeID) throws IOException {

      if(database.getProducts().size()==0)
          product.productID=0;
      else
          product.productID=database.getProducts().get(database.getProducts().size()-1).productID+1;
    for(Store store:database.getStores())
    {
      if(store.storeID==storeID)
      {
        store.numOfProducts++;
        break;
      }
    }
    product.storeID=storeID;
    database.addProduct(product);
    database.write("Products");
    database.write("Stores");
  }

  public void removeProduct(int productID,int storeID) throws IOException {
     for(Product product:database.getProducts())
     {
       if(product.productID==productID&&product.storeID==storeID)
       {
         database.getProducts().remove(product);
         database.write("Products");
         return;
       }
     }
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Product doesn't Exists");
      alert.showAndWait();
  }

  public void suggestProduct(Product product)
  {

  }

  public void suggestBrand(Brand brand)
  {

  }

}