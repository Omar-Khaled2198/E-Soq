package SWProject.Control;

import SWProject.Model.Store;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AStoreController extends SharedController implements Initializable
{
    public Store store;

    public AStoreController(Store store)
    {
        this.store=store;
    }
    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @Override
    public void initialize(URL location, ResourceBundle resources)
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
