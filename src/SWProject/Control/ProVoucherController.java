package SWProject.Control;

import SWProject.Model.NormalUser;
import SWProject.Model.VoucherCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProVoucherController extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private TextField userID;

    @FXML
    private TextField voucherValue;

    @FXML
    private Button addVoucherCard;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayUsers();
        addVoucherCard.setOnAction(e->{
            if(userID.getText().isEmpty())
                userID.getStyleClass().add("error");
            else if(voucherValue.getText().isEmpty())
                voucherValue.getStyleClass().add("error");
            else
            {
                if(database.loadUser(userID.getText())==null)
                    displayPopUp("User ID doesn't Exists!");
                else
                {
                    VoucherCard voucherCard=new VoucherCard();
                    voucherCard.setUserID(userID.getText());
                    voucherCard.setValue(Integer.valueOf(voucherValue.getText()));
                    database.addVoucherCard(voucherCard);
                    stage.getScene().getWindow().hide();
                }
            }
        });
    }

    public void displayPopUp(String str)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

    public void displayUsers()
    {
        for(int i=0;i<database.getUsers().size();i++)
        {
            if(database.getUsers().get(i) instanceof NormalUser)
            {
                show.appendText("UserID: "+database.getUsers().get(i).userID+"\n");
                show.appendText("User Name: "+database.getUsers().get(i).userName+"\n");
                show.appendText("User Email: "+database.getUsers().get(i).email+"\n");
                show.appendText("User Gender: "+database.getUsers().get(i).gender+"\n");
                show.appendText("User Age: "+database.getUsers().get(i).age+"\n");
                show.appendText("----------------------------------------------------------\n");
            }
        }
    }
}
