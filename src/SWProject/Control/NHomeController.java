package SWProject.Control;



import SWProject.Model.OnSiteStore;
import SWProject.Model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NHomeController extends SharedController implements Initializable
{

    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private Text userName;

    @FXML
    private Button logOut;

    @FXML
    private TextField keyword;

    @FXML
    private Button search;

    @FXML
    private Button suggest;

    @FXML
    private Button myPurchases;

    @FXML
    private TextField storeID;

    @FXML
    private Button selectStore;

    @FXML
    private Button checkOut;

    @FXML
    private Button update;

    @FXML
    private TextField productID;

    @FXML
    private Button addToCart;

    @FXML
    private TextField quantity;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        userName.setText(sharedNormalUser.userName);
        displayProducts();
        displayStores();

        addToCart.setOnAction(e->{


            if(productID.getText().isEmpty())
                productID.getStyleClass().add("error");
            else if(quantity.getText().isEmpty()||Integer.valueOf(quantity.getText())<=0)
                quantity.getStyleClass().add("error");
            else
            {
                sharedshoppingCart.addToCart(Integer.valueOf(productID.getText()),Integer.valueOf(quantity.getText()));
                database.loadProduct(Integer.valueOf(productID.getText())).quantity-=Integer.valueOf(quantity.getText());
                try {
                    database.write("Products");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        search.setOnAction(e->{
            show.clear();
            if(keyword.getText().isEmpty())
            {
                displayProducts();
                displayStores();
            }
            else
            {
                for(int i=0;i<database.getProducts().size();i++)
                {
                    if(database.getProducts().get(i).name.toLowerCase().contains(keyword.getText().toLowerCase()))
                    {
                        show.appendText("Product Name: "+database.getProducts().get(i).name+"\n");
                        show.appendText("Product Brand: "+database.getProducts().get(i).brand+"\n");
                        show.appendText("Product Price: "+database.getProducts().get(i).price+"\n");
                        for(int x=0;x<database.getStores().size();x++)
                        {
                            if(database.getStores().get(i).storeID ==database.getProducts().get(i).storeID)
                            {
                                show.appendText("Store Name: "+database.getStores().get(x).name+"\n");
                                break;
                            }
                        }
                        show.appendText("-----------------------------------------------------------------------------------------");
                    }
                }
            }
        });

        logOut.setOnAction(e->{
            sharedNormalUser=null;
            viewController.getLoginView((Stage)stage.getScene().getWindow());
        });

        update.setOnAction(e->{

            viewController.getInformationView((Stage)stage.getScene().getWindow(),sharedNormalUser);
        });

        selectStore.setOnAction(e->{

            if(storeID.getText().isEmpty())
                storeID.getStyleClass().add("error");
            else
            {
                Store store=null;
                store=database.loadStore(Integer.valueOf(storeID.getText()));//////////////////////////
                if(store!=null)
                {
                    store.numOfUsersExplored++;
                    database.updateStore(store);
                    viewController.getNStoreView((Stage)stage.getScene().getWindow(),store);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Store doesn't Exists");
                    alert.showAndWait();
                }
            }
        });

        checkOut.setOnAction(e->{
            viewController.getCartView((Stage)stage.getScene().getWindow());
        });

        suggest.setOnAction(e->{
            viewController.getSuggestView(new Stage(),sharedNormalUser);
        });

        myPurchases.setOnAction(e->{

            viewController.getTransacitionView(new Stage(),sharedNormalUser);
        });
    }

    public void displayProducts()
    {
        for(int i=0;i<database.getProducts().size();i++)
        {
            if(database.getProducts().get(i).storeID==-1){
            show.appendText("Product Name: "+database.getProducts().get(i).name+"\n");
            show.appendText("Product Brand: "+database.getProducts().get(i).brand+"\n");
            show.appendText("Product Price: "+database.getProducts().get(i).price+"\n");
            show.appendText("Product ID: "+database.getProducts().get(i).productID+"\n");
            show.appendText("Product Quantity: "+database.getProducts().get(i).quantity+"\n");
            show.appendText("-----------------------------------------------------------------------------------------\n");
        }
        }
    }
    public void displayStores()
    {
        for(int i=0;i<database.getStores().size();i++)
        {
            show.appendText("Store Name: "+database.getStores().get(i).name+"\n");
            if(database.getStores().get(i) instanceof OnSiteStore)
            {
                show.appendText("Store Type: Onsite\n");
                show.appendText("Store Address: "+((OnSiteStore) database.getStores().get(i)).getAddress()+"\n");
            }
            else
                show.appendText("Store Type: Online\n");
            show.appendText("Store ID: "+database.getStores().get(i).storeID +"\n");
            show.appendText("-----------------------------------------------------------------------------------------\n");
        }
    }
    public void displayPopUp(String str)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}

