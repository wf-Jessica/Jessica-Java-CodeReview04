package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CodeReview");



        ObservableList<Product> items = FXCollections.observableArrayList(
                new Product("Pfeffer",
                            "1 Stück",
                            3.49,
                            2.79,
                            "Pfeffer.jpg",
                            "Schwarzer Pfeffer verleiht Ihren Speisen\n eine pikante Schärfe, besonders wenn er länger mitgekocht wird."),
                new Product("Schafmilchkäse",
                            "200 Gramm Packung",
                            2.59,
                            1.99,
                            "Schafskäse.jpg",
                            "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur\nbedingt damit aus, wie man eine Werbebeschreibung schreibt."),
                new Product("Vöslauer",
                            "1.5 Liter Flasche",
                            0.75,
                            0.49,
                            "Vöslauer.jpg",
                            "Spritziges Vöslauer Mineralwasser."),
                new Product("Gelierzucker",
                            "500 Gramm Packung",
                            1.39,
                            0.89,
                            "Gelierzucker.jpg",
                            "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen\nVerhältnis mit Zitronensäure und Kristallzucker abgemischt wurde.")
        );
        ListView<Product> list = new ListView<>();
        list.getItems().addAll(items);

        //Labels
        Label lblProductName= new Label("Product Name: ");
        Label lblQuantity= new Label("Menge: ");
        Label lbloldPrice= new Label("alter Preis: ");
        Label lblnewPrice= new Label("neuer Preis: ");
        Label lbldescription = new Label("Beschreibung: ");
        Label lbldesctext= new Label();

        //Textfields
        TextField txtProductName= new TextField("");
        TextField txtQuantity= new TextField("");
        TextField txtoldPrice= new TextField("");
        TextField txtnewPrice= new TextField("");

        GridPane gridPane = new GridPane();
        gridPane.add(lblProductName,0,0,2,1);
        gridPane.add(lblQuantity,0,1,2,1);
        gridPane.add(lbloldPrice,0,2,2,1);
        gridPane.add(lblnewPrice,0,3,2,1);
        gridPane.add(lbldescription,0,8,2,2);
        gridPane.add(lbldesctext,0,9,16,6);
        gridPane.setPadding(new Insets(35, 35, 35, 35));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        //gridPane.setGridLinesVisible(true);
        gridPane.add(txtProductName,3,0,2,1);
        gridPane.add(txtQuantity, 3,1,2,1);
        gridPane.add(txtoldPrice,3,2,2,1);
        gridPane.add(txtnewPrice,3,3,2,1);
        gridPane.add(list,7,0,8,8);


        HBox main= new HBox(gridPane);

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            txtProductName.setText(newValue.getProductName());
            txtQuantity.setText(newValue.getQuantity());
            txtoldPrice.setText(Double.toString(newValue.getOldPrice()));
            txtnewPrice.setText(Double.toString(newValue.getNewPrice()));
            javafx.scene.image.Image image = new Image(newValue.getImagePath());
            ImageView imageview = new ImageView(image);
            imageview.setFitHeight(250);
            imageview.setFitWidth(250);
            gridPane.add(imageview,1,5,3,3);
            lbldesctext.setText(newValue.getDiscription());


        });

        Scene scene = new Scene(main, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
