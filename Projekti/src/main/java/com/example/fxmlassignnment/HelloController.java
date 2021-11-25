package com.example.fxmlassignnment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    TextField tfName;
    @FXML
    TextField tfLname;
    @FXML
    TextField tfAddress;
    @FXML
    ChoiceBox cbSpeed;
    @FXML
    ChoiceBox cbBandwidth;
    @FXML
    ChoiceBox cbDuration;
    @FXML
    Button btnSave;
    @FXML
    Button delBtn;
    @FXML
    Button btnClear;
    @FXML
    private TableView<Person> table;

    public HelloController(){

    }




    ObservableList<Person> persons;
    Person person;



    @FXML
    private void clearForm(){
        tfName.setText("");
        tfLname.setText("");
        tfAddress.setText("");

    }
    @FXML
    private void closePlatform(){
        Platform.exit();
    }


    @FXML
    private void delete(){
        persons = table.getItems();
        int del = table.selectionModelProperty().getValue().getSelectedIndex();
        persons.remove(del);

        table.setItems(persons);
    }
    @FXML
    private void initialize(){
        persons = FXCollections.<Person>observableArrayList();

        person = new Person();

        tfName.textProperty().bindBidirectional(person.firstNameProperty());
        tfLname.textProperty().bindBidirectional(person.lastNameProperty());
        tfAddress.textProperty().bindBidirectional(person.addressProperty());
        cbSpeed.valueProperty().bindBidirectional(person.speedProperty());
        cbDuration.valueProperty().bindBidirectional(person.durationProperty());
        cbBandwidth.valueProperty().bindBidirectional(person.bandwidthProperty());

        table.prefWidthProperty().bind(table.getColumns().get(2).widthProperty().multiply(2.6));







        cbDuration.getItems().addAll(1,2);
        cbSpeed.getItems().addAll(2,5,10,20,50,100);
        cbBandwidth.getItems().addAll(1,5,10,100, "Flat");
}
@FXML
    private void save() {
    if (person.isValid()){
        persons = table.getItems();

        persons.add(new Person(tfName.getText(), tfLname.getText(), tfAddress.getText(),
                cbSpeed.getValue(), cbBandwidth.getValue(), cbDuration.getValue()));


    }else {

        StringBuilder errMsg = new StringBuilder();

        ArrayList<String> errList = person.errorsProperty().get();

        for(String errList1 : errList) {
            errMsg.append(errList1);
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Person can be saved!");
        alert.setHeaderText(null);
        alert.setContentText(errMsg.toString());
        alert.showAndWait();
        errList.clear();
    }
}


}