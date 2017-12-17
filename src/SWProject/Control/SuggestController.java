package SWProject.Control;

import SWProject.Model.Suggest;
import SWProject.Model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class SuggestController extends SharedController implements Initializable
{
    public User user;

    public SuggestController(User user)
    {
        this.user=user;
    }
    @FXML
    private AnchorPane stage;

    @FXML
    private ChoiceBox<String> suggestType;

    @FXML
    private TextArea suggestion;

    @FXML
    private Button send;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       suggestType.setItems(FXCollections.observableArrayList("Product","Brand"));

       send.setOnAction(e->{
           if(suggestType.getValue().isEmpty())
               suggestType.getStyleClass().add("error");
           else if(suggestion.getText().isEmpty())
               suggestion.getStyleClass().add("error");
           else
           {
               Suggest suggest=new Suggest();
               suggest.setUserID(user.userID);
               suggest.setType(suggestType.getValue());
               suggest.setSuggestion(suggestion.getText());
               database.addSuggest(suggest);
               stage.getScene().getWindow().hide();
           }
       });
    }
}
