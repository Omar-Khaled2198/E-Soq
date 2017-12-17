package SWProject.Model;

import javafx.scene.control.Alert;

import static SWProject.Control.SharedController.database;

public class Admin extends User {

  public void addBrand(Brand brand)
  {
    if(database.loadBrand(brand.getName())!=null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("Brand already Exists");
      alert.showAndWait();
    }
    else
    {
      if(database.getBrands().size()==0)
        brand.setBrandID(0);
      else
        brand.setBrandID(database.getBrands().get(database.getBrands().size()-1).brandID+1);
      database.addBrand(brand);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Message");
      alert.setHeaderText(null);
      alert.setContentText("Done!");
      alert.showAndWait();
    }
  }

  public void storeData() {
  }

  public void provideVoucherCard()
  {

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