package SWProject.Control;

import SWProject.Model.Admin;
import SWProject.Model.NormalUser;
import SWProject.Model.ShopOwner;
import SWProject.Model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InformationController extends SharedController implements Initializable {
    User user;

    public InformationController(User user) {
        this.user = user;
    }

    @FXML
    private AnchorPane stage;

    @FXML
    private TextField email;

    @FXML
    private TextField age;

    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private TextField userName;

    @FXML
    private Button save;

    @FXML
    private PasswordField password;

    @FXML
    private Text error;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.setItems(FXCollections.observableArrayList("Male", "Female"));
        userName.setText(user.userName);
        password.setText(user.password);
        age.setText(String.valueOf(user.age));
        email.setText(user.email);
        gender.setValue(user.gender);

        save.setOnAction(e -> {
            if (email.getText().isEmpty())
                email.getStyleClass().add("error");
            else if (age.getText().isEmpty())
                age.getStyleClass().add("error");
            else if (userName.getText().isEmpty())
                userName.getStyleClass().add("error");
            else if (password.getText().isEmpty())
                password.getStyleClass().add("error");
            else if (gender.getValue().isEmpty())
                gender.getStyleClass().add("error");
            else {
                user.userName = userName.getText();
                user.email = email.getText();
                user.gender = gender.getValue();
                user.age = Integer.valueOf(age.getText());
                try {
                    database.updateUser(user);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if(user instanceof NormalUser)viewController.getNormalUserView((Stage)stage.getScene().getWindow());
                else if(user instanceof ShopOwner)viewController.getShopOwnerView((Stage)stage.getScene().getWindow());
                else if(user instanceof Admin)viewController.getAdminView((Stage)stage.getScene().getWindow());
            }
        });

    }
}