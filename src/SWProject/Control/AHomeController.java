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

import java.net.URL;
import java.util.ResourceBundle;

public class AHomeController  extends SharedController implements Initializable
{

    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private Text userName;

    @FXML
    private Button addAdmin;

    @FXML
    private Button logOut;

    @FXML
    private TextField keyword;

    @FXML
    private Button search;

    @FXML
    private Button addProduct;

    @FXML
    private TextField storeID;

    @FXML
    private Button selectStore;

    @FXML
    private Button update;

    @FXML
    private Button transactions;

    @FXML
    private Button suggestion;


    @FXML
    private Button provideVoucherCard;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayProducts();
        displayStores();
        selectStore.setOnAction(e->{

            if(storeID.getText().isEmpty())
                storeID.getStyleClass().add("error");
            else
            {
                Store store=null;
                store=database.loadStore(Integer.valueOf(storeID.getText()));//////////////////////////
                if(store!=null)
                {
                    viewController.getAStoreView(new Stage(),store);
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
        addProduct.setOnAction(e->{
            viewController.getAdminProduct(new Stage());
            show.clear();
            displayProducts();
            displayStores();

        });

        addAdmin.setOnAction(e->{

            viewController.getRegisterView((Stage)stage.getScene().getWindow());
        });

        logOut.setOnAction(e->{
            sharedAdmin=null;
            viewController.getLoginView((Stage)stage.getScene().getWindow());
        });

        update.setOnAction(e->{
            viewController.getInformationView((Stage)stage.getScene().getWindow(),sharedAdmin);
        });

        transactions.setOnAction(e->{

            viewController.getTransacitionView(new Stage(),sharedAdmin);
        });

        suggestion.setOnAction(e->{

            viewController.getSuggestionView(new Stage());
        });

        provideVoucherCard.setOnAction(e->{

            viewController.getVoucherView(new Stage());
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

}
