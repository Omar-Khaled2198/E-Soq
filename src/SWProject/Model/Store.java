package SWProject.Model;



import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static SWProject.Control.SharedController.database;

public class Store implements IStore, Serializable {

  public Store()
  {
    usersExplored=0;
    numOfProducts=0;
    mostOrderedProduct=null;
  }

  public int storeID;

  public int usersExplored;

  public String ownerid;

  public String name;

  public int numOfUsersExplored=0;

  public int numOfProducts=0;

  public Product mostOrderedProduct;

  public int getStoreID() {
  return storeID;
  }

  public int getNumOfUsersExplored() {
  return numOfUsersExplored;
  }

  public int getNumOfProducts() {
  return numOfProducts;
  }

  public Brand getMostOrderedBrand() {
  return null;
  }

  public Product getMostOrderedProduct()
  {
    HashMap<Integer,Integer>most=new HashMap<>();
    for(int i=0;i<database.getTransactions().size();i++)
    {
       if(database.getTransactions().get(i).getStoreID()==storeID)
       {
         if(!most.containsKey(database.getTransactions().get(i).getProductID()))
           most.put(database.getTransactions().get(i).getProductID(),0);
         else
           most.put(database.getTransactions().get(i).getProductID(),most.get(database.getTransactions().get(i).getProductID())+1);
       }
    }
    System.out.println(most.size());
    if(most.size()==0)
      return null;
    return database.loadProduct( Collections.max(most.entrySet(), Map.Entry.comparingByValue()).getKey());
  }



  @Override
  public void Statistics() {

  }


  public void Store(int id) {
  }

  public void addProduct(Product product) {
  }

}