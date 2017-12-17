package SWProject.Model;


public interface IStore {

  public int getStoreID();

  public int getNumOfUsersExplored();

  public int getNumOfProducts();

  public Brand getMostOrderedBrand();

  public Product getMostOrderedProduct();

  public void Statistics();

}