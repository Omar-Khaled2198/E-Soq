package SWProject.Control;

import SWProject.Model.OnSiteStore;
import SWProject.Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsController  extends SharedController implements Initializable
{
    @FXML
    private TextArea show;

    @FXML
    private AnchorPane stage;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayStores();

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
                show.appendText("Number Of Products: "+database.getStores().get(i).numOfProducts +"\n");
                show.appendText("Number Of Users Explored: "+database.getStores().get(i).numOfUsersExplored +"\n");
                Product temp=database.getStores().get(i).getMostOrderedProduct();
                if(temp!=null)show.appendText("Most Ordered Product: "+temp.name+"\n");
                else show.appendText("Most Ordered Product: None\n");
                show.appendText("-----------------------------------------------------------------------------------------\n");
            }
        }
    }
}
