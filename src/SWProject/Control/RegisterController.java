package SWProject.Control;

import SWProject.Model.Admin;
import SWProject.Model.NormalUser;
import SWProject.Model.ShopOwner;
import SWProject.Model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends SharedController implements Initializable
{

    @FXML
    private AnchorPane stage;

    @FXML
    private TextField email;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox<String> userType;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField userName;

    @FXML
    private TextField userID;

    @FXML
    private PasswordField password;

    @FXML
    private Button homePage;


    @FXML
    private Text error;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(sharedAdmin!=null)
            userType.setDisable(true);

        userType.setItems(FXCollections.observableArrayList("Normal User","Shop Owner"));
        gender.setItems(FXCollections.observableArrayList("Male","Female"));

        homePage.setOnAction(e->{
            if(userID.getText().isEmpty())
                userID.getStyleClass().add("error");
            else if(email.getText().isEmpty())
                email.getStyleClass().add("error");
            else if(age.getText().isEmpty())
                age.getStyleClass().add("error");
            else if(userName.getText().isEmpty())
                userName.getStyleClass().add("error");
            else if(password.getText().isEmpty())
                password.getStyleClass().add("error");
            else if(gender.getValue().isEmpty())
                gender.getStyleClass().add("error");
            else if(!userType.isDisabled()&&userType.getValue().isEmpty())
                userType.getStyleClass().add("error");
            else
            {
                User user=null;
                user=database.loadUser(userID.getText());
                if(user==null)
                {
                    if(!userType.isDisabled()&&userType.getValue().equals("Normal User"))
                        user=new NormalUser();
                    else if(!userType.isDisabled()&&userType.getValue().equals("Shop Owner"))
                        user=new ShopOwner();
                    else
                        user=new Admin();
                    user.userName=userName.getText();
                    user.userID=userID.getText();
                    user.password=password.getText();
                    user.age=Integer.valueOf(age.getText());
                    user.gender=gender.getValue();
                    user.email=email.getText();
                    database.addUser(user);
                    if(user instanceof ShopOwner)
                    {
                        sharedShopOwner =(ShopOwner)user;
                        viewController.getShopOwnerView((Stage)stage.getScene().getWindow());
                    }
                    else if(user instanceof NormalUser)
                    {
                        sharedNormalUser=(NormalUser)user;
                        viewController.getNormalUserView((Stage)stage.getScene().getWindow());
                    }
                    else if(user instanceof Admin)
                    {
                        viewController.getAdminView((Stage)stage.getScene().getWindow());
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("UserID Already Exists");
                    alert.showAndWait();
                    userID.clear();
                }
            }

        });

    }
}
