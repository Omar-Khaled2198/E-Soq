package SWProject.Control;

import SWProject.Model.OnLineStore;
import SWProject.Model.Product;
import SWProject.Model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SStoreController extends SharedController implements Initializable
{

    Store store;
    public SStoreController(Store store)
    {
        this.store=store;
    }

    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private TextField keyword;

    @FXML
    private Button search;

    @FXML
    private TextField productName;

    @FXML
    private TextField productBrand;

    @FXML
    private TextField productQuantity;

    @FXML
    private TextField productPrice;

    @FXML
    private Button addProduct;

    @FXML
    private TextField productID;

    @FXML
    private Button removeProduct;

    @FXML
    private Button manageStores;

    @FXML
    private Button homePage;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(store instanceof OnLineStore)
            productQuantity.setDisable(true);
        displayProducts();

        addProduct.setOnAction(e->{
            if(productName.getText().isEmpty())
                productName.getStyleClass().add("error");
            else if(productBrand.getText().isEmpty())
                productBrand.getStyleClass().add("error");
            else if(!productQuantity.isDisabled()&&productQuantity.getText().isEmpty())
                productQuantity.getStyleClass().add("error");
            else if(productPrice.getText().isEmpty())
                productPrice.getStyleClass().add("error");
            else
            {
                Product product=new Product();
                product.name=productName.getText();
                product.brand=productBrand.getText();
                product.price=Double.valueOf(productPrice.getText());
                if(!productQuantity.isDisabled())product.quantity=Integer.valueOf(productQuantity.getText());
                else product.quantity=0;
                try {
                    sharedShopOwner.addProduct(product,store.storeID);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                show.clear();
                displayProducts();
            }

        });
        removeProduct.setOnAction(e->{
            if(productID.getText().isEmpty())
                productID.getStyleClass().add("error");
            else
            {
                if(database.loadProduct(Integer.valueOf(productID.getText()))==null)
                    displayPopUp("Product Doesn't Exists!");
                else
                {
                    try {
                        sharedShopOwner.removeProduct(Integer.valueOf(productID.getText()),store.storeID);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    show.clear();
                    displayProducts();
                }
            }

        });

        search.setOnAction(e->{
            show.clear();
            if(keyword.getText().isEmpty())
                displayProducts();
            else
            {
                for(int i=0;i<database.getProducts().size();i++)
                {
                    if(database.getProducts().get(i).storeID==store.storeID)
                    {
                        if(database.getProducts().get(i).name.toLowerCase().contains(keyword.getText().toLowerCase()))
                        {
                            show.appendText("Product Name: "+database.getProducts().get(i).name+"\n");
                            show.appendText("Product Brand: "+database.getProducts().get(i).brand+"\n");
                            show.appendText("Product Price: "+database.getProducts().get(i).price+"\n");
                            show.appendText("Product ID: "+database.getProducts().get(i).productID+"\n");
                            show.appendText("-----------------------------------------------------------------------------------------\n");
                        }
                    }
                }
            }
        });

        manageStores.setOnAction(e->{
            viewController.getManageStoresView((Stage)stage.getScene().getWindow());
        });

        homePage.setOnAction(e->{
            viewController.getShopOwnerView((Stage)stage.getScene().getWindow());
        });

    }

    public void displayProducts()
    {
        for(int i=0;i<database.getProducts().size();i++)
        {
            if(database.getProducts().get(i).storeID==store.storeID)
            {
                show.appendText("Product Name: "+database.getProducts().get(i).name+"\n");
                show.appendText("Product Brand: "+database.getProducts().get(i).brand+"\n");
                show.appendText("Product Price: "+database.getProducts().get(i).price+"\n");
                show.appendText("Product ID: "+database.getProducts().get(i).productID+"\n");
                show.appendText("Product Quantity: "+database.getProducts().get(i).quantity+"\n");
                show.appendText("-----------------------------------------------------------------------------------------\n");
            }
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
