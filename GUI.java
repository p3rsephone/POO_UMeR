/**
 * Created by Nuno on 07/05/2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.time.LocalDate;

public class GUI extends Application{
    UMeR umer = new UMeR();
    private Stage window;
    private Scene menu, signupClient_menu, signupDriver_menu;
    private Button login_button, quit_button, signupClient_button, signupDriver_button;


    public boolean addClient(String email, String name, String password, String address, LocalDate date, Point2D.Double position){
        if (email != null && name != null && password != null && address != null && date != null && position != null){
            Client client = new Client(email, name, password, address, date, position);
            if (umer.registerUser(client, null) == true)
                return true;
            else return false;
        }
        else return false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        this.window = primaryStage;
        this.window.setTitle("UMeR");

        //Logo
        ImageView logo = new ImageView("images/logo.png");
        logo.setPreserveRatio(true);
		logo.setSmooth(true);
		logo.setFitWidth(300);

        //Login button
        login_button = new Button("Login");
        login_button.setMaxWidth(150);
        login_button.setOnAction(e -> {
                    System.out.println("Login pressed");
                });

        //Signup Client button
        signupClient_button = new Button("Registar Cliente");
        signupClient_button.setMaxWidth(150);
        signupClient_button.setOnAction(e -> {
                    window.setScene(signupClient_menu);
                });

        //Signup Driver button
        signupDriver_button = new Button("Registar Condutor");
        signupDriver_button.setMaxWidth(150);
        signupDriver_button.setOnAction(e -> {
            window.setScene(signupDriver_menu);
        });

        //Quit button
        quit_button = new Button("Quit");
        quit_button.setMaxWidth(150);
        quit_button.setOnAction(e -> {
            Platform.exit();
        });

        //Back button
        Button back_button = new Button("<- Back");
        back_button.setMaxWidth(100);
        back_button.setOnAction(e -> {
            window.setScene(menu);
        });


        //Signup Client Text
        Label clientTitle_label = new Label("Registar Cliente");
        clientTitle_label.setFont(Font.font(35));

        Label clientName_label = new Label("Nome\t");
        TextField clientName_text = new TextField();

        Label clientEmail_label = new Label("Email\t");
        TextField clientEmail_text = new TextField();

        Label clientAddress_label = new Label("Morada\t");
        TextField clientAddress_text = new TextField();

        Label clientPosition_label = new Label("Posição\t");
        TextField clientPositionX_text = new TextField();
        clientPositionX_text.setPromptText("X");
        clientPositionX_text.setPrefWidth(40);
        TextField clientPositionY_text = new TextField();
        clientPositionY_text.setPromptText("Y");
        clientPositionY_text.setPrefWidth(40);

        Label clientBday_label = new Label("BDay\t");

        TextField clientBdayD_text = new TextField();
        clientBdayD_text.setPromptText("dd");
        clientBdayD_text.setPrefWidth(30);
        TextField clientBdayM_text = new TextField();
        clientBdayM_text.setPromptText("mm");
        clientBdayM_text.setPrefWidth(30);
        TextField clientBdayY_text = new TextField();
        clientBdayY_text.setPromptText("yyyy");
        clientBdayY_text.setPrefWidth(48);

        Label clientPassword_label = new Label("Password\t");
        PasswordField clientPasssword_text = new PasswordField();

        Button signupClientDone_button = new Button("Registar");
        signupClientDone_button.setFont(Font.font(20));
        signupClientDone_button.setPrefSize(250, 50);

        signupClientDone_button.setOnAction(e -> {
            String clientEmail      = clientEmail_text.getText();
            String clientName       = clientName_text.getText();
            String clientAddress    = clientAddress_text.getText();
            double clientPositonX   = Double.parseDouble(clientPositionX_text.getText());
            double clientPositonY   = Double.parseDouble(clientPositionY_text.getText());
            int clientBdayD         = Integer.parseInt(clientBdayD_text.getText());
            int clientBdayM         = Integer.parseInt(clientBdayM_text.getText());
            int clientBdayY         = Integer.parseInt(clientBdayY_text.getText());
            String clientPassword   = clientPasssword_text.getText();

            LocalDate clientBday            = LocalDate.of(clientBdayY, clientBdayM, clientBdayD);
            Point2D.Double clientPosition   = new Point2D.Double(clientPositonX, clientPositonY);

            boolean sucess = addClient(clientEmail, clientName, clientPassword, clientAddress, clientBday, clientPosition);
            if (sucess == false){
                System.out.println("False");
            }
            else System.out.println("True");

        });

        //Signup client
        HBox clientName_hbox = new HBox(20);
        clientName_hbox.getChildren().addAll(clientName_label, clientName_text);

        HBox clientEmail_hbox = new HBox(20);
        clientEmail_hbox.getChildren().addAll(clientEmail_label, clientEmail_text);

        HBox clientAddress_hbox = new HBox(20);
        clientAddress_hbox.getChildren().addAll(clientAddress_label, clientAddress_text);

        HBox clientPosition_hbox = new HBox(20);
        clientPosition_hbox.getChildren().addAll(clientPosition_label, clientPositionX_text, clientPositionY_text);

        HBox clientBday_hbox = new HBox(20);
        clientBday_hbox.getChildren().addAll(clientBday_label, clientBdayD_text, clientBdayM_text, clientBdayY_text);

        HBox clientPassword_hbox = new HBox(20);
        clientPassword_hbox.getChildren().addAll(clientPassword_label, clientPasssword_text);

        VBox signupClient_layout = new VBox(20);
        signupClient_layout.setPadding(new Insets(50, 50, 50, 50));
        signupClient_layout.getChildren().addAll(clientTitle_label, clientName_hbox, clientEmail_hbox, clientAddress_hbox, clientBday_hbox, clientPosition_hbox, clientPassword_hbox, signupClientDone_button, back_button);
        signupClient_menu = new Scene(signupClient_layout, 350, 570);
		//signupClient_layout.setStyle("-fx-background-color: #FFFFFF;");


        //Menu
        VBox menu_layout = new VBox(30);
        menu_layout.setPadding(new Insets(20, 100, 20, 100));
        menu_layout.getChildren().addAll(logo, login_button, signupClient_button, signupDriver_button, quit_button);
        menu = new Scene(menu_layout, 350, 570);
		menu_layout.setStyle("-fx-background-color: #FFFFFF;");

        this.window.setScene(menu);
        this.window.show();
    }

    public void Main(String[] args){
        umer = new UMeR();
        launch(args);
    }
}
