package project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class SecondController implements Initializable {

    @FXML
    public ListView<String> DetailsListView;

    @FXML
    public Button AddSelectedSandwichB, RemoveSandwichB, ClearSandwichB, SaveB , GoToMain;

    @FXML
    public TextField SumTotal;

    //to go back
    public Stage stage;


    /**
     * Method to remove sandwich from order details stage.
     * Method also check for errors and shows alert window
     * for the errors to notify user for proper function of
     * program.
     */
    public void remove() {//index

        int selected = DetailsListView.getSelectionModel().getSelectedIndex();

        if(DetailsListView.getItems().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("Cannot remove, your order is empty!");
            alert.showAndWait();

        }

       else if (selected != DetailsListView.getItems().size() - 1) {//total price is not selected

            DetailsListView.getItems().remove(selected);
            Controller.order.remove(Controller.order.getOrderLine(selected));
            updateListView();
            calcTotalPrice();
        }

        else if (selected == DetailsListView.getItems().size() - 1) {//total price is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("You selected total price, please select an order line");
            alert.showAndWait();

        }
    }

    /**
     * Method to add selected sandwich to the order.
     * Method also check for errors and shows alert window
     * for the errors to notify user for proper function of
     * program.
     */
    public void add() {

        int selected = DetailsListView.getSelectionModel().getSelectedIndex();


        if(DetailsListView.getItems().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("Cannot add, your order is empty!");
            alert.showAndWait();

        }

        else if (selected != DetailsListView.getItems().size() - 1) {
            DetailsListView.getItems().addAll(DetailsListView.getSelectionModel().getSelectedItems());
            OrderLine duplicate = new OrderLine(Order.lineNumber, Controller.order.getOrderLine(selected).getSandwich());
            Controller.order.add(duplicate);
            updateListView();
            calcTotalPrice();
        }
        else if (selected == DetailsListView.getItems().size() - 1) {//total price is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("You selected total price, please select an order line");
            alert.showAndWait();

        }
    }


    /**
     * Method to clear the order.
     * Method also check for errors and shows alert window
     * for the errors to notify user for proper function of
     * program.
     */
    public void clear() {

        if(DetailsListView.getItems().size() <= 1){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("Cannot empty, there are no sandwiches!");
            alert.showAndWait();

        }
        else if(DetailsListView.getItems().size() != 1 ) {
            Controller.order.clear();
            DetailsListView.getItems().clear();
        }
    }

    /**
     * Method to save the order to a file.
     */
    public void save() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(targetFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (pw != null) {
            ObservableList<String> list = getDetailsList();

            for (String orderline : list) {
                pw.write(orderline);
                pw.write("\n");
            }
        }

        pw.close();
        Controller.order.reorder();
        updateListView();
        calcTotalPrice();
    }

    /**
     * Method to get this stage.
     *
     * @param stage
     */
    public void getStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Method to close this stage on back button click.
     */
    public void close() {
        Controller.order.reorder();
        this.stage.close();
    }

    /**
     * Called when view loads.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateListView();
        calcTotalPrice();
    }

    /**
     * Updates the DetailsListView with the order.
     */
    private void updateListView() {
        DetailsListView.setItems(getDetailsList());
    }

    /**
     * Converts the order into an observable list for the DetailsListView.
     * @return Observable List list
     */
    private ObservableList<String> getDetailsList() {
        ObservableList<String> list = FXCollections.observableArrayList();

        // loop through order line list
        for (int i = 0; i < Controller.order.size(); i++) {
            OrderLine temp = Controller.order.getOrderLine(i);
            String[] sandwichStr = temp.getSandwich().toString().split(",");
            String display = "Order #";

            // set up sandwich name
            display = display.concat(String.valueOf(temp.getLineNumber()));
            display = display.concat(": ");
            display = display.concat(sandwichStr[0]);
            display = display.concat(" sandwich\n");

            int j = 1;
            // loop through ingredients
            for (j = 1; j < sandwichStr.length - 1; j++) {
                display = display.concat("\t");
                display = display.concat(sandwichStr[j]);

                if (j != sandwichStr.length - 2) {
                    display = display.concat(",");
                }
                display = display.concat("\n");
            }

            // price of the sandwich
            display = display.concat("Price: ");
            display = display.concat(sandwichStr[j]);

            list.add(display);
        }

        return list;
    }

    private void calcTotalPrice() {
        DecimalFormat format = new DecimalFormat("0.00");

        double t = 0;//get total price
        for(int x = 0 ; x<Controller.order.size() ; x++){
            t = t + Controller.order.getOrderLine(x).getSandwich().price();
        }

        DetailsListView.getItems().addAll("Total: $" + format.format(t));


    }
}
