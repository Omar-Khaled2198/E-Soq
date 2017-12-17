package SWProject.Control;


import SWProject.Model.Admin;
import SWProject.Model.NormalUser;
import SWProject.Model.ShopOwner;
import SWProject.Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsConroller extends SharedController implements Initializable
{
    User user;
    public TransactionsConroller(User user)
    {
        this.user=user;
    }

    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(user instanceof NormalUser)
        {
            for(int i=0;i<database.getTransactions().size();i++)
            {
                if(database.getTransactions().get(i).getUserID().equals(user.userID))
                {
                    show.appendText("UserID: "+database.getTransactions().get(i).getUserID()+"\n");
                    show.appendText("Product Name: "+database.loadProduct(database.getTransactions().get(i).getProductID()).name+"\n");
                    show.appendText("Product Quantity: "+database.getTransactions().get(i).getQuantity()+"\n");
                    if(database.getTransactions().get(i).getStoreID()!=-1)
                        show.appendText("Store Name: "+database.loadStore(database.getTransactions().get(i).getStoreID()).name+"\n");
                    show.appendText("Date: "+database.getTransactions().get(i).getDate()+"\n");
                    show.appendText("---------------------------------------------------------------\n");
                }
            }
        }
        else if(user instanceof ShopOwner)
        {
            for(int i=0;i<database.getTransactions().size();i++)
            {
                for(int x=0;x<database.getStores().size();x++)
                {
                    if(database.getTransactions().get(i).getStoreID()==database.getStores().get(x).storeID)
                    {
                       if(database.getStores().get(x).ownerid.equals(user.userID))
                       {
                           show.appendText("UserID: "+database.getTransactions().get(i).getUserID()+"\n");
                           show.appendText("Product Name: "+database.loadProduct(database.getTransactions().get(i).getProductID()).name+"\n");
                           show.appendText("Product Quantity: "+database.getTransactions().get(i).getQuantity()+"\n");
                           show.appendText("Store Name: "+database.loadStore(database.getTransactions().get(i).getStoreID()).name+"\n");
                           show.appendText("Date: "+database.getTransactions().get(i).getDate()+"\n");
                           show.appendText("---------------------------------------------------------------\n");
                       }
                    }
                }
            }
        }
        else if(user instanceof Admin)
        {
            for(int i=0;i<database.getTransactions().size();i++)
            {
                    show.appendText("UserID: "+database.getTransactions().get(i).getUserID()+"\n");
                    show.appendText("Product Name: "+database.loadProduct(database.getTransactions().get(i).getProductID()).name+"\n");
                    show.appendText("Product Quantity: "+database.getTransactions().get(i).getQuantity()+"\n");
                    if(database.getTransactions().get(i).getStoreID()!=-1)
                        show.appendText("Store Name: "+database.loadStore(database.getTransactions().get(i).getStoreID()).name+"\n");
                    show.appendText("Date: "+database.getTransactions().get(i).getDate()+"\n");
                    show.appendText("---------------------------------------------------------------\n");
            }
        }
    }
}
