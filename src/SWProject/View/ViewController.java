package SWProject.View;

import SWProject.Control.*;
import SWProject.Model.Store;
import SWProject.Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewController
{

    public void getLoginView(Stage primaryStage)
    {
        LoginController loginController =new LoginController();
        Scene scene=setScene("LoginView.fxml",loginController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("E-Soq");
        primaryStage.centerOnScreen();
        primaryStage.show();


    }

    public void getShopOwnerView(Stage primaryStage)
    {
        SHomeController shopOwnerHome=new SHomeController();
        Scene scene=setScene("SHomeView.fxml",shopOwnerHome);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getNStoreView(Stage primaryStage,Store store)
    {
        NStoreController nStoreController=new NStoreController(store);
        Scene scene=setScene("NStoreView.fxml",nStoreController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle(store.name);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getManageStoresView(Stage primaryStage)
    {
        ManageStoreController manageStoreController=new ManageStoreController();
        Scene scene=setScene("ManageStoreView.fxml",manageStoreController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manage Stores");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getSStoreView(Stage primaryStage,Store store)
    {
        SStoreController sStoreController=new SStoreController(store);
        Scene scene=setScene("SStoreView.fxml",sStoreController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle(store.name);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getRegisterView(Stage primaryStage)
    {
        RegisterController registerController=new RegisterController();
        Scene scene=setScene("RegisterView.fxml",registerController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registeration");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getStatisticsView(Stage primaryStage)
    {
        StatisticsController statisticsController=new StatisticsController();
        Scene scene=setScene("StatisticsView.fxml",statisticsController);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Statistics");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getNormalUserView(Stage primaryStage)
    {
        NHomeController nHomeController=new NHomeController();
        Scene scene=setScene("NHomeView.fxml",nHomeController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getInformationView(Stage primaryStage,User user)
    {
        InformationController informationController=new InformationController(user);
        Scene scene=setScene("InformationView.fxml",informationController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Inforamtions");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getAdminView(Stage primaryStage)
    {
        AHomeController aHomeController=new AHomeController();
        Scene scene=setScene("AHomeView.fxml",aHomeController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getCartView(Stage primaryStage)
    {
        CartController cartController=new CartController();
        Scene scene=setScene("ShopingCartView.fxml",cartController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shopping Cart");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getTransacitionView(Stage primaryStage,User user)
    {
        TransactionsConroller transactionsConroller=new TransactionsConroller(user);
        Scene scene=setScene("TransactionsView.fxml",transactionsConroller);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Transactions");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    public void getAdminProduct(Stage primaryStage)
    {
        AProductConroller transactionsConroller=new AProductConroller();
        Scene scene=setScene("AaddProductView.fxml",transactionsConroller);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Product");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getSuggestView(Stage primaryStage,User user)
    {
        SuggestController suggestController=new SuggestController(user);
        Scene scene=setScene("SuggestView.fxml",suggestController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Suggestion");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getSuggestionView(Stage primaryStage)
    {
        SuggestionController suggestionController=new SuggestionController();
        Scene scene=setScene("SuggestionsView.fxml",suggestionController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Suggestion");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getVoucherView(Stage primaryStage)
    {
        ProVoucherController proVoucherController=new ProVoucherController();
        Scene scene=setScene("ProvideVoucherView.fxml",proVoucherController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Voucher Card");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void getAStoreView(Stage primaryStage,Store store)
    {
        AStoreController aStoreController=new AStoreController(store);
        Scene scene=setScene("AStoreView.fxml",aStoreController);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle(store.name);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public Scene setScene(String fxml, SharedController controller){

        FXMLLoader loader;
        Pane pane;
        Scene scene;
        try {
            loader=new FXMLLoader(getClass().getResource(fxml));
            loader.setController(controller);
            pane=loader.load();
        }
        catch (Exception e) {
            return null;
        }
        scene=new Scene(pane);
        return scene;
    }
}
