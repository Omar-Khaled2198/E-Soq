package SWProject.Control;

import SWProject.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField userID;

    @FXML
    private PasswordField password;

    @FXML
    private Text error;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        login.setOnAction(e->{
               User user=null;
               user=database.loadUser(userID.getText());
               if(user==null){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText(null);
                   alert.setContentText("UserID doesn't Exists");
                   alert.showAndWait();
               }
               else
               {
                   if(!user.password.equals(password.getText()))
                   {
                       Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Error");
                       alert.setHeaderText(null);
                       alert.setContentText("Password is Wrong!");
                       alert.showAndWait();
                   }
                   else if(user instanceof ShopOwner)
                   {
                       sharedShopOwner =(ShopOwner)user;
                       viewController.getShopOwnerView((Stage)stage.getScene().getWindow());
                   }
                   else if(user instanceof NormalUser)
                   {
                       sharedNormalUser=(NormalUser)user;
                       sharedshoppingCart=database.loadShoppingCart(sharedNormalUser.userID);
                       if(sharedshoppingCart==null)
                       {
                           sharedshoppingCart=new ShoppingCart();
                           sharedshoppingCart.userID=sharedNormalUser.userID;
                           try {
                               database.addShoppingCart(sharedshoppingCart);
                           } catch (IOException e1) {
                               e1.printStackTrace();
                           }
                       }
                       viewController.getNormalUserView((Stage)stage.getScene().getWindow());
                   }
                   else if(user instanceof Admin)
                   {
                       sharedAdmin=(Admin)user;
                       viewController.getAdminView((Stage)stage.getScene().getWindow());
                   }
               }

        });

        register.setOnAction(e->{

            viewController.getRegisterView((Stage)stage.getScene().getWindow());
        });
    }
}
