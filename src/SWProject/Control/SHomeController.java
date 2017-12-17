package SWProject.Control;

import SWProject.Model.OnSiteStore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SHomeController extends SharedController implements Initializable
{


    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private Text userName;

    @FXML
    private Button manageStores;

    @FXML
    private Button viewStatistics;

    @FXML
    private Button logOut;

    @FXML
    private TextField keyword;

    @FXML
    private Button search;

    @FXML
    private Button suggest;

    @FXML
    private Button transactions;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayProducts();
        displayStores();

        userName.setText(sharedShopOwner.userName);

        manageStores.setOnAction(e->{
            viewController.getManageStoresView((Stage)stage.getScene().getWindow());
        });

        logOut.setOnAction(e->{
            sharedShopOwner=null;
            viewController.getLoginView((Stage)stage.getScene().getWindow());
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
                            if(database.getStores().get(x).storeID ==database.getProducts().get(i).storeID)
                            {
                                show.appendText("Store Name: "+database.getStores().get(x).name+"\n");
                                break;
                            }
                        }
                        show.appendText("-----------------------------------------------------------------------------------------\n");
                    }
                }
            }
        });

        viewStatistics.setOnAction(e->{
            viewController.getStatisticsView(new Stage());
        });

        transactions.setOnAction(e->{

            viewController.getTransacitionView(new Stage(),sharedShopOwner);
        });

        suggest.setOnAction(e->{

            viewController.getSuggestView(new Stage(),sharedShopOwner);
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
            if(database.getStores().get(i).ownerid.equals(sharedShopOwner.userID))
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
    }
}
