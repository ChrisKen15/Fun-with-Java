package project4;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller class for the Order.fxml file.
 */
public class Controller implements Initializable {

    @FXML
    public ComboBox SandwichTypeCB;

    @FXML
    public ListView<String> ExtraIngredientLV, SelectedExtraIngredientLV;

    @FXML
    public ImageView SandwichImage;

    @FXML
    public Button AddB, DetailsB , AddIngredientsB, RemoveIngredientsB;

    @FXML
    public TextField PriceTF;

    public static Order order;

    private Sandwich sandwich;	// sandwich the order is making
    private DecimalFormat format = new DecimalFormat("0.00");	// price formatting

    /**
     * Determines if the ingredient has already been added to selected.
     * @param ingredient
     * @return true if the ingredient is already added, false otherwise
     */
    private boolean duplicate(String ingredient) {
        ObservableList<String> list = SelectedExtraIngredientLV.getItems();

        for (String s : list) {
            if (ingredient.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to control all ComboBox functions based on
     * the sandwich type selected.
     */
    public void ComboBoxActions() {
        FileInputStream input = null;

        //change image
        if(SandwichTypeCB.getValue().toString().matches("Chicken") ) {//change image
            sandwich = new Chicken();
            SelectedExtraIngredientLV.getItems().clear();

            try {
                input = new FileInputStream("PJ4_Chicken.png");

            } catch (FileNotFoundException t) {
                t.printStackTrace();
            }
        }
        else if(SandwichTypeCB.getValue().toString().matches("Beef")){//change image
            sandwich = new Beef();
            SelectedExtraIngredientLV.getItems().clear();

            try {
                input = new FileInputStream("PJ4_Beef.png");

            } catch (FileNotFoundException t) {
                t.printStackTrace();
            }
        }
        else if(SandwichTypeCB.getValue().toString().matches("Fish")) {//change image
            sandwich = new Fish();
            SelectedExtraIngredientLV.getItems().clear();

            try {
                input = new FileInputStream("PJ4_Fish.png");

            } catch (FileNotFoundException t) {
                t.printStackTrace();
            }
        }

        if (input != null) {
            Image image = new Image(input);
            SandwichImage.setImage(image);
        }

        PriceTF.setText("$" + format.format(sandwich.price()));
        ExtraIngredientLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        SelectedExtraIngredientLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ExtraIngredientLV.setItems(Extra.ingredientList());
    }

    /**
     * Mehtod to control all extra add Ingredient functions.
     * Method also check for errors and has alert window
     * for the errors to notify user for proper function of program.
     */
    public void SubmitExtra() {

        String ingredient = ExtraIngredientLV.getSelectionModel().getSelectedItem();

        if(ExtraIngredientLV.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("To add extra ingredient please select from top list");
            alert.showAndWait();
        }
        else if(SelectedExtraIngredientLV.getItems().size() < 6) {
            if (duplicate(ingredient) == false) {
                sandwich.add(new Extra(ingredient));
                SelectedExtraIngredientLV.getItems().add(ingredient);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Note");
                alert.setHeaderText("You can only add an ingredient once.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("You can only add upto 6 ingredients per sandwich.");
            alert.showAndWait();
        }
        PriceTF.setText("$" + format.format(sandwich.price()));
    }

    /**
     * Method to control all extra remove Ingredient functions.
     * Method also checks for errors and has a alert window
     * for different errors to notify user for proper function of program.
     */
    public void RemoveExtra() {

        if(SelectedExtraIngredientLV.getItems().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("There are no extra ingredients to remove");
            alert.showAndWait();
        }
        else if(SelectedExtraIngredientLV.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Note");
            alert.setHeaderText("To remove extra ingredient please select from the bottom list");
            alert.showAndWait();
        }

        else {
            String ingredient = SelectedExtraIngredientLV.getSelectionModel().getSelectedItem();

            sandwich.remove(new Extra(ingredient));
            SelectedExtraIngredientLV.getItems().remove(ingredient);
            PriceTF.setText("$" + format.format(sandwich.price()));
        }
    }

    /**
     * Method to add sandwich to the order and
     * Increases line counter by 1. Method also
     * gets the extra the Ingredient.
     */
    public void addSandwich() {
        OrderLine line = new OrderLine(Order.lineNumber, sandwich);
        order.add(line);

        if(SandwichTypeCB.getValue().toString().matches("Chicken") ) {
            sandwich = new Chicken();
        }
        else if(SandwichTypeCB.getValue().toString().matches("Beef") ) {
            sandwich = new Beef();
        }
        else if(SandwichTypeCB.getValue().toString().matches("Fish") ) {
            sandwich = new Fish();
        }

        PriceTF.setText("$" + format.format(sandwich.price()));
        SelectedExtraIngredientLV.getItems().clear();
    }

    /**
     * Method opens a new stage
     * for OrderDetails. Method adds
     * order to the listview in the new stage.
     */
    public void openDetails() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderDetails.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root,950,700);
            stage.setTitle("Order Details");
            stage.setScene(scene);
            stage.show();


            //Get controller of scene2
            SecondController controller2 = loader.getController();
            controller2.getStage(stage);

            /*
            for(int x  = 0 ; x < order.getArray().size() ; x++){
                    controller2.DetailsListView.getItems().addAll(order.getArray().get(x).getLineNumber() + " " + order.getArray().get(x).getSandwich());
            }
            */

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Called when view loads.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set image to chicken upon start
        SandwichTypeCB.getSelectionModel().selectFirst();
        try {
            FileInputStream input = new FileInputStream("PJ4_Chicken.png");
            Image image = new Image(input);
            SandwichImage.setImage(image);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        order = new Order();
        sandwich = new Chicken();
        PriceTF.setText("$" + format.format(sandwich.price()));

        ExtraIngredientLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        SelectedExtraIngredientLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ExtraIngredientLV.setItems(Extra.ingredientList());
        SelectedExtraIngredientLV.getItems().clear();
    }

}
