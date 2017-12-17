package SWProject.Control;

import SWProject.Model.OnLineStore;
import SWProject.Model.OnSiteStore;
import SWProject.Model.Store;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageStoreController extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;


    @FXML
    private Button addStores;

    @FXML
    private Button homePage;

    @FXML
    private TextField storeName;

    @FXML
    private TextField address;

    @FXML
    private ChoiceBox<String> storeType;

    @FXML
    private TextArea show;

    @FXML
    private Button removeStore;

    @FXML
    private TextField storeID;

    @FXML
    private Button selectStore;

    @FXML
    private TextField chosenStore;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayStores();
        storeType.setItems(FXCollections.observableArrayList("Online Store","Onsite Store"));
        storeType.setOnAction(e->{
            if(storeType.getValue().equals("Online Store"))
                address.setDisable(true);
            else
                address.setDisable(false);
        });
        addStores.setOnAction(e->{

            if(storeName.getText().isEmpty())
                storeName.getStyleClass().add("error");
            else if(storeType.getValue().isEmpty())
                storeType.getStyleClass().add("error");
            else if(address.getText().isEmpty()&&storeType.getValue().equals("Onsite Store"))
                address.getStyleClass().isEmpty();
            else
            {
                if(storeType.getValue().equals("Onsite Store"))
                {
                    OnSiteStore store=new OnSiteStore();
                    store.name=storeName.getText();
                    store.setAddress(address.getText());
                    System.out.println(store.storeID);
                    try {
                        sharedShopOwner.addStore(store);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else
                {
                    OnLineStore store=new OnLineStore();
                    store.name=storeName.getText();
                    try {
                        sharedShopOwner.addStore(store);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
            show.clear();
            displayStores();
        });

        removeStore.setOnAction(e->{
            if(storeID.getText().isEmpty())
                storeID.getStyleClass().add("error");
            else
            {
                if(database.loadStore(Integer.valueOf(storeID.getText()))==null)
                    displayPopUp("Store doesn't Exists!");
                else
                {
                    try {
                        sharedShopOwner.removeStore(Integer.valueOf(storeID.getText()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    show.clear();
                    displayStores();
                }

            }

        });

        homePage.setOnAction(e->{
           viewController.getShopOwnerView((Stage)stage.getScene().getWindow());
       });

        selectStore.setOnAction(e->{
            if(chosenStore.getText().isEmpty())
                chosenStore.getStyleClass().add("error");
            else
            {
                Store store=null;
                store=database.loadStore(Integer.valueOf(chosenStore.getText()));
                if(store!=null)
                   viewController.getSStoreView((Stage)stage.getScene().getWindow(),store);
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
    public void displayPopUp(String str)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
