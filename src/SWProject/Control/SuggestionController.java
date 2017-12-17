package SWProject.Control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SuggestionController extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;
    @FXML
    private TextArea suggestion;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        for(int i=0;i<database.getSuggests().size();i++)
        {
            suggestion.appendText("Sender: "+database.getSuggests().get(i).getUserID()+"\n");
            suggestion.appendText("Type: "+database.getSuggests().get(i).getType()+"\n");
            suggestion.appendText("Suggestion: "+database.getSuggests().get(i).getSuggestion()+"\n");
            suggestion.appendText("-----------------------------------------------------------\n");
        }
    }
}
