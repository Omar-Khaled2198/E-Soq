package SWProject.Model;

import static SWProject.Control.SharedController.database;

public class Admin extends User {

  public void manageBrands() {
  }

  public void storeData() {
  }

  public void provideVoucherCard() {
  }

  public void addProduct(Product product)
  {
    if(database.getProducts().size()==0)
      product.productID=0;
    else
      product.productID=database.getProducts().get(database.getProducts().size()-1).productID+1;
    product.storeID=-1;
    database.addProduct(product);
  }


}