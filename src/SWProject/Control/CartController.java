package SWProject.Control;

import SWProject.Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CartController extends SharedController implements Initializable
{
    @FXML
    private AnchorPane stage;

    @FXML
    private TextArea show;

    @FXML
    private TextArea voucherCardsShow;

    @FXML
    private TextField productID;

    @FXML
    private ChoiceBox<String> paymentType;

    @FXML
    private TextField address;

    @FXML
    private PasswordField visaCode;

    @FXML
    private TextField voucherCardID;

    @FXML
    private Button buy;

    @FXML
    private Button homePage;

    @FXML
    private TextArea info;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        displayInfo();
        displayCartItems();
        paymentType.setItems(FXCollections.observableArrayList("Visa","Delivery","Voucher Card"));
        address.setDisable(true);
        visaCode.setDisable(true);
        voucherCardID.setDisable(true);
        voucherCardsShow.setDisable(true);
        displayVoucherCards();

        paymentType.setOnAction(e->{

            if(paymentType.getValue().equals("Visa"))
            {
                visaCode.setDisable(false);
                address.setDisable(true);
                voucherCardID.setDisable(true);
                voucherCardsShow.setDisable(true);
            }
            else if(paymentType.getValue().equals("Delivery"))
            {
                address.setDisable(false);
                visaCode.setDisable(true);
                voucherCardID.setDisable(true);
                voucherCardsShow.setDisable(true);

            }
            else if(paymentType.getValue().equals("Voucher Card"))
            {
                address.setDisable(true);
                visaCode.setDisable(true);
                voucherCardID.setDisable(false);
                voucherCardsShow.setDisable(false);
            }

        });

        buy.setOnAction(e->{
            boolean check=false;
            if(productID.getText().isEmpty())
                productID.getStyleClass().add("error");
            else if(paymentType.getValue().isEmpty())
                paymentType.getStyleClass().add("error");
            else
            {
                if(database.loadProduct(Integer.valueOf(productID.getText()))==null||
                        sharedshoppingCart.getItem(Integer.valueOf(productID.getText()))==null)
                {
                    displayPopUp("Product doesn't Exists");
                }
                else
                {
                    Transaction transaction=new Transaction();
                    transaction.setUserID(sharedNormalUser.userID);
                    System.out.println(sharedshoppingCart.getItem(Integer.valueOf(productID.getText())).quantity);
                    transaction.setQuantity(sharedshoppingCart.getItem(Integer.valueOf(productID.getText())).quantity);
                    transaction.setProductID(Integer.valueOf(productID.getText()));
                    transaction.setStoreID(database.loadProduct(Integer.valueOf(productID.getText())).storeID);
                    transaction.setDate(Calendar.getInstance().getTime().toString());
                    if(paymentType.getValue().equals("Visa"))
                    {
                        if(visaCode.getText().isEmpty())
                            visaCode.getStyleClass().add("error");
                        else
                        {
                            Visa visa=new Visa();
                            visa.code=Integer.valueOf(visaCode.getText());
                            transaction.setPayment(visa);
                            try {
                                database.addTransactions(transaction);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            visaCode.clear();
                            displayPopUp("Done");
                            check=true;
                        }
                    }
                    else if(paymentType.getValue().equals("Delivery"))
                    {
                        if(address.getText().isEmpty())
                            address.getStyleClass().add("error");
                        else
                        {
                            Delivery delivery=new Delivery();
                            delivery.setAddress(visaCode.getText());
                            transaction.setPayment(delivery);
                            try {
                                database.addTransactions(transaction);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            address.clear();
                            displayPopUp("Done");
                            check=true;
                        }
                    }
                    else if(paymentType.getValue().equals("Voucher Card"))
                    {
                        if(voucherCardID.getText().isEmpty())
                            voucherCardID.getStyleClass().add("error");
                        else
                        {
                            VoucherCard voucherCard=database.loadVoucherCard(Integer.valueOf(voucherCardID.getText()));
                            if(voucherCard==null)
                            {
                                displayPopUp("Voucher ID doesn't Exists");
                            }
                            else
                            {
                                if(voucherCard.getValue()<database.loadProduct(Integer.valueOf(productID.getText())).price)
                                {
                                    displayPopUp("VoucherCard Value less than Product Price");
                                }
                                else
                                {
                                    transaction.setPayment(voucherCard);
                                    try {
                                        database.addTransactions(transaction);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    database.removeVoucherCard(Integer.valueOf(productID.getText()));
                                    voucherCardID.clear();
                                    displayPopUp("Done");
                                    voucherCardsShow.clear();
                                    displayVoucherCards();
                                    check=true;

                                }
                            }
                        }
                    }

                }
                if(check)
                {
                    show.clear();
                    sharedshoppingCart.removeItem(Integer.valueOf(productID.getText()));
                    productID.clear();
                    displayCartItems();
                    info.clear();
                    displayInfo();
                }
            }

        });

        homePage.setOnAction(e->{

            viewController.getNormalUserView((Stage)stage.getScene().getWindow());
        });

   }

   public void displayPopUp(String str)
   {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Message");
       alert.setHeaderText(null);
       alert.setContentText(str);
       alert.showAndWait();
   }
   public void displayCartItems()
   {
       for(int i=0;i<sharedshoppingCart.items.size();i++)
       {
           show.appendText("Product Name: "+sharedshoppingCart.items.get(i).name+"\n");
           show.appendText("Product Brand: "+sharedshoppingCart.items.get(i).brand+"\n");
           show.appendText("Product Price: "+sharedshoppingCart.items.get(i).price+"\n");
           show.appendText("Product Quantity: "+sharedshoppingCart.items.get(i).quantity+"\n");
           show.appendText("Product ID: "+sharedshoppingCart.items.get(i).productID+"\n");
           for(int x=0;x<database.getStores().size();x++)
           {
               if(database.getStores().get(x).storeID ==sharedshoppingCart.items.get(i).storeID)
               {
                   show.appendText("Store Name: "+database.getStores().get(x).name+"\n");
                   break;
               }
           }
           show.appendText("-----------------------------------------------------------------------------------------\n");
       }
   }
   public void displayVoucherCards()
   {
       for(int i=0;i<database.getVoucherCards().size();i++)
       {
           if(database.getVoucherCards().get(i).getUserID().equals(sharedNormalUser.userID))
           {
               voucherCardsShow.appendText("VoucherCard ID:"+database.getVoucherCards().get(i).getID()+"\n");
               voucherCardsShow.appendText("VoucherCard Value:"+database.getVoucherCards().get(i).getValue()+"\n");
               voucherCardsShow.appendText("-------------------------------------");
           }
       }
   }

   public void displayInfo()
   {
       info.appendText("Number of Products: "+sharedshoppingCart.items.size()+"\n");
       info.appendText("Total Cost: "+sharedshoppingCart.getTotalCost()+"\n");
   }
}
