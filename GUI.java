/**
 * Created by Nuno on 07/05/2017.
 */

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class GUI extends Application{
    UMeR umer = new UMeR();
    private Stage window;
    private Scene menu, login_menu, signupClient_menu, signupDriver_menu, signupCompany_menu;
    private String current_user, current_class;

    private HBox company_hbox;


    public boolean addClient(String email, String name, String password, String address, LocalDate date){
        if (email != null && name != null && password != null && address != null && date != null &&
                !email.equals("") && !name.equals("") && !password.equals("") && !address.equals("")){
            Client client = new Client(email, name, password, address, date);
            return umer.registerUser(client, null);
        }
        else return false;
    }

    public boolean addDriver(String email, String name, String password, String address, LocalDate date, String company){
        if (email != null && name != null && password != null && address != null && date != null &&
                !email.equals("") && !name.equals("") && !password.equals("") && !address.equals("")){
            int timeComplience = ThreadLocalRandom.current().nextInt(1, 10);
            Driver driver = new Driver(email, name, password, address, date, timeComplience);
            return umer.registerUser(driver, company);
        }
        else return false;
    }

    public boolean addCompany(String name, String password){
        if (name != null && password != null && !name.equals("") && !password.equals(""))
            return umer.registerCompany(name, password);
        else return false;
    }

    public boolean loginCheck(String key, String password){
        User user = umer.allUsers().get(key);
        if (user != null && user.getPassword().equals(password)) {
            this.current_user = key;
            this.current_class = user.getClass().getSimpleName();
            return true;
        }
        else {
            Company company = umer.getCompanies().get(key);
            if (company != null && company.getPassword().equals(password)) {
                this.current_user = key;
                this.current_class = company.getClass().getSimpleName();
                return true;
            }
        }

        return false;
    }

    public HBox nameRegisterBox (){
        Label name_label = new Label("Nome\t");
        TextField name_text = new TextField();
        HBox name_hbox = new HBox(20);
        name_hbox.getChildren().addAll(name_label, name_text);
        return name_hbox;
    }

    public HBox emailRegisterBox(){
        Label email_label = new Label("Email\t");
        TextField email_text = new TextField();
        HBox email_hbox = new HBox(20);
        email_hbox.getChildren().addAll(email_label, email_text);
        return email_hbox;
    }

    public HBox addressRegisterBox(){
        Label address_label = new Label("Morada\t");
        TextField address_text = new TextField();
        HBox address_hbox = new HBox(20);
        address_hbox.getChildren().addAll(address_label, address_text);
        return address_hbox;
    }

    public HBox bDayRegisterBox(){
        Label bday_label = new Label("Data de nascimento");
        ObservableList<Integer> dayOptions = FXCollections.observableArrayList();
        for (int i=1; i<=31; i++) dayOptions.add(i);

        ObservableList<Integer> monthOptions = FXCollections.observableArrayList();
        for (int i=1; i<=12; i++) monthOptions.add(i);

        ObservableList<Integer> yearOptions = FXCollections.observableArrayList();
        for (int i=1999; i>=1900; i--) yearOptions.add(i);

        ComboBox day_box = new ComboBox(dayOptions);
        day_box.getSelectionModel().selectFirst();
        ComboBox month_box = new ComboBox(monthOptions);
        month_box.getSelectionModel().selectFirst();
        ComboBox year_box = new ComboBox(yearOptions);
        year_box.getSelectionModel().selectFirst();

        HBox bday_hbox = new HBox(10);
        bday_hbox.getChildren().addAll(day_box, month_box, year_box);
        return bday_hbox;
    }

    public HBox passwordRegisterBox(){
        Label password_label = new Label("Password\t");
        PasswordField passsword_text = new PasswordField();
        HBox password_hbox = new HBox(20);
        password_hbox.getChildren().addAll(password_label, passsword_text);
        return password_hbox;
    }

    public HBox companyRegisterBox(){
        Label company_label = new Label("Empresa");

        ObservableList<String> companiesList = FXCollections.observableArrayList();
        companiesList.add("--private driver--");
        for (Company c: umer.getCompanies().values())
            companiesList.add(c.getName());

        ComboBox companies_cbox = new ComboBox(companiesList);
        companies_cbox.getSelectionModel().selectFirst();

        HBox companies_box = new HBox(20);
        companies_box.getChildren().addAll(company_label, companies_cbox);
        return companies_box;
    }

    public Button doneRegisterButton(){
        Button signupDone_button = new Button("Registar");
        signupDone_button.setFont(Font.font(20));
        signupDone_button.setPrefSize(250, 50);
        return signupDone_button;
    }

    public Button backButton(){
        Button back_button = new Button("<- Back");
        back_button.setMaxWidth(100);
        back_button.setOnAction(e -> {
            window.setScene(menu);
        });
        return back_button;
    }

    public Label errorLabel(String text){
        Label error = new Label(text);
        error.setFont(Font.font(15));
        error.setTextFill(Color.RED);
        error.setWrapText(true);
        error.setTextAlignment(TextAlignment.JUSTIFY);
        return error;
    }

    public Label successLabel(String text){
        Label success = new Label(text);
        success.setFont(Font.font(15));
        success.setTextFill(Color.GREEN);
        success.setWrapText(true);
        success.setTextAlignment(TextAlignment.JUSTIFY);
        return success;
    }

    public void clientSignupMenu(){
        VBox signupClient_layout = new VBox(20);

        Label driverTitle_label = new Label("Registar Cliente");
        driverTitle_label.setFont(Font.font(30));
        HBox name_hbox = nameRegisterBox();
        HBox email_hbox = emailRegisterBox();
        HBox address_hbox = addressRegisterBox();
        Label bday_label = new Label("Data de Aniversário");
        HBox bday_hbox = bDayRegisterBox();
        HBox password_hbox = passwordRegisterBox();
        Label error = errorLabel("Erro: Email já existe ou dados inseridos incorretamente");
        Label success = successLabel("Cliente registado com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            //Get input
            String email    = ((TextField) email_hbox.getChildren().get(1)).getText();
            String name     = ((TextField) name_hbox.getChildren().get(1)).getText();
            String address  = ((TextField) address_hbox.getChildren().get(1)).getText();
            int    bdayD    = (int) ((ComboBox)bday_hbox.getChildren().get(0)).getValue();
            int    bdayM    = (int) ((ComboBox)bday_hbox.getChildren().get(1)).getValue();
            int    bdayY    = (int) ((ComboBox)bday_hbox.getChildren().get(2)).getValue();
            String password = ((TextField) password_hbox.getChildren().get(1)).getText();

            //Add
            LocalDate bday  = LocalDate.of(bdayY, bdayM, bdayD);

            signupClient_layout.getChildren().removeAll(error, success);

            boolean b  = addClient(email, name, password, address, bday);
            if (b == false) signupClient_layout.getChildren().add(error);
            else signupClient_layout.getChildren().add(success);
        });

        Button back_button = backButton();

        signupClient_layout.setPadding(new Insets(35, 50, 20, 50));
        signupClient_layout.getChildren().addAll(driverTitle_label, name_hbox, email_hbox, address_hbox, bday_label, bday_hbox, password_hbox, signupDone_button, back_button);

        signupClient_menu = new Scene(signupClient_layout);
    }

    public void driverSignupMenu(){
        VBox signupDriver_layout = new VBox(15);

        Label driverTitle_label = new Label("Registar Condutor");
        driverTitle_label.setFont(Font.font(30));
        HBox name_hbox = nameRegisterBox();
        HBox email_hbox = emailRegisterBox();
        HBox address_hbox = addressRegisterBox();
        Label bday_label = new Label("Data de Aniversário");
        HBox bday_hbox = bDayRegisterBox();
        HBox password_hbox = passwordRegisterBox();
        HBox company_hbox = companyRegisterBox();
        Label error = errorLabel("Erro: Email já existe ou dados inseridos incorretamente");
        Label success = successLabel("Condutor registado com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            //Get input
            String email      = ((TextField) email_hbox.getChildren().get(1)).getText();
            String name       = ((TextField) name_hbox.getChildren().get(1)).getText();
            String address    = ((TextField) address_hbox.getChildren().get(1)).getText();
            int    bdayD      = (int) ((ComboBox)bday_hbox.getChildren().get(0)).getValue();
            int    bdayM      = (int) ((ComboBox)bday_hbox.getChildren().get(1)).getValue();
            int    bdayY      = (int) ((ComboBox)bday_hbox.getChildren().get(2)).getValue();
            String password   = ((TextField) password_hbox.getChildren().get(1)).getText();
            String company    = (String) ((ComboBox) company_hbox.getChildren().get(1)).getValue();

            //Add
            LocalDate bday    = LocalDate.of(bdayY, bdayM, bdayD);

            signupDriver_layout.getChildren().removeAll(error, success);

            if (company.equals("--private driver--")) company = null;

            boolean b = addDriver(email, name, password, address, bday, company);
            if (b == false) signupDriver_layout.getChildren().add(error);
            else signupDriver_layout.getChildren().add(success);
        });

        Button back_button = backButton();

        signupDriver_layout.setPadding(new Insets(20, 50, 20, 50));
        signupDriver_layout.getChildren().addAll(driverTitle_label, name_hbox, email_hbox, address_hbox, bday_label, bday_hbox, company_hbox, password_hbox, signupDone_button, back_button);

        signupDriver_menu = new Scene(signupDriver_layout);
    }

    public void companySignup(){
        VBox signupCompany_layout = new VBox(20);

        Label companyTitle_label = new Label("Registar Empresa");
        companyTitle_label.setFont(Font.font(30));
        HBox name_hbox = nameRegisterBox();
        HBox password_hbox = passwordRegisterBox();
        Label error = errorLabel("Erro: Email já existe ou dados inseridos incorretamente");
        Label success = successLabel("Empresa registada com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            String name      = ((TextField) name_hbox.getChildren().get(1)).getText();
            String password  = ((TextField) password_hbox.getChildren().get(1)).getText();

            signupCompany_layout.getChildren().removeAll(error, success);

            boolean b = addCompany(name, password);
            if (b == false) signupCompany_layout.getChildren().add(error);
            else signupCompany_layout.getChildren().add(success);
        });

        Button back_button = backButton();

        signupCompany_layout.setPadding(new Insets(35, 50, 50, 50));
        signupCompany_layout.getChildren().addAll(companyTitle_label, name_hbox, password_hbox, signupDone_button, back_button);

        signupCompany_menu = new Scene(signupCompany_layout);
    }

    public void loginMenu(){
        VBox login_layout = new VBox(20);

        Label loginTitle_label = new Label("Login");
        loginTitle_label.setFont(Font.font(30));
        HBox email_hbox = emailRegisterBox();
        HBox password_hbox = passwordRegisterBox();
        Label error = errorLabel("Erro: Utilizador inexistente ou dados inseridos incorretamente.");

        Button login_button = new Button("Entrar \uD83D\uDEAA ");
        login_button.setFont(Font.font(20));
        login_button.setOnAction(e -> {
            String name     = ((TextField) email_hbox.getChildren().get(1)).getText();
            String password = ((TextField) password_hbox.getChildren().get(1)).getText();

            login_layout.getChildren().remove(error);

            boolean b = loginCheck(name, password);
            if (b == false) login_layout.getChildren().add(error);

            else {
                System.out.println(true);
            }
        });

        Button back_button = backButton();

        login_layout.setPadding(new Insets(35, 50, 50, 50));
        login_layout.getChildren().addAll(loginTitle_label, email_hbox, password_hbox, login_button, back_button);

        login_menu = new Scene(login_layout);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        this.window = primaryStage;
        this.window.setWidth(350);
        this.window.setHeight(570);
        this.window.setTitle("UMeR");

        //Logo
        ImageView logo = new ImageView("images/umer_logo_small.png");
        logo.setPreserveRatio(true);
        logo.setFitWidth(250);
        logo.setSmooth(true);
        logo.setCache(true);


        //Login button
        Button login_button = new Button("LogIn");
        login_button.setFont(Font.font(15));
        login_button.setPrefSize(160, 40);
        login_button.setOnAction(e -> {
                    window.setScene(login_menu);
                });

        //Signup Client button
        Button signupClient_button = new Button("Registar Cliente");
        signupClient_button.setFont(Font.font(15));
        signupClient_button.setPrefSize(160, 40);
        signupClient_button.setOnAction(e -> {
                    window.setScene(signupClient_menu);
                });

        //Signup Driver button
        Button signupDriver_button = new Button("Registar Condutor");
        signupDriver_button.setFont(Font.font(15));
        signupDriver_button.setPrefSize(160, 40);
        signupDriver_button.setOnAction(e -> {
            window.setScene(signupDriver_menu);
        });

        Button signupCompany_button = new Button("Registar Empresa");
        signupCompany_button.setFont(Font.font(15));
        signupCompany_button.setPrefSize(160, 40);
        signupCompany_button.setOnAction(e -> {
            window.setScene(signupCompany_menu);
        });

        //Quit button
        Button quit_button = new Button("Quit");
        quit_button.setFont(Font.font(15));
        quit_button.setPrefSize(160, 40);
        quit_button.setOnAction(e -> {
            Platform.exit();
        });

        //Main menu Layout
        VBox menu_buttons_layout = new VBox(20);
        menu_buttons_layout.setPadding(new Insets(30, 50, 20, 45));
        menu_buttons_layout.getChildren().addAll(login_button, signupClient_button, signupDriver_button, signupCompany_button, quit_button);

        VBox menu_layout = new VBox(20);
        menu_layout.getChildren().addAll(logo, menu_buttons_layout);
        menu_layout.setPadding(new Insets(50, 20, 0, 50));

        //Main Menu
        menu = new Scene(menu_layout);

        loginMenu();
        clientSignupMenu();
        driverSignupMenu();
        companySignup();

        //Window
        this.window.setScene(menu);
        this.window.setResizable(false);
        this.window.show();
    }

    public void Main(String[] args){
        umer = new UMeR();
        launch(args);
    }
}
