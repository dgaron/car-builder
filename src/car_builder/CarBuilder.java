package car_builder;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class CarBuilder extends Application {
    private Scene scene1,
        scene2,
        scene3,
        sceen4;
    private MyBuild car = new MyBuild();
    private Customer customer = new Customer();
    private final double SALES_TAX_RATE = 0.06;
    private final double FINANCE_RATE = 0.07;
    private final double CASH_DISCOUNT = 750;
    private final double DOC_FEES = 325;
    private double totalPrice;
    private double taxOwed;
    private boolean payingCash = true;

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        scene1 = getScene1(car, primaryStage);

        primaryStage.setTitle("Car Builder");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    private Scene getScene1(MyBuild car, Stage window) {
        //BorderPane top section
        Label sceneHeading = new Label("SELECT A MODEL");
        sceneHeading.setStyle("-fx-font-size: 36");
        HBox topBox = new HBox(sceneHeading);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.TOP_LEFT);

        //BorderPane center section
        Image s40 = new Image("file:S40.jpeg");
        Image s60 = new Image("file:S60.jpg");
        Image s70 = new Image("file:S70.jpg");
        Image s80 = new Image("file:S80.jpg");

        ImageView s40View = new ImageView(s40);
        ImageView s60View = new ImageView(s60);
        ImageView s70View = new ImageView(s70);
        ImageView s80View = new ImageView(s80);

        s40View.setFitHeight(200);
        s40View.setPreserveRatio(true);
        s60View.setFitHeight(200);
        s60View.setPreserveRatio(true);
        s70View.setFitHeight(200);
        s70View.setPreserveRatio(true);
        s80View.setFitHeight(200);
        s80View.setPreserveRatio(true);
        //Labels
        Label l1 = new Label("S40");
        Label l1b = new Label(String.format("Starting at $%,.2f", car.S40_BASE_PRICE));
        Label l2 = new Label("S60");
        Label l2b = new Label(String.format("Starting at $%,.2f", car.S60_BASE_PRICE));
        Label l3 = new Label("S70");
        Label l3b = new Label(String.format("Starting at $%,.2f", car.S70_BASE_PRICE));
        Label l4 = new Label("S80");
        Label l4b = new Label(String.format("Starting at $%,.2f", car.S80_BASE_PRICE));
        //RadioButtons
        RadioButton s40Button = new RadioButton("Build your S40");
        RadioButton s60Button = new RadioButton("Build your S60");
        RadioButton s70Button = new RadioButton("Build your S70");
        RadioButton s80Button = new RadioButton("Build your S80");
        //ToggleGroup
        ToggleGroup modelGroup = new ToggleGroup();
        s40Button.setToggleGroup(modelGroup);
        s60Button.setToggleGroup(modelGroup);
        s70Button.setToggleGroup(modelGroup);
        s80Button.setToggleGroup(modelGroup);
        //Populate selected model
        if (car.getModel().equals("Unknown") || car.getModel().equals("S40")) {
            s40Button.setSelected(true);
            car.setModel("S40");
        } else if (car.getModel().equals("S60")) {
            s60Button.setSelected(true);
        } else if (car.getModel().equals("S70")) {
            s70Button.setSelected(true);
        } else {
            s80Button.setSelected(true);
        }
        //Event Handler
        s40Button.setOnAction(event -> {
            car.setModel("S40");
        });
        s60Button.setOnAction(event -> {
            car.setModel("S60");
        });
        s70Button.setOnAction(event -> {
            car.setModel("S70");
        });
        s80Button.setOnAction(event -> {
            car.setModel("S80");
        });
        //Assemble GridPane
        GridPane modelGrid = new GridPane();
        modelGrid.setHgap(20);
        modelGrid.setVgap(10);
        modelGrid.add(l1,0,0);
        modelGrid.add(s40View,0,1);
        modelGrid.add(l1b,0,2);
        modelGrid.add(s40Button,0,3);
        modelGrid.add(l2,1,0);
        modelGrid.add(s60View,1,1);
        modelGrid.add(l2b,1,2);
        modelGrid.add(s60Button,1,3);
        modelGrid.add(l3,2,0);
        modelGrid.add(s70View,2,1);
        modelGrid.add(l3b,2,2);
        modelGrid.add(s70Button, 2,3);
        modelGrid.add(l4,3,0);
        modelGrid.add(s80View,3,1);
        modelGrid.add(l4b,3,2);
        modelGrid.add(s80Button,3,3);

        //BorderPane bottom section
        Button eButton = new Button("Exit");
        Button cButton = new Button("Continue");
        HBox bottomBox = new HBox(20, cButton, eButton);
        bottomBox.setPadding(new Insets(40,0,0,0));
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        //Event handlers
        eButton.setOnAction(event -> {
            window.close();
        });
        cButton.setOnAction(event -> {
            window.setScene(getScene2(car,window));
        });

        //Assemble BorderPane
        BorderPane bp1 = new BorderPane();
        bp1.setPadding(new Insets(20));
        bp1.setTop(topBox);
        bp1.setBottom(bottomBox);
        bp1.setCenter(modelGrid);

        return new Scene(bp1);
    }
    private Scene getScene2(MyBuild car, Stage window) {
        //BorderPane top section
        Label sceneHeading = new Label("CONFIGURATION");
        sceneHeading.setStyle("-fx-font-size: 36");
        HBox topBox = new HBox(sceneHeading);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.TOP_LEFT);

        //BorderPane center section
        Label packageA = new Label("Package A");
        Label packageB = new Label("Package B");
        Label aDesc = new Label("Includes:"+
                "\nLeather seats"+
                "\nMoonroof"+
                "\nNavigation system"+
                "\nPremium audio system");
        Label bDesc = new Label("Includes:"+
                "\nPrecollision braking"+
                "\nAdaptive cruise control"+
                "\nRear cross-traffic assist"+
                "\n\nAvailable on S70 and S80 only");
        Label metallicLabel = new Label("Metallic paint");
        //CheckBoxes
        CheckBox aCheck = new CheckBox("$2,200");
        CheckBox bCheck = new CheckBox("$3,250");
        CheckBox mCheck = new CheckBox("$650");
        //Disable package B for S40 & S60
        if (car.getModel().equals("S70") || car.getModel().equals("S80")) {
            bCheck.setDisable(false);
        } else {
            bCheck.setDisable(true);
            car.setPkgB(false);
        }
        //Populate currently selected options
        if (car.getPkgA()) {
            aCheck.setSelected(true);
        }
        if (car.getPkgB()) {
            bCheck.setSelected(true);
        }
        if (car.getMetallic()) {
            mCheck.setSelected(true);
        }
        //CheckBox event handlers
        aCheck.setOnAction(event -> {
            if (aCheck.isSelected()) {
                car.setPkgA(true);
            } else {
                car.setPkgA(false);
            }
        });
        bCheck.setOnAction(event -> {
            if (bCheck.isSelected()) {
                car.setPkgB(true);
            } else {
                car.setPkgB(false);
            }
        });
        mCheck.setOnAction(event -> {
            if (mCheck.isSelected()) {
                car.setMetallic(true);
            } else {
                car.setMetallic(false);
            }
        });

        //Assemble GridPane
        GridPane packages = new GridPane();
        packages.setHgap(20);
        packages.setVgap(20);
        packages.add(packageA, 0, 0);
        packages.add(packageB, 1, 0);
        packages.add(aDesc, 0,1);
        packages.add(bDesc, 1,1);
        packages.add(aCheck, 0,2);
        packages.add(bCheck, 1,2);
        packages.add(metallicLabel, 0,3);
        packages.add(mCheck, 0,4);

        //Color images
        Image black = new Image("file:Black.png");
        Image blue = new Image("file:Blue.png");
        Image grey = new Image("file:Grey.png");
        Image white = new Image("file:White.png");
        Image red = new Image("file:Red.png");

        ImageView blackView = new ImageView(black);
        ImageView blueView = new ImageView(blue);
        ImageView greyView = new ImageView(grey);
        ImageView whiteView = new ImageView(white);
        ImageView redView = new ImageView(red);

        whiteView.setFitWidth(50);
        whiteView.setPreserveRatio(true);
        blackView.setFitWidth(50);
        blackView.setPreserveRatio(true);
        greyView.setFitWidth(50);
        greyView.setPreserveRatio(true);
        blueView.setFitWidth(50);
        blueView.setPreserveRatio(true);
        redView.setFitWidth(50);
        redView.setPreserveRatio(true);

        ListView<ImageView> colorList = new ListView<>();
        colorList.setPrefSize(75,275);
        colorList.getItems().addAll(whiteView, blackView, greyView, blueView, redView);
        //Set a selected value
        colorList.getSelectionModel().selectIndices(0);
        car.setColor("White");
        //Populate currently selected color

        //REPLACE WITH SWITCH STATEMENT
        if (car.getColor().equals("White")) {
            colorList.getSelectionModel().selectIndices(0);
        }
        if (car.getColor().equals("Black")) {
            colorList.getSelectionModel().selectIndices(1);
        }
        if (car.getColor().equals("Grey")) {
            colorList.getSelectionModel().selectIndices(2);
        }
        if (car.getColor().equals("Blue")) {
            colorList.getSelectionModel().selectIndices(3);
        }
        if (car.getColor().equals("Red")) {
            colorList.getSelectionModel().selectIndices(4);
        }
        //Event handler
        colorList.getSelectionModel().selectedItemProperty().addListener(event -> {
            int i = colorList.getSelectionModel().getSelectedIndex();

            //REPLACE WITH SWITCH STATEMENT

            if (i==0) {
                car.setColor("White");
            } else if (i==1) {
                car.setColor("Black");
            } else if (i==2) {
                car.setColor("Grey");
            } else if (i==3) {
                car.setColor("Blue");
            } else {
                car.setColor("Red");
            }
        });

        HBox center = new HBox(20, colorList, packages);

        //BorderPane bottom section
        Button eButton = new Button("Exit");
        Button pButton = new Button("Previous");
        Button cButton = new Button("Continue");
        HBox bottomBox = new HBox(20, pButton, cButton, eButton);
        bottomBox.setPadding(new Insets(40,0,0,0));
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        //Event handlers
        pButton.setOnAction(event -> {
            window.setScene(getScene1(car,window));
        });
        cButton.setOnAction(event -> {
            window.setScene(getScene3(customer,window));
        });
        eButton.setOnAction(event -> {
            window.close();
        });

        //Assemble BorderPane
        BorderPane bp2 = new BorderPane();
        bp2.setPadding(new Insets(20));
        bp2.setTop(topBox);
        bp2.setBottom(bottomBox);
        bp2.setCenter(center);

        return new Scene(bp2);
    }
    private Scene getScene3(Customer customer, Stage window) {
        //Alerts
        Alert missingInfo = new Alert(Alert.AlertType.WARNING,
                "ERROR: Please fill in missing fields");

        //BorderPane top section
        Label sceneHeading = new Label("INFORMATION");
        sceneHeading.setStyle("-fx-font-size: 36");
        HBox topBox = new HBox(sceneHeading);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.TOP_LEFT);

        //Labels & TextFields
        Label fName = new Label("First Name");
        Label lName = new Label("Last Name");
        Label phone = new Label("Phone");
        Label address1 = new Label("Address 1");
        Label address2 = new Label("Address 2");
        Label city = new Label("City");
        Label state = new Label("State");
        Label zip = new Label("Zip");

        TextField fNameField = new TextField();
        fNameField.setMaxWidth(150);
        TextField lNameField = new TextField();
        lNameField.setMaxWidth(150);
        TextField phoneField = new TextField();
        phoneField.setMaxWidth(150);
        TextField address1Field = new TextField();
        address1Field.setMaxWidth(300);
        TextField address2Field = new TextField();
        address2Field.setMaxWidth(300);
        TextField cityField = new TextField();
        cityField.setMaxWidth(150);
        TextField zipField = new TextField();
        zipField.setMaxWidth(100);

        //Populate stored data
        if (!customer.getFirstName().equals("Unknown")) {
            fNameField.setText(customer.getFirstName());
        }
        if (!customer.getLastName().equals("Unknown")) {
            lNameField.setText(customer.getLastName());
        }
        if (!customer.getPhone().equals("Unknown")) {
            phoneField.setText(customer.getPhone());
        }
        if (!customer.getAddress1().equals("Unknown")) {
            address1Field.setText(customer.getAddress1());
        }
        if (!customer.getAddress2().equals("Unknown")) {
            address2Field.setText(customer.getAddress2());
        }
        if (!customer.getCity().equals("Unknown")) {
            cityField.setText(customer.getCity());
        }
        if (!customer.getZip().equals("Unknown")) {
            zipField.setText(customer.getZip());
        }

        //States ComboBox
        ComboBox<String> states = new ComboBox<>();
        states.getItems().addAll("Alabama","Alaska","Arizona","Arkansas",
                "California","Colorado","Connecticut","Delaware","Florida","Georgia",
                "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
                "Maine","Maryland","Massachusetts","Michigan","Mississippi","Missouri","Montana",
                "Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York",
                "North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania",
                "Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah",
                "Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming");
        states.setVisibleRowCount(10);
        states.setValue("Alabama");
        //Populate stored data
        if (!customer.getState().equals("Unknown")) {
            states.setValue(customer.getState());
        }

        //Assemble GridPane 1
        GridPane custFormT = new GridPane();
        custFormT.setHgap(20);
        custFormT.setVgap(10);
        //Add controls
        custFormT.add(fName, 0,0);
        custFormT.add(fNameField, 0,1);
        custFormT.add(lName, 1,0);
        custFormT.add(lNameField,1,1);
        custFormT.add(phone,0,2);
        custFormT.add(phoneField,0,3);
        //Assemble GridPane 2
        GridPane custFormB = new GridPane();
        custFormB.setHgap(20);
        custFormB.setVgap(10);
        //Add controls
        custFormB.add(city,0,0);
        custFormB.add(cityField,0,1);
        custFormB.add(state,1,0);
        custFormB.add(states,1,1);
        custFormB.add(zip,2,0);
        custFormB.add(zipField,2,1);
        //VBox for address
        VBox addressVBox = new VBox(10, address1, address1Field,
                address2, address2Field);
        //VBox to contain center section
        VBox formVBox = new VBox(10, custFormT, addressVBox, custFormB);

        //BorderPane bottom section
        Button eButton = new Button("Exit");
        Button pButton = new Button("Previous");
        Button cButton = new Button("Continue");
        HBox bottomBox = new HBox(20, pButton, cButton, eButton);
        bottomBox.setPadding(new Insets(40,0,0,0));
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        //Event handlers
        pButton.setOnAction(event -> {
            if (!(fNameField.getText().isEmpty())) {
                customer.setFirstName(fNameField.getText());
            }
            if (!(lNameField.getText().isEmpty())) {
                customer.setLastName(lNameField.getText());
            }
            if (!(phoneField.getText().isEmpty())) {
                customer.setPhone(phoneField.getText());
            }
            if (!(address1Field.getText().isEmpty())) {
                customer.setAddress1(address1Field.getText());
            }
            if (!(address2Field.getText().isEmpty())) {
                customer.setAddress2(address2Field.getText());
            }
            if (!(cityField.getText().isEmpty())) {
                customer.setCity(cityField.getText());
            }
            if (!(zipField.getText().isEmpty())) {
                customer.setZip(zipField.getText());
            }
            customer.setState(states.getValue());
            window.setScene(getScene2(car,window));
        });
        cButton.setOnAction(event -> {
            //Explain formFilled
            boolean formFilled = true;
            if (!(fNameField.getText().isEmpty())) {
                customer.setFirstName(fNameField.getText());
                fName.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                fName.setTextFill(Color.RED);
            }
            if (!(lNameField.getText().isEmpty())) {
                customer.setLastName(lNameField.getText());
                lName.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                lName.setTextFill(Color.RED);
            }
            if (!(phoneField.getText().isEmpty())) {
                customer.setPhone(phoneField.getText());
                phone.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                phone.setTextFill(Color.RED);
            }
            if (!(address1Field.getText().isEmpty())) {
                customer.setAddress1(address1Field.getText());
                address1.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                address1.setTextFill(Color.RED);
            }
            if (!(address2Field.getText().isEmpty())) {
                customer.setAddress2(address2Field.getText());
            }
            if (!(cityField.getText().isEmpty())) {
                customer.setCity(cityField.getText());
                city.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                city.setTextFill(Color.RED);
            }
            if (!(zipField.getText().isEmpty())) {
                customer.setZip(zipField.getText());
                zip.setTextFill(Color.BLACK);
            } else {
                formFilled = false;
                zip.setTextFill(Color.RED);
            }
            customer.setState(states.getValue());
            if (formFilled) {
                window.setScene(getScene4(car, customer, window));
            } else {
                missingInfo.show();
            }
        });
        eButton.setOnAction(event -> {
            window.close();
        });

        //Assemble BorderPane
        BorderPane bp3 = new BorderPane();
        bp3.setPadding(new Insets(20));
        bp3.setTop(topBox);
        bp3.setBottom(bottomBox);
        bp3.setCenter(formVBox);

        return new Scene(bp3);
    }
    private Scene getScene4(MyBuild car, Customer customer, Stage window) {
        //Alerts
        Alert tradeFieldAlert = new Alert(Alert.AlertType.WARNING);

        //BorderPane top section
        Label sceneHeading = new Label("SUMMARY");
        sceneHeading.setStyle("-fx-font-size: 36");
        HBox topBox = new HBox(sceneHeading);
        topBox.setPadding(new Insets(20));
        topBox.setAlignment(Pos.TOP_LEFT);

        //BorderPane center section
        //Labels
        Label model = new Label("Model: ");
        Label color = new Label("Color: ");
        Label metallicLabel = new Label("Metallic paint: ");
        Label pkgALabel = new Label("Package A: ");
        Label pkgBLabel = new Label("Package B: ");
        Label priceAsBuilt = new Label("Price as built: ");
        Label tradeInL = new Label("Trade-in value: ");
        Label discounts = new Label("Cash discount: ");
        Label tax = new Label(String.format("Sales tax (%d%%):",
                (int)(SALES_TAX_RATE*100)));
        Label docFee = new Label("Document fees: ");
        Label docFeeValue = new Label(String.format("$%,.2f",DOC_FEES));
        Label totalCost = new Label("Total: ");
        Label tradeInR = new Label("Trade-in value: ");
        Label financeRate = new Label(String.format("Interest rate:\t%d%% APR", (int)(FINANCE_RATE*100)));
        //Customer Labels
        Label aNumLabel = new Label("Account number: ");
        Label cNameLabel = new Label("Customer name: ");
        Label accountNumber = new Label(String.format("%d",
                customer.getAccountNumber()));
        Label customerName = new Label(String.format("%s %s",
                customer.getFirstName(), customer.getLastName()));
        //Financing Labels
        Label monthlyPayment = new Label("Monthly payment: ");
        Label numPayment = new Label("Number of payments: ");
        Label totalPayments = new Label("Total cost: ");
        Label monthlyPaymentResult = new Label();
        Label numPaymentResult = new Label();
        Label totalPaymentsResult = new Label();
        //Set financing Labels invisible
        numPaymentResult.setVisible(false);
        monthlyPaymentResult.setVisible(false);
        totalPayments.setVisible(false);
        numPayment.setVisible(false);
        monthlyPayment.setVisible(false);
        totalPaymentsResult.setVisible(false);
        //Populate stored data
        Label modelChosen = new Label(car.getModel());
        Label colorChosen = new Label(car.getColor());
        Label packageAChosen = new Label();
        Label packageBChosen = new Label();
        Label metallicChosen = new Label();
        if (car.getPkgA()) {
            packageAChosen.setText(String.format("$%,.2f",
                    car.getPkgAPrice()));
        } else {
            pkgALabel.setVisible(false);
        }
        if (car.getPkgB()) {
            packageBChosen.setText(String.format("$%,.2f",
                    car.getPkgBPrice()));
        } else {
            pkgBLabel.setVisible(false);
        }
        if (car.getMetallic()) {
            metallicChosen.setText(String.format("$%,.2f",
                    car.getMetallicPaintPrice()));
        } else {
            metallicLabel.setVisible(false);
        }
        Label priceAsBuiltResult = new Label(String.format("$%,.2f",car.getTotalPrice()));
        Label tradeInValue = new Label("($0.00)");
        Label discountsApplied = new Label(String.format("($%,.2f)",CASH_DISCOUNT));
        //Trade-in TextField
        TextField tradeField = new TextField();
        tradeField.setMaxWidth(150);
        tradeField.setText("0.00");
        //Calculate tax & price
        totalPrice = calculateTotalPrice(car, Double.parseDouble(tradeField.getText()));
        Label taxOwedLabel = new Label(String.format("$%,.2f",taxOwed));
        Label totalCostResult = new Label(String.format("$%,.2f", totalPrice));
        //Financing CheckBox & term ComboBox
        CheckBox financing = new CheckBox("Finance");
        //Term ComboBox
        ComboBox<String> term = new ComboBox<>();
        term.getItems().addAll("24 months","36 months", "48 months", "60 months");
        term.setValue("36 months");
        term.setDisable(true);
        //Event handlers
        term.setOnAction(event -> {
            int numPayments;
            if (term.getValue().equals("24 months")) {
                numPaymentResult.setText("24");
                numPayments = 24;
            } else if (term.getValue().equals("36 months")) {
                numPaymentResult.setText("36");
                numPayments = 36;
            } else if (term.getValue().equals("48 months")) {
                numPaymentResult.setText("48");
                numPayments = 48;
            } else {
                numPaymentResult.setText("60");
                numPayments = 60;
            }
            double payment = finance(numPayments);
            monthlyPaymentResult.setText(String.format("$%,.2f", payment));
            totalPaymentsResult.setText(String.format("$%,.2f", (payment * numPayments)));
        });
        financing.setOnAction(event -> {
            if (financing.isSelected()) {
                //Update financing boolean
                payingCash = false;
                //Enable term ComboBox
                term.setDisable(false);
                //Update total price
                if (!(tradeField.getText().isEmpty())) {
                    try {
                        //Update total price
                        totalPrice = calculateTotalPrice(car, Double.parseDouble(tradeField.getText()));
                        if (Double.parseDouble(tradeField.getText())<0) {
                            throw new NegativeTradeException();
                        }
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    } catch (NumberFormatException ex) {
                        //Reset trade-in Label and TextField to 0.0
                        tradeField.setText("0.00");
                        tradeInValue.setText("($0.00)");
                        tradeInR.setTextFill(Color.RED);
                        //Display alert
                        tradeFieldAlert.setContentText("Error: Please enter numerical values only for trade-in");
                        tradeFieldAlert.show();
                        //Update price for 0.0 trade-in value
                        totalPrice = calculateTotalPrice(car, 0.0);
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    } catch (NegativeTradeException ex) {
                        //Reset trade-in Label and TextField to 0.0
                        tradeField.setText("0.00");
                        tradeInValue.setText("($0.00)");
                        tradeInR.setTextFill(Color.RED);
                        //Display alert
                        tradeFieldAlert.setContentText(ex.getMessage());
                        tradeFieldAlert.show();
                        //Update price for 0.0 trade-in value
                        totalPrice = calculateTotalPrice(car, 0.0);
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    }
                }
                //Determine number of payments
                int numPayments;
                if (term.getValue().equals("24 months")) {
                    numPaymentResult.setText("24");
                    numPayments = 24;
                } else if (term.getValue().equals("36 months")) {
                    numPaymentResult.setText("36");
                    numPayments = 36;
                } else if (term.getValue().equals("48 months")) {
                    numPaymentResult.setText("48");
                    numPayments = 48;
                } else {
                    numPaymentResult.setText("60");
                    numPayments = 60;
                }
                //Update payments info
                double payment = finance(numPayments);
                monthlyPaymentResult.setText(String.format("$%,.2f", payment));
                totalPaymentsResult.setText(String.format("$%,.2f", (payment * numPayments)));
                //Set financing Labels visible
                numPayment.setVisible(true);
                numPaymentResult.setVisible(true);
                monthlyPayment.setVisible(true);
                monthlyPaymentResult.setVisible(true);
                totalPayments.setVisible(true);
                totalPaymentsResult.setVisible(true);
                //Set discounts Labels invisible
                discountsApplied.setVisible(false);
                discounts.setVisible(false);
            } else {
                //Update financing boolean
                payingCash = true;
                //Disable term ComboBox
                term.setDisable(true);
                //Update total price
                if (!(tradeField.getText().isEmpty())) {
                    try {
                        //Update total price
                        totalPrice = calculateTotalPrice(car, Double.parseDouble(tradeField.getText()));
                        if (Double.parseDouble(tradeField.getText())<0)
                        {
                            throw new NegativeTradeException();
                        }
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    } catch (NumberFormatException ex) {
                        //Reset trade-in Label and TextField to 0.0
                        tradeField.setText("0.00");
                        tradeInValue.setText("($0.00)");
                        tradeInR.setTextFill(Color.RED);
                        //Display alert
                        tradeFieldAlert.setContentText("Error: Please enter numerical values only for trade-in");
                        tradeFieldAlert.show();
                        //Update price for 0.0 trade-in value
                        totalPrice = calculateTotalPrice(car, 0.0);
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    } catch (NegativeTradeException ex) {
                        //Reset trade-in Label and TextField to 0.0
                        tradeField.setText("0.00");
                        tradeInValue.setText("($0.00)");
                        tradeInR.setTextFill(Color.RED);
                        //Display alert
                        tradeFieldAlert.setContentText(ex.getMessage());
                        tradeFieldAlert.show();
                        //Update price for 0.0 trade-in value
                        totalPrice = calculateTotalPrice(car, 0.0);
                        totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    }
                }
                //Set financing Labels invisible
                numPayment.setVisible(false);
                numPaymentResult.setVisible(false);
                monthlyPayment.setVisible(false);
                monthlyPaymentResult.setVisible(false);
                totalPayments.setVisible(false);
                totalPaymentsResult.setVisible(false);
                //Set discounts Labels visible
                discountsApplied.setVisible(true);
                discounts.setVisible(true);
            }
        });
        tradeField.setOnAction(event -> {
            if (!(tradeField.getText().isEmpty())) {
                try {
                    tradeInValue.setText(String.format("($%,.2f)",
                            Double.parseDouble(tradeField.getText())));
                    if (Double.parseDouble(tradeField.getText())<0) {
                        throw new NegativeTradeException();
                    }
                    //Update total price
                    totalPrice = calculateTotalPrice(car, Double.parseDouble(tradeField.getText()));
                    totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    taxOwedLabel.setText(String.format("$%,.2f",taxOwed));
                    //Update payments info
                    if (financing.isSelected()) {
                        //Determine number of payments
                        int numPayments;
                        if (term.getValue().equals("24 months")) {
                            numPayments = 24;
                        } else if (term.getValue().equals("36 months")) {
                            numPayments = 36;
                        } else if (term.getValue().equals("48 months")) {
                            numPayments = 48;
                        } else {
                            numPayments = 60;
                        }
                        //Update payments info
                        double payment = finance(numPayments);
                        monthlyPaymentResult.setText(String.format("$%,.2f", payment));
                        totalPaymentsResult.setText(String.format("$%,.2f", (payment * numPayments)));

                    }
                    tradeInR.setTextFill(Color.BLACK);
                } catch (NumberFormatException ex) {
                    //Reset trade-in Label and TextField to 0.0
                    tradeField.setText("0.00");
                    tradeInValue.setText("($0.00)");
                    tradeInR.setTextFill(Color.RED);
                    //Display alert
                    tradeFieldAlert.setContentText("Error: Please enter numerical values only for trade-in");
                    tradeFieldAlert.show();
                    //Update total price for 0.0 trade-in value
                    totalPrice = calculateTotalPrice(car, 0.0);
                    totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    taxOwedLabel.setText(String.format("$%,.2f",taxOwed));
                    //Update payments info based on new total if financing is selected
                    if (financing.isSelected()) {
                        //Determine number of payments
                        int numPayments;
                        if (term.getValue().equals("24 months")) {
                            numPayments = 24;
                        } else if (term.getValue().equals("36 months")) {
                            numPayments = 36;
                        } else if (term.getValue().equals("48 months")) {
                            numPayments = 48;
                        } else {
                            numPayments = 60;
                        }
                        //Update payments info
                        double payment = finance(numPayments);
                        monthlyPaymentResult.setText(String.format("$%,.2f", payment));
                        totalPaymentsResult.setText(String.format("$%,.2f", (payment * numPayments)));
                    }
                } catch (NegativeTradeException ex) {
                    //Reset trade-in Label and TextField to 0.0
                    tradeField.setText("0.00");
                    tradeInValue.setText("($0.00)");
                    tradeInR.setTextFill(Color.RED);
                    //Display alert
                    tradeFieldAlert.setContentText(ex.getMessage());
                    tradeFieldAlert.show();
                    //Update total price for 0.0 trade-in value
                    totalPrice = calculateTotalPrice(car, 0.0);
                    totalCostResult.setText(String.format("$%,.2f", totalPrice));
                    taxOwedLabel.setText(String.format("$%,.2f",taxOwed));
                    //Update payments info based on new total if financing is selected
                    if (financing.isSelected()) {
                        //Determine number of payments
                        int numPayments;
                        if (term.getValue().equals("24 months")) {
                            numPayments = 24;
                        } else if (term.getValue().equals("36 months")) {
                            numPayments = 36;
                        } else if (term.getValue().equals("48 months")) {
                            numPayments = 48;
                        } else {
                            numPayments = 60;
                        }
                        //Update payments info
                        double payment = finance(numPayments);
                        monthlyPaymentResult.setText(String.format("$%,.2f", payment));
                        totalPaymentsResult.setText(String.format("$%,.2f", (payment * numPayments)));
                    }
                }
            }
        });
        //GridPane for left
        GridPane carInfoPane = new GridPane();
        carInfoPane.setHgap(20);
        carInfoPane.setVgap(10);
        carInfoPane.add(model, 0,0);
        carInfoPane.add(modelChosen, 1,0);
        carInfoPane.add(color,0,1);
        carInfoPane.add(colorChosen, 1,1);
        carInfoPane.add(metallicLabel,0,2);
        carInfoPane.add(metallicChosen, 1,2);
        carInfoPane.add(pkgALabel,0,3);
        carInfoPane.add(pkgBLabel,0,4);
        carInfoPane.add(packageAChosen,1,3);
        carInfoPane.add(packageBChosen, 1,4);
        carInfoPane.add(priceAsBuilt,0,5);
        carInfoPane.add(priceAsBuiltResult,1,5);
        carInfoPane.add(tradeInL, 0,6);
        carInfoPane.add(tradeInValue, 1,6);
        carInfoPane.add(discounts,0,7);
        carInfoPane.add(discountsApplied,1,7);
        carInfoPane.add(tax,0,8);
        carInfoPane.add(taxOwedLabel,1,8);
        carInfoPane.add(docFee,0,9);
        carInfoPane.add(docFeeValue,1,9);
        carInfoPane.add(totalCost,0,10);
        carInfoPane.add(totalCostResult,1,10);
        //GridPane for right top
        GridPane tradeInPane = new GridPane();
        tradeInPane.setHgap(20);
        tradeInPane.setVgap(10);
        tradeInPane.add(aNumLabel,0,0);
        tradeInPane.add(cNameLabel,0,1);
        tradeInPane.add(accountNumber,1,0);
        tradeInPane.add(customerName,1,1);
        tradeInPane.add(tradeInR,0,3);
        tradeInPane.add(tradeField,1,3);
        tradeInPane.add(financing,0,4);
        tradeInPane.add(financeRate,1,4);
        //GridPane for right bottom
        GridPane paymentInfoPane = new GridPane();
        paymentInfoPane.setHgap(20);
        paymentInfoPane.setVgap(10);
        paymentInfoPane.add(monthlyPayment,0,0);
        paymentInfoPane.add(monthlyPaymentResult,1,0);
        paymentInfoPane.add(numPayment,0,1);
        paymentInfoPane.add(numPaymentResult,1,1);
        paymentInfoPane.add(totalPayments,0,2);
        paymentInfoPane.add(totalPaymentsResult,1,2);
        //VBox for right
        VBox rightVBox = new VBox(20,tradeInPane,term,paymentInfoPane);
        //HBox for center
        HBox mainBox = new HBox(20,carInfoPane,rightVBox);

        //BorderPane bottom section
        Button eButton = new Button("Exit");
        Button pButton = new Button("Previous");
        HBox bottomBox = new HBox(20, pButton, eButton);
        bottomBox.setPadding(new Insets(40,0,0,0));
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        //Event handlers
        pButton.setOnAction(event -> {
            window.setScene(getScene3(customer,window));
        });
        eButton.setOnAction(event -> {
            window.close();
        });
        //Assemble BorderPane
        BorderPane bp4 = new BorderPane();
        bp4.setPadding(new Insets(20));
        bp4.setTop(topBox);
        bp4.setBottom(bottomBox);
        bp4.setCenter(mainBox);

        return new Scene(bp4);
    }
    private double calculateTotalPrice(MyBuild car, double tradeValue) {
        if (payingCash) {
            taxOwed = (car.getTotalPrice() - tradeValue - CASH_DISCOUNT) * SALES_TAX_RATE;
            return car.getTotalPrice() - tradeValue - CASH_DISCOUNT + taxOwed + DOC_FEES;
        } else {
            taxOwed = (car.getTotalPrice() - tradeValue) * SALES_TAX_RATE;
            return car.getTotalPrice() - tradeValue + taxOwed + DOC_FEES;
        }
    }
    private double finance(int term) {
        double r = FINANCE_RATE / 12;	//Interest per period
        int n = term;
        return (r*totalPrice) / (1-Math.pow(1+r,-n));
    }
}
