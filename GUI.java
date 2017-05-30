/**
 * Created by Nuno on 07/05/2017.
 */

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class GUI extends Application{
    UMeR umer = new UMeR();
    private Stage window;
    private Scene menu, login_menu, signupClient_menu, signupDriver_menu, signupCompany_menu, user_menu, signupVehicle_menu;
    private String current_user, current_class;
    private boolean driverAvailiable = true, noAvailiableTaxis = true;
    private String currentTripDriver;
    private int currentTripId = 0;
    private String saveFile = "umerData";

    private HBox company_hbox;

    public boolean addClient(String email, String name, String password, String address, LocalDate date){
        if (email != null && name != null && password != null && address != null && date != null &&
                !email.equals("") && !name.equals("") && !password.equals("") && !address.equals("")
                && !email.equals("admin")){
            Client client = new Client(email, name, password, address, date);
            return umer.registerUser(client, null);
        }
        else return false;
    }

    public boolean addDriver(String email, String name, String password, String address, LocalDate date, String company){
        if (email != null && name != null && password != null && address != null && date != null &&
                !email.equals("") && !name.equals("") && !password.equals("") && !address.equals("")
                && !email.equals("admin")){
            int timeComplience = ThreadLocalRandom.current().nextInt(10, 100);
            Driver driver = new Driver(email, name, password, address, date, timeComplience, company);
            return umer.registerUser(driver, company);
        }
        else return false;
    }

    public boolean addCompany(String name, String password){
        if (name != null && password != null && !name.equals("") && !password.equals("") && !name.equals("admin"))
            return umer.registerCompany(name, password);
        else return false;
    }

    public boolean addVehicle(String licencePlate, String vehicleClass, String condition, Point2D.Double position){
        if (licencePlate != null && vehicleClass != null && condition != null && position != null &&
                !licencePlate.equals("")) {
            double reliable = -1;
            if (condition.equals("Muito Bom"))  reliable = ThreadLocalRandom.current().nextDouble(80, 100);
            if (condition.equals("Bom"))        reliable = ThreadLocalRandom.current().nextDouble(60, 80);
            if (condition.equals("Razoável"))   reliable = ThreadLocalRandom.current().nextDouble(40, 60);
            if (condition.equals("Mau"))        reliable = ThreadLocalRandom.current().nextDouble(20, 40);
            if (condition.equals("Muito Mau"))  reliable = ThreadLocalRandom.current().nextDouble(0, 20);

            Vehicle vehicle = null;
            if (vehicleClass.equals("Carro"))       vehicle = new Car(licencePlate, reliable, position, this.current_user);
            if (vehicleClass.equals("Mota"))        vehicle = new Bike(licencePlate, reliable, position, this.current_user);
            if (vehicleClass.equals("Carrinha"))    vehicle = new Van(licencePlate, reliable, position, this.current_user);
            if (vehicleClass.equals("Helicóptero")) vehicle = new Helicopter(licencePlate, reliable, position, this.current_user);

            if (this.current_class.equals("Driver")) {
                    if (umer.registerVehicleP(vehicle)) {
                    umer.changeDriverVehicle(current_user, licencePlate);
                    return true;
                }
                else return false;
            }
            else return umer.registerCompanyVehicle(this.current_user, vehicle);
        }
        else return false;
    }

    public Trip newTrip(Client client, String d, Point2D.Double start, Point2D.Double end){
        client.setPosition(start);
        if (d.equals("--condutor mais próximo--")){
            Trip t = umer.newTripClosest(client, end);
            if (t != null) {
                String vehicle = umer.closestAvailableTaxi(client);
                String driver = t.getDriver();
                this.currentTripDriver = driver;
                this.noAvailiableTaxis = false;
                return t;
            }
            else{
                this.noAvailiableTaxis = true;
                return null;
            }
        }
        else{
            Trip t = umer.newTripSpecific(client, d, end);
            if (t == null) this.driverAvailiable = false;
            else this.driverAvailiable = true;
            if (umer.getAllDrivers().get(d) == null) this.noAvailiableTaxis = true;
            else this.noAvailiableTaxis = false;
            return t;
        }
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
        if (key.equals("admin") && password.equals("12345")){
            this.current_user = "admin";
            this.current_class = "admin";
            return true;
        }
        return false;
    }

    public HBox textRegisterBox(String field){
        Label label = new Label(field + "\t");
        label.setFont(Font.font(15));
        TextField textField = new TextField();
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(label, textField);
        return hbox;
    }

    public HBox passwordRegisterBox(String field){
        Label label = new Label(field + "\t");
        label.setFont(Font.font(15));
        PasswordField textField = new PasswordField();
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(label, textField);
        return hbox;
    }

    public HBox datesBox(){
        LocalDate now = LocalDate.now();

        ObservableList<Integer> dayOptions = FXCollections.observableArrayList();
        for (int i=1; i<=31; i++) dayOptions.add(i);

        ObservableList<Integer> monthOptions = FXCollections.observableArrayList();
        for (int i=1; i<=12; i++) monthOptions.add(i);

        ObservableList<Integer> yearOptions = FXCollections.observableArrayList();
        for (int i= now.getYear(); i>=1900; i--) yearOptions.add(i);

        ComboBox day_box = new ComboBox(dayOptions);
        day_box.getSelectionModel().select(now.getDayOfMonth()-1);
        ComboBox month_box = new ComboBox(monthOptions);
        month_box.getSelectionModel().select(now.getMonthValue()-1);
        ComboBox year_box = new ComboBox(yearOptions);
        year_box.getSelectionModel().selectFirst();

        HBox bday_hbox = new HBox(10);
        bday_hbox.getChildren().addAll(day_box, month_box, year_box);
        return bday_hbox;
    }

    public HBox companyRegisterBox(){
        Label company_label = new Label("Empresa");
        company_label.setFont(Font.font(15));

        ObservableList<String> companiesList = FXCollections.observableArrayList();
        companiesList.add("--private driver--");
        for (Company c: umer.getCompanies().values())
            companiesList.add(c.getName());

        ComboBox companies_cbox = new ComboBox(companiesList);
        companies_cbox.getSelectionModel().selectFirst();

        HBox companies_hbox = new HBox(20);
        companies_hbox.getChildren().addAll(company_label, companies_cbox);
        return companies_hbox;
    }

    public HBox vehicleConditionBox(){
        Label condition_label = new Label("Estado");

        ObservableList<String> condition_list = FXCollections.observableArrayList();
        condition_list.addAll("Muito Bom", "Bom", "Razoável", "Mau", "Muito Mau");

        ComboBox condition_cbox = new ComboBox(condition_list);
        condition_cbox.getSelectionModel().select(2);

        HBox condition_hbox = new HBox(20);
        condition_hbox.getChildren().addAll(condition_label, condition_cbox);
        return condition_hbox;
    }

    public HBox vehicleClassBox(){
        Label condition_label = new Label("Estado");

        ObservableList<String> condition_list = FXCollections.observableArrayList();
        condition_list.addAll("Carro", "Mota", "Carrinha", "Helicóptero");

        ComboBox condition_cbox = new ComboBox(condition_list);
        condition_cbox.getSelectionModel().select(0);

        HBox condition_hbox = new HBox(20);
        condition_hbox.getChildren().addAll(condition_label, condition_cbox);
        return condition_hbox;
    }

    public HBox positionBox(String p){
        HBox positionHbox = new HBox(20);
        Label positionLabel = new Label(p +"\t");
        positionLabel.setFont(Font.font(15));
        TextField positionX = new TextField();
        positionX.setPromptText("X");
        positionX.setPrefSize(50, 30);
        TextField positionY = new TextField();
        positionY.setPromptText("Y");
        positionY.setPrefSize(50, 30);
        positionHbox.getChildren().addAll(positionLabel, positionX, positionY);
        return positionHbox;
    }

    public Tab infoTab(Object user){
        Tab infoTab = new Tab("Informação");
        VBox infoTabVBox = new VBox(10);
        infoTabVBox.setPadding(new Insets(20,20,20,20));
        Label info_title = new Label("Informação Pessoal");
        info_title.setFont(Font.font(30));
        info_title.setUnderline(true);
        Label info = new Label();
        if (user instanceof Client)
            info.setText(printClient((Client) user));
        else if (user instanceof Driver)
            info.setText(printDriver((Driver) user));
        else info.setText(printCompany((Company) user));
        info.setFont(Font.font(15));
        infoTabVBox.getChildren().addAll(info_title, info);
        infoTab.setContent(infoTabVBox);
        return infoTab;
    }

    public Tab tripsTab(Object user){
        boolean admin = false;
        if (user == null) admin = true;
        VBox tripsTabVBox = new VBox(10);
        tripsTabVBox.setPadding(new Insets(20,20,20,20));

        Tab tripsTab = new Tab("Viagens");

        Label trips_title = new Label("Viagens");
        trips_title.setFont(Font.font(30));
        trips_title.setUnderline(true);

        TreeItem<String> trips_root = new TreeItem<>("Viagens");
        trips_root.setExpanded(true);

        ArrayList<String> trips_info;
        ArrayList<String> trips_dates;
        if (admin){
            trips_info = printTrips(null);
            trips_dates = umer.getDates();
        }
        else if (user instanceof User) {
            trips_info = printTrips((User) user);
            trips_dates = ((User) user).getDates();
        }
        else{
            trips_info = printTrips((Company) user);
            trips_dates = ((Company) user).getDates();
        }

        int i = 0;
        for (String s : trips_dates){
            TreeItem<String> trips_items = new TreeItem<>(s);
            TreeItem<String> trips_i = new TreeItem<>(trips_info.get(i));
            trips_items.getChildren().add(trips_i);
            trips_root.getChildren().add(trips_items);
            i++;
        }

        TreeView<String> trips = new TreeView<>(trips_root);

        tripsTabVBox.getChildren().addAll(trips_title, trips);

        tripsTab.setContent(tripsTabVBox);
        return tripsTab;
    }

    public Tab usersTab(Company c, String user){
        int user_class;
        boolean admin = false;
        Tab users_tab = new Tab();
        if (user.equals("client")){
            users_tab.setText("Clientes");
            user_class = 1;
        }
        else {
            users_tab.setText("Condutores");
            user_class = 0;
        }

        if (c == null)
            admin = true;

        VBox usersTabVbox = new VBox(10);
        usersTabVbox.setPadding(new Insets(20,20,20,20));

        Label users_title;
        if (user_class == 1) users_title = new Label("Clientes");
        else users_title = new Label("Condutores");
        users_title.setFont(Font.font(30));
        users_title.setUnderline(true);

        TreeItem<String> users_root;
        if (user_class == 1) users_root = new TreeItem<>("Clientes");
        else users_root = new TreeItem<>("Condutores");
        users_root.setExpanded(true);

        TreeSet<String> users_emails = null;
        if (admin)
            if (user_class == 1)  users_emails = printIdentifier(umer.getClients());
            else users_emails = printIdentifier(umer.getAllDrivers());
        else users_emails = printIdentifier(c.getDrivers());

        for (String s : users_emails){
            TreeItem<String> trips_items = new TreeItem<>(s);
            TreeItem<String> users_i;
            if (admin)
                if (user_class == 1) users_i = new TreeItem<>(printClient(umer.getClients().get(s)));
                else users_i = new TreeItem<>(printDriver(umer.getAllDrivers().get(s)));
            else users_i = new TreeItem<>(printDriver(c.getDrivers().get(s)));
            trips_items.getChildren().add(users_i);
            users_root.getChildren().add(trips_items);
        }

        TreeView<String> users = new TreeView<>(users_root);

        usersTabVbox.getChildren().addAll(users_title, users);
        users_tab.setContent(usersTabVbox);
        return users_tab;
    }

    public Tab vehiclesTab(Company c){
        boolean admin = false;
        if (c == null) admin = true;
        Tab vehicles_tab = new Tab("Veículos");

        VBox vehiclesTabVbox = new VBox(10);
        vehiclesTabVbox.setPadding(new Insets(20,20,20,20));

        Label vehicles_title = new Label("Veículos");
        vehicles_title.setFont(Font.font(30));
        vehicles_title.setUnderline(true);

        Button registerVehicle_button = new Button("Registar veículo");
        registerVehicle_button.setFont(Font.font(15));
        registerVehicle_button.setOnAction(e -> {
            vehicleSignupMenu();
            window.setScene(signupVehicle_menu);
        });

        TreeItem<String> vehicles_root = new TreeItem<>("Veículos");
        vehicles_root.setExpanded(true);

        TreeSet<String> vehicles_licencePlate;
        if (admin) vehicles_licencePlate = new TreeSet<>(printIdentifier(umer.getAllVehicles()));
        else vehicles_licencePlate = new TreeSet<>(printIdentifier(c.getVehicles()));

        for (String s : vehicles_licencePlate){
            TreeItem<String> trips_items = new TreeItem<>(s);
            TreeItem<String> vehicles_i;
            if (admin) vehicles_i = new TreeItem<>(printVehicle(umer.getAllVehicles().get(s)));
            else vehicles_i = new TreeItem<>(printVehicle(c.getVehicles().get(s)));
            trips_items.getChildren().add(vehicles_i);
            vehicles_root.getChildren().add(trips_items);
        }

        TreeView<String> vehicles = new TreeView<>(vehicles_root);

        if (admin) vehiclesTabVbox.getChildren().addAll(vehicles_title, vehicles);
        else vehiclesTabVbox.getChildren().addAll(vehicles_title, registerVehicle_button, vehicles);
        vehicles_tab.setContent(vehiclesTabVbox);
        return vehicles_tab;
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
            loadMenu();
        });
        return back_button;
    }

    public Label errorLabel(String text){
        Label error = new Label("Erro: " + text);
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

    public VBox userHeaderBox(Object user){
        VBox header = new VBox(5);
        header.setPadding(new Insets(10, 10, 0, 10));

        HBox headerInfo = new HBox(5);
        HBox headerInfo2 = new HBox(5);

        ImageView thumbnail;
        if (user instanceof Client)
            thumbnail = new ImageView("images/client_small.png");
        else if (user instanceof Driver)
            thumbnail = new ImageView("images/driver_small.png");
        else thumbnail = new ImageView("images/company_small.png");

        Label name;
        if (user instanceof User)
            name = new Label(((User) user).getName() + "\n"+ user.getClass().getSimpleName() + " | " + LocalDate.now());
        else name = new Label(((Company) user).getName() +" \n"+ user.getClass().getSimpleName() + " | " + LocalDate.now());
        name.setFont(Font.font(20));

        Label points = new Label();
        if (user instanceof Client)
            points.setText((((Client) user).getPoints()) + " pontos");
        else if (user instanceof User)
            points.setText((((Driver) user).getExp()) + " exp");
        else points.setText(((Company) user).getPoints() + " pontos");

        Button logout = new Button("Logout ✖");
        logout.setOnAction(e->{
            loadMenu();
        });

        Button reload_button = new Button("⟳");

        reload_button.setOnAction(e ->{
            if (user instanceof Client) clientMenu();
            else if (user instanceof Driver) driverMenu();
            else companyMenu();

            window.setScene(user_menu);
        });

        headerInfo.getChildren().addAll(thumbnail, name);
        headerInfo2.getChildren().addAll(logout, reload_button, points);
        header.getChildren().addAll(headerInfo, headerInfo2);
        header.setStyle("-fx-background-color: #f8b702;");
        return header;
    }

    public int moneyGeneratedBetween(String s, LocalDate t1, LocalDate t2){
        if (umer.getAllVehicles().get(s) != null)
            return umer.getAllVehicles().get(s).moneyGeneratedBetween(t1, t2);
        else return umer.getCompanies().get(s).moneyGeneratedBetween(t1, t2);
    }

    public VBox moneyGeneratedBetweenBox(){
        VBox moneyGeneratedBetween_box = new VBox(5);
        moneyGeneratedBetween_box.setPadding(new Insets(20,20,20,20));

        Label title = new Label("Dinheiro gerado entre:");
        title.setUnderline(true);
        title.setFont(Font.font(30));

        Label date1_label = new Label("Data inicial");
        date1_label.setFont(Font.font(15));
        Label date2_label = new Label("Data final");
        date2_label.setFont(Font.font(15));

        HBox date1_box = datesBox();
        HBox date2_box = datesBox();

        ListView<String> options = new ListView<>();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Vehicle v: umer.getAllVehicles().values())
            list.add(v.getLicencePlate());
        for (Company c: umer.getCompanies().values())
            list.add(c.getName());

        options.setItems(list);
        options.getSelectionModel().selectFirst();

        HBox button_and_money = new HBox(10);
        Label moneyGenerated_label = new Label("");
        moneyGenerated_label.setFont(Font.font(20));
        Button done_buttom = new Button("Concluído");

        button_and_money.getChildren().addAll(done_buttom, moneyGenerated_label);

        done_buttom.setOnAction(e ->{
            button_and_money.getChildren().remove(moneyGenerated_label);

            int date1_day   = (int)     ((ComboBox) date1_box.getChildren().get(0)).getValue();
            int date1_month = (int)     ((ComboBox) date1_box.getChildren().get(1)).getValue();
            int date1_year  = (int)     ((ComboBox) date1_box.getChildren().get(2)).getValue();
            int date2_day   = (int)     ((ComboBox) date2_box.getChildren().get(0)).getValue();
            int date2_month = (int)     ((ComboBox) date2_box.getChildren().get(1)).getValue();
            int date2_year  = (int)     ((ComboBox) date2_box.getChildren().get(2)).getValue();
            String s        = options.getSelectionModel().getSelectedItem();

            LocalDate date1 = LocalDate.of(date1_year, date1_month, date1_day);
            LocalDate date2 = LocalDate.of(date2_year, date2_month, date2_day);

            int moneyGenerated = moneyGeneratedBetween(s, date1, date2);
            moneyGenerated_label.setText(printMoney(moneyGenerated));
            button_and_money.getChildren().add(moneyGenerated_label);
        });

        moneyGeneratedBetween_box.getChildren().addAll(title, date1_label, date1_box, date2_label, date2_box, options, button_and_money);
        return moneyGeneratedBetween_box;
    }

    public String printTime(double time){
        return (int) time + "h:" + Math.round(time * 60)%60 + "m:" + Math.round(time * 3600)%60 + "s";
    }

    public String printMoney(double money){
        return (int) money + "€";
    }

    public String printDistance(double distance){
        return (int) distance + "km";
    }

    public String printClient(Client c){
        return "Nome: " + c.getName() +
                "\nEmail: " + c.getEmail() +
                "\nMorada: " + c.getAddress() +
                "\nData de nascimento: " + c.getBirthday() +
                "\nViagens totais: " + c.getNumberOfTrips() +
                "\nDistância percorrida: " + printDistance(c.getTotalDistance()) +
                "\nDinheiro gasto: " + printMoney(c.getMoney());
    }

    public String printDriver(Driver d){
        return "Nome: " + d.getName() +
                "\nEmail: " + d.getEmail() +
                "\nMorada: " + d.getAddress() +
                "\nData de nascimento: " + d.getBirthday() +
                "\nViagens totais: " + d.getNumberOfTrips() +
                "\nDistância percorrida: " + printDistance(d.getTotalDistance()) +
                "\nDinheiro ganho: " + printMoney(d.getMoney()) +
                "\nNúmero de classificações: " + d.getNumberOfReviews() +
                "\nClassficação: " + d.getRating() +
                "\nDesvio total: " + d.getDeviation();
    }

    public String printCompany(Company c){
        return "Nome: " + c.getName() +
                "\nNúmero de condutores: " + c.getDrivers().size() +
                "\nNúmero de veículos: " + c.getVehicles().size() +
                "\nNúmero de viagens: " + c.getTotalTrips() +
                "\nDinheiro gerado: " + printMoney(c.getMoneyGenerated());
    }

    public String printVehicle(Vehicle v){
        return "Matrícula: " + v.getLicencePlate() +
                "\nClass: " + v.getClass().getSimpleName() +
                "\nVelocidade: " + v.getSpeed() + " km/s" +
                "\nPreço (por km): " + v.getPrice() +
                "\nLugares: " + v.getSeats() +
                "\nFator de confiança: " + (int) v.getReliable() +
                "\nPosição Atual: " + v.getPosition().getX() + " , " + v.getPosition().getY()  +
                "\nNúmero de viagens: " + v.getTrips().size();
    }

    public String printTripsByDate(String date, ArrayList<Trip> trips){
        String s = "";
        for (Trip t: trips)
            if (t.getDate().toString().equals(date)) {
                s += t.toString() + "\n---------------------------------------------\n";
            }
        return s;
    }

    public ArrayList<String> printTrips(Object user){
        ArrayList<String> trips = new ArrayList<>();
        ArrayList<String> dates;
        ArrayList<Trip> t;

        if (user == null){
            dates = umer.getDates();
            t = umer.getTrips();
        }
        else if (user instanceof User) {
            dates = ((User) user).getDates();
            t = ((User) user).getTrips();
        }
        else{
            dates = ((Company) user).getDates();
            t = ((Company) user).getTrips();
        }

        for (String d : dates) {
            trips.add(printTripsByDate(d, t));
        }
        return trips;
    }

    public TreeSet<String> printIdentifier(HashMap map){
        TreeSet<String> emailsOrd = new TreeSet<>(String::compareTo);

        for (Object o: map.values()) {
            if (o instanceof User) emailsOrd.add(((User) o).getEmail());
            else emailsOrd.add(((Vehicle) o).getLicencePlate());
        }

        return emailsOrd;
    }

    public String printAdminInfo(){
        return "Número de viagens : " + umer.getTrips().size() +
                "\nNúmero de condutores : " + umer.getAllDrivers().size() +
                "\nNúmero de veículos : " + umer.getAllVehicles().size() +
                "\nNúmero de empresas : " + umer.getCompanies().size() +
                "\nNúmero de clientes : " + umer.getClients().size() +
                "\nDinheiro gerado : " + printMoney(umer.getMoneyGenerated()) +
                "\nDistância total : " + printDistance(umer.getTotalDistance()) +
                "\nTempo total : " + printTime(umer.getTotalTime());
    }

    public String printTopMoney(TreeSet<? extends User> clients){
        String s = "";
        int i = 1;
        for (User u: clients) {
            if (i == 11) break;
            s += i + "\t " + u.getEmail() + " - " + printMoney(u.getMoney()) +"\n";
            i++;
        }
        return s;
    }
    
    public String printTD(TreeSet<Driver> drivers){
        String s = "";
        int i = 1;
        for (Driver d: drivers) {
            if (i == 6) break;
            s += i + "\t " + d.getEmail() + " - " + printMoney(d.getDeviation()) +"\n";
            i++;
        }
        return s;
    }

    public String printTopRating(TreeSet<Driver> drivers){
        String s = "";
        int i = 1;
        for (Driver d: drivers) {
            if (i == 11) break;
            s += i + "\t " + d.getEmail() + " - " + Math.round(d.getRating()) +"\n";
            i++;
        }
        return s;
    }
    
    public void clientSignupMenu(){
        VBox signupClient_layout = new VBox(15);
        signupClient_layout.setPadding(new Insets(35, 50, 20, 50));

        Label driverTitle_label = new Label("Registar Cliente");
        driverTitle_label.setFont(Font.font(30));
        HBox name_hbox = textRegisterBox("Nome");
        HBox email_hbox = textRegisterBox("Email");
        HBox address_hbox = textRegisterBox("Morada");
        Label bday_label = new Label("Data de Aniversário");
        bday_label.setFont(Font.font(15));
        HBox bday_hbox = datesBox();
        HBox password_hbox = passwordRegisterBox("Password");
        Label error = errorLabel("");
        Label success = successLabel("Cliente registado com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            String email     = ((TextField) email_hbox.getChildren().get(1)).getText();
            String name      = ((TextField) name_hbox.getChildren().get(1)).getText();
            String address   = ((TextField) address_hbox.getChildren().get(1)).getText();
            int     bdayD    = (int) ((ComboBox) bday_hbox.getChildren().get(0)).getValue();
            int     bdayM    = (int) ((ComboBox) bday_hbox.getChildren().get(1)).getValue();
            int     bdayY    = (int) ((ComboBox) bday_hbox.getChildren().get(2)).getValue();
            String  password = ((TextField) password_hbox.getChildren().get(1)).getText();

            LocalDate bday = LocalDate.of(bdayY, bdayM, bdayD);

            signupClient_layout.getChildren().removeAll(error, success);

            boolean b = addClient(email, name, password, address, bday);
            if (!b) {
                error.setText("Email já existe ou dados inseridos incorretamente");
                signupClient_layout.getChildren().add(error);
            }
            else signupClient_layout.getChildren().add(success);
        });

        Button back_button = backButton();

        signupClient_layout.getChildren().addAll(driverTitle_label, name_hbox, email_hbox, address_hbox, bday_label, bday_hbox, password_hbox, signupDone_button, back_button);

        signupClient_menu = new Scene(signupClient_layout);
    }

    public void driverSignupMenu(){
        VBox signupDriver_layout = new VBox(15);
        signupDriver_layout.setPadding(new Insets(20, 50, 20, 50));

        Label driverTitle_label = new Label("Registar Condutor");
        driverTitle_label.setFont(Font.font(30));
        HBox name_hbox = textRegisterBox("Nome");
        HBox email_hbox = textRegisterBox("Email");
        HBox address_hbox = textRegisterBox("Morada");
        Label bday_label = new Label("Data de Aniversário");
        bday_label.setFont(Font.font(15));
        HBox bday_hbox = datesBox();
        HBox password_hbox = passwordRegisterBox("Password");
        HBox company_hbox = companyRegisterBox();
        Label error = errorLabel("");
        Label success = successLabel("Condutor registado com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            String email    = ((TextField) email_hbox.getChildren().get(1)).getText();
            String name     = ((TextField) name_hbox.getChildren().get(1)).getText();
            String address  = ((TextField) address_hbox.getChildren().get(1)).getText();
            int     bdayD   = (int) ((ComboBox) bday_hbox.getChildren().get(0)).getValue();
            int     bdayM   = (int) ((ComboBox) bday_hbox.getChildren().get(1)).getValue();
            int     bdayY   = (int) ((ComboBox) bday_hbox.getChildren().get(2)).getValue();
            String password = ((TextField) password_hbox.getChildren().get(1)).getText();
            String company  = (String) ((ComboBox) company_hbox.getChildren().get(1)).getValue();

            LocalDate bday = LocalDate.of(bdayY, bdayM, bdayD);

            signupDriver_layout.getChildren().removeAll(error, success);

            if (company.equals("--private driver--")) company = null;

            boolean b = addDriver(email, name, password, address, bday, company);
            if (!b) {
                error.setText("Email já existe ou dados inseridos incorretamente");
                signupDriver_layout.getChildren().add(error);
            }
            else {
                this.current_class = "Driver";
                this.current_user = email;
                if (company == null) {
                    vehicleSignupMenu();
                    window.setScene(signupVehicle_menu);
                }
                signupDriver_layout.getChildren().add(success);
            }
        });

        Button back_button = backButton();

        signupDriver_layout.getChildren().addAll(driverTitle_label, name_hbox, email_hbox, address_hbox, bday_label, bday_hbox, company_hbox, password_hbox, signupDone_button, back_button);

        signupDriver_menu = new Scene(signupDriver_layout);
    }

    public void vehicleSignupMenu(){
        VBox signupVehicle_layout = new VBox(15);
        signupVehicle_layout.setPadding(new Insets(20,50,20,50));

        Label vehicleTitle_label = new Label("Registar Veículo");
        vehicleTitle_label.setFont(Font.font(30));

        HBox licencePlate_hbox = textRegisterBox("Matrícula");
        HBox class_hbox = vehicleClassBox();
        HBox position_hbox = positionBox("Posição atual");
        HBox condition_hbox = vehicleConditionBox();

        Label error = errorLabel("");
        Label success = successLabel("Utilizador e veículo registado com sucesso");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e ->{
            try{
                String licencePlate = ((TextField) licencePlate_hbox.getChildren().get(1)).getText();
                String vehicleClass = (String) ((ComboBox) class_hbox.getChildren().get(1)).getValue();
                String condition    = (String) ((ComboBox) condition_hbox.getChildren().get(1)).getValue();

                signupVehicle_layout.getChildren().removeAll(error, success);

                double posX = Double.parseDouble(((TextField) position_hbox.getChildren().get(1)).getText());
                double posY = Double.parseDouble(((TextField) position_hbox.getChildren().get(2)).getText());

                Point2D.Double pos = new Point2D.Double(posX, posY);

                boolean b = addVehicle(licencePlate, vehicleClass, condition, pos);

                if (!b) {
                    error.setText("Matrícula já existe ou vazia");
                    signupVehicle_layout.getChildren().add(error);
                }
                else if (current_class.equals("Driver")) {
                        window.setScene(signupDriver_menu);
                    }
                else window.setScene(user_menu);
            }
            catch (NumberFormatException exception){
                error.setText("Posição inserida incorretamente");
                signupVehicle_layout.getChildren().add(error);
            }
        });


        signupVehicle_layout.getChildren().addAll(vehicleTitle_label, licencePlate_hbox, class_hbox, position_hbox, condition_hbox, signupDone_button);
        signupVehicle_menu = new Scene(signupVehicle_layout);
    }

    public void companySignup(){
        VBox signupCompany_layout = new VBox(20);

        Label companyTitle_label = new Label("Registar Empresa");
        companyTitle_label.setFont(Font.font(30));
        HBox name_hbox = textRegisterBox("Nome");
        HBox password_hbox = passwordRegisterBox("Password");
        Label error = errorLabel("");
        Label success = successLabel("Empresa registada com sucesso!");

        Button signupDone_button = doneRegisterButton();
        signupDone_button.setOnAction(e -> {
            try {
                String name = ((TextField) name_hbox.getChildren().get(1)).getText();
                String password = ((TextField) password_hbox.getChildren().get(1)).getText();

                signupCompany_layout.getChildren().removeAll(error, success);

                boolean b = addCompany(name, password);
                if (b == false) {
                    error.setText("Empresa já existe");
                    signupCompany_layout.getChildren().add(error);
                } else signupCompany_layout.getChildren().add(success);
            }
            catch (NullPointerException excepetion){
                error.setText("Dados inseridos incorretamente");
                signupCompany_layout.getChildren().add(error);
            }
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
        HBox email_hbox = textRegisterBox("Email");
        HBox password_hbox = passwordRegisterBox("Password");
        Label error = errorLabel("Email e/ou Password incorreto(s)");

        Button login_button = new Button("Entrar \uD83D\uDEAA ");
        login_button.setFont(Font.font(20));
        login_button.setOnAction(e -> {
            String email = ((TextField) email_hbox.getChildren().get(1)).getText();
            String password = ((TextField) password_hbox.getChildren().get(1)).getText();

            login_layout.getChildren().remove(error);

            boolean b = loginCheck(email, password);
            if (b == false) login_layout.getChildren().add(error);

            else if (current_class.equals("Client")) {
                clientMenu();
                window.setScene(user_menu);
            }
            else if (current_class.equals("Driver")) {
                driverMenu();
                window.setScene(user_menu);
            }
            else if (current_class.equals("Company")){
                companyMenu();
                window.setScene(user_menu);
            }
            else if (current_user.equals("admin")) {
                adminMenu();
                window.setScene(user_menu);
            }
        });

        Button back_button = backButton();

        login_layout.setPadding(new Insets(35, 50, 50, 50));
        login_layout.getChildren().addAll(loginTitle_label, email_hbox, password_hbox, login_button, back_button);

        login_menu = new Scene(login_layout);
    }

    public void clientMenu(){
        saveData();

        Client client = umer.getClients().get(current_user);

        VBox menu_vbox = new VBox();

        TabPane menu_tab = new TabPane();
        menu_tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox header = userHeaderBox(client);

        Tab info_tab = infoTab(client);

        Tab trips_tab = tripsTab(client);

        Tab newTrip_tab = new Tab("Nova viagem");
        VBox newTrip_tabVBox = new VBox(10);
        newTrip_tabVBox.setPadding(new Insets(20,20,20,20));
        Label newTrip_title = new Label("Nova Viagem");
        newTrip_title.setFont(Font.font(30));
        newTrip_title.setUnderline(true);


        if (client.getQueue() != null){
            Label queue_label = new Label("Em fila de espera...");
            queue_label.setFont(Font.font(20));

            Button cancel_button = new Button("Cancelar");
            cancel_button.setFont(Font.font(15));
            cancel_button.setTextFill(Color.RED);
            cancel_button.setOnAction(e -> {
                umer.cancelTripQueue(client.getEmail());
                clientMenu();
                window.setScene(user_menu);
            });

            newTrip_tabVBox.getChildren().addAll(queue_label, cancel_button);
        }

        else {
            ObservableList<String> driversList = FXCollections.observableArrayList();
            driversList.add("--condutor mais próximo--");
            for (Driver d : umer.getDriversP().values())
                driversList.add(d.getEmail());
            for (Company c : umer.getCompanies().values())
                driversList.add(c.getName());
            ComboBox<String> drivers_box = new ComboBox<>(driversList);
            drivers_box.getSelectionModel().selectFirst();

            HBox positionStart_hbox = positionBox("Posição atual");
            HBox positionEnd_hbox = positionBox("Posição final");

            Label error = errorLabel("");
            Label success = successLabel("");
            ScrollPane tripInfo_pane = new ScrollPane();

            ObservableList<Integer> rateList = FXCollections.observableArrayList(5, 4, 3, 2, 1);
            ComboBox<Integer> rateDriver_cbox = new ComboBox<Integer>(rateList);
            rateDriver_cbox.getSelectionModel().selectFirst();
            Button rateDriver_button = new Button("Classificar");

            HBox rate_hbox = new HBox(10);

            Button newTrip_button = new Button("Realizar viagem");
            newTrip_button.setOnAction(e -> {
                newTrip_tabVBox.getChildren().removeAll(error, rate_hbox, tripInfo_pane, success);
                String driver = drivers_box.getValue();

                try {
                    double posStartX = Double.parseDouble(((TextField) positionStart_hbox.getChildren().get(1)).getText());
                    double posStartY = Double.parseDouble(((TextField) positionStart_hbox.getChildren().get(2)).getText());
                    double posEndX = Double.parseDouble(((TextField) positionEnd_hbox.getChildren().get(1)).getText());
                    double posEndY = Double.parseDouble(((TextField) positionEnd_hbox.getChildren().get(2)).getText());

                    Point2D.Double posStart = new Point2D.Double(posStartX, posStartY);
                    Point2D.Double posEnd = new Point2D.Double(posEndX, posEndY);

                    Trip trip = newTrip(client, driver, posStart, posEnd);
                    if (trip != null) {
                        this.currentTripDriver = trip.getDriver();
                        this.currentTripId = trip.getID();

                        rate_hbox.getChildren().removeAll(rateDriver_cbox, rateDriver_button);
                        success.setText(trip.toString());
                        rate_hbox.getChildren().addAll(rateDriver_cbox, rateDriver_button);
                        tripInfo_pane.setContent(null);
                        tripInfo_pane.setContent(success);
                        newTrip_tabVBox.getChildren().addAll(rate_hbox, tripInfo_pane);

                        umer.addTrip(client.getEmail(), trip.getDriver(), trip.getLicencePlate(), trip);
                    }
                    else{
                        if (!driverAvailiable && !noAvailiableTaxis) {
                            newTrip_tabVBox.getChildren().removeAll(success, drivers_box, positionStart_hbox, positionEnd_hbox, newTrip_button);
                            success.setText("Colocado na fila de espera de " + driver);
                            newTrip_tabVBox.getChildren().add(success);
                        }
                        else if (noAvailiableTaxis) {
                            error.setText("Não existem taxis disponíveis");
                            newTrip_tabVBox.getChildren().add(error);
                        }
                    }
                }
                catch (NumberFormatException exception) {
                    error.setText("Posição inserida incorretamente");
                    newTrip_tabVBox.getChildren().add(error);
                }
            });

            rateDriver_button.setOnAction(e -> {
                int rating = rateDriver_cbox.getValue();
                umer.addRating(currentTripDriver, rating, currentTripId);

                newTrip_tabVBox.getChildren().removeAll(error, rate_hbox, tripInfo_pane, success);
                success.setText("Classificação adicionada");
                newTrip_tabVBox.getChildren().add(success);

            });

            newTrip_tabVBox.getChildren().addAll(newTrip_title, drivers_box, positionStart_hbox, positionEnd_hbox, newTrip_button);
        }
        newTrip_tab.setContent(newTrip_tabVBox);

        //------------
        menu_tab.getTabs().addAll(info_tab, trips_tab, newTrip_tab);
        menu_vbox.getChildren().addAll(header, menu_tab);
        user_menu = new Scene(menu_vbox);
    }


    public void driverMenu(){
        saveData();

        Driver driver = umer.getAllDrivers().get(current_user);

        VBox menu_vbox = new VBox();
        TabPane menu_tab = new TabPane();
        menu_tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox header = userHeaderBox(driver);
        Tab info_tab = infoTab(driver);

        Tab trips_tab = tripsTab(driver);

        Tab work_tab = new Tab("Disponibilidade");
        VBox work_tabVbox = new VBox(10);
        work_tabVbox.setPadding(new Insets(20,20,20,20));
        Label status_title = new Label("Disponibilidade");
        status_title.setFont(Font.font(30));
        status_title.setUnderline(true);

        ToggleGroup status_group = new ToggleGroup();
        HBox status_box = new HBox(10);
        ImageView free_image = new ImageView("images/check-mark_small.png");
        free_image.setPreserveRatio(true);
        ImageView occupied_image = new ImageView("images/red-cross_small.png");
        RadioButton free_radioButton = new RadioButton();
        RadioButton occupied_radioButton = new RadioButton();
        free_radioButton.setToggleGroup(status_group);
        free_radioButton.setGraphic(free_image);
        free_radioButton.setUserData(true);
        free_radioButton.setSelected(true);

        occupied_radioButton.setGraphic(occupied_image);
        occupied_radioButton.setToggleGroup(status_group);
        occupied_radioButton.setUserData(false);

        status_box.getChildren().addAll(free_radioButton, occupied_radioButton);
        Label currentStatus = new Label("Disponibilidade atual: " + driver.isAvailable());
        currentStatus.setFont(Font.font(15));

        Label queueNumber;
        if (driver.getVehicle() != null)
           queueNumber = new Label("Número de clientes em fila de espera - " + umer.getAllVehicles().get(driver.getVehicle()).getQueue().size());

        else queueNumber = new Label("");

        Button changeStatusButton = new Button("Alterar disponibilidade");
        changeStatusButton.setFont(Font.font(15));
        changeStatusButton.setOnAction(e -> {
            if (umer.getDriversP().get(driver.getEmail()) != null) {
                queueNumber.setText("Número de clientes em fila de espera - 0");
                umer.doAllTripsQueue(driver.getEmail());
            }
            work_tabVbox.getChildren().removeAll(currentStatus, queueNumber);
            umer.changeDriverAvailability(driver.getEmail(), (boolean) status_group.getSelectedToggle().getUserData());
            driver.setAvailability((boolean) status_group.getSelectedToggle().getUserData());
            currentStatus.setText("Disponibilidade atual: " + driver.isAvailable());
            work_tabVbox.getChildren().addAll(currentStatus, queueNumber);
            driverAvailiable = true;
            noAvailiableTaxis = false;

        });

        work_tabVbox.getChildren().addAll(status_title, status_box, changeStatusButton, currentStatus, queueNumber);
        work_tab.setContent(work_tabVbox);


        menu_tab.getTabs().addAll(info_tab, trips_tab, work_tab);
        menu_vbox.getChildren().addAll(header, menu_tab);

        user_menu = new Scene(menu_vbox);
    }

    public void companyMenu(){
        saveData();

        Company company = umer.getCompanies().get(this.current_user);

        VBox menu_vbox = new VBox();
        TabPane menu_tab = new TabPane();
        menu_tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox header = userHeaderBox(company);
        Tab info_tab = infoTab(company);

        Tab trips_tab = tripsTab(company);

        Tab drivers_tab = usersTab(company, "driver");

        Tab vehicles_tab = vehiclesTab(company);

        menu_tab.getTabs().addAll(info_tab, trips_tab, drivers_tab, vehicles_tab);
        menu_vbox.getChildren().addAll(header, menu_tab);

        user_menu = new Scene(menu_vbox);
    }

    public void adminMenu(){
        saveData();

        VBox menu_vbox = new VBox();
        VBox header = new VBox();
        header.getChildren().addAll(new ImageView("images/admin_banner_small.png"));

        Button logout = new Button("Logout ✖");
        logout.setPrefWidth(350);
        logout.setOnAction(e->{
            loadMenu();
        });

        TabPane menu_tab = new TabPane();
        menu_tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab info_tab = new Tab("Informação");
        VBox info_tabVbox = new VBox(20);
        info_tabVbox.setPadding(new Insets(20,20,20,20));
        Label info_title = new Label("Informação");
        info_title.setFont(Font.font(30));
        info_title.setUnderline(true);
        Label info = new Label(printAdminInfo());
        info.setFont(Font.font(15));
        info_tabVbox.getChildren().addAll(info_title, info);
        info_tab.setContent(info_tabVbox);

        Tab drivers_tab = usersTab(null, "driver");

        Tab clients_tab = usersTab(null, "client");

        Tab vehicles_tab = vehiclesTab(null);

        Tab trips_tab = tripsTab(null);

        Tab top_tab = new Tab("Top");
        VBox top_tabVBox = new VBox(20);
        top_tabVBox.setPadding(new Insets(20,20,20,20));
        ScrollPane topClientesScroll = new ScrollPane();
        Label topMoney_title = new Label("Top 10 dinheiro");
        topMoney_title.setUnderline(true);
        topMoney_title.setFont(Font.font(30));
        Label topClients_title = new Label("Clientes");
        topClients_title.setFont(Font.font(25));
        Label topClients = new Label(printTopMoney(umer.ordClient(new MoneyComparatorC())));
        topClients.setFont(Font.font(15));
        Label topDeviations_title = new Label("Condutores");
        topDeviations_title.setFont(Font.font(25));
        Label topDeviations = new Label(printTopMoney(umer.ordDriver(new MoneyComparatorD())));
        topDeviations.setFont(Font.font(15));
        Label topRating_title = new Label("Top 10 dinheiro gerado");
        Label topdeviation_title = new Label("Top 5 Maiores desvios");
        topdeviation_title.setFont(Font.font(25));
        topdeviation_title.setUnderline(true);
        Label topdeviation = new Label(printTD(umer.ordDriver(new DeviationComparator())));
        topdeviation.setFont(Font.font(15));
        Label topBestRating_title = new Label("Top Rating");
        topBestRating_title.setUnderline(true);
        topBestRating_title.setFont(Font.font(25));
        Label topBestRating = new Label(printTopRating(umer.ordDriver(new RatingComparator())));
        topBestRating.setFont(Font.font(15));
        top_tabVBox.getChildren().addAll(topMoney_title, topClients_title, topClients, topDeviations_title, topDeviations, topdeviation_title, topdeviation, topBestRating_title, topBestRating);
        topClientesScroll.setContent(top_tabVBox);
        top_tab.setContent(topClientesScroll);

        Tab moneyGeneratedBetween_tab = new Tab("Dinheiro Gerado Entre");
        VBox moneyGeneratedBetween_tabVBox = moneyGeneratedBetweenBox();
        moneyGeneratedBetween_tab.setContent(moneyGeneratedBetween_tabVBox);

        menu_tab.getTabs().addAll(info_tab, drivers_tab, clients_tab, vehicles_tab, trips_tab, top_tab, moneyGeneratedBetween_tab);
        menu_vbox.getChildren().addAll(header, logout, menu_tab);

        user_menu = new Scene(menu_vbox);
    }

    public void loadMenu(){
        //saveData();

        //Logo
        ImageView logo = new ImageView("images/logo_taxi_small.png");
        logo.setPreserveRatio(true);
        logo.setFitWidth(250);
        logo.setSmooth(true);
        logo.setCache(true);
        logo.setTranslateX(-10);


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
        menu_buttons_layout.setPadding(new Insets(30, 50, 20, 50));
        menu_buttons_layout.getChildren().addAll(login_button, signupClient_button, signupDriver_button, signupCompany_button, quit_button);

        VBox menu_layout = new VBox(20);
        menu_layout.getChildren().addAll(logo, menu_buttons_layout);
        menu_layout.setPadding(new Insets(50, 20, 0, 45));

        //Main Menu
        menu = new Scene(menu_layout);

        loginMenu();
        clientSignupMenu();
        driverSignupMenu();
        companySignup();
        adminMenu();

        //Window
        this.window.setScene(menu);
        this.window.setResizable(false);
        this.window.show();
    }

    public void saveData(){
        try{
            umer.saveUMeR(saveFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        umer = new UMeR();
        try{
            umer = umer.loadUMeR(saveFile);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.setProperty("prism.lcdtext", "false");
        this.window = primaryStage;
        this.window.setWidth(350);
        this.window.setHeight(570);
        this.window.setTitle("UMeR");
        this.window.getIcons().add(new Image("images/umer_icon.png"));

        loadMenu();
    }

    public void Main(String[] args){
        launch(args);
    }
}