package SWProject.Control;

import SWProject.Model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NStoreController extends SharedController implements Initializable
{

    public Store store;

    public NStoreController(Store store)
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
    private Button homePage;

    @FXML
    private Button addToCart;

    @FXML
    private TextField productID;

    @FXML
    private TextField quantity;

    @FXML
    private Button checkOut;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        displayProducts();

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

        homePage.setOnAction(e->{
            viewController.getNormalUserView((Stage)stage.getScene().getWindow());
        });

        checkOut.setOnAction(e->{
            viewController.getCartView((Stage)stage.getScene().getWindow());
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
}
