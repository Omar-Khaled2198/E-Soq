package SWProject.Control;

import SWProject.Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AProductConroller extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;

    @FXML
    private TextField productName;

    @FXML
    private TextField productBrand;

    @FXML
    private TextField productQuantity;

    @FXML
    private TextField productPrice;

    @FXML
    private Button addProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        addProduct.setOnAction(e->{

            if(productName.getText().isEmpty())
                productName.getStyleClass().add("error");
            else if(productBrand.getText().isEmpty())
                productBrand.getStyleClass().add("error");
            else if(productQuantity.getText().isEmpty())
                productQuantity.getStyleClass().add("error");
            else if(productPrice.getText().isEmpty())
                productPrice.getStyleClass().add("error");
            else
            {
                Product product=new Product();
                product.name=productName.getText();
                product.brand=productBrand.getText();
                product.price=Double.valueOf(productPrice.getText());
                product.quantity=Integer.valueOf(productQuantity.getText());
                sharedAdmin.addProduct(product);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Done");
                alert.showAndWait();

            }
            stage.getScene().getWindow().hide();
        });

    }
}
