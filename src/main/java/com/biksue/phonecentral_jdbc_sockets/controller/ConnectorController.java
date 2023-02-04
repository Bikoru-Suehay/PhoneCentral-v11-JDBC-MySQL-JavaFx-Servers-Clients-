package com.biksue.phonecentral_jdbc_sockets.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ConnectorController {
    public AnchorPane TittleBar;
    public JFXButton ButtonClose;
    public JFXButton ButtonCentralPage;
    public JFXButton ButtonClient;
    public JFXButton ButtonOptions;
    public JFXButton ButtonHelpPage;
    public AnchorPane AnchorPaneCentralPage;
    public JFXButton ButtonCreateCentral;
    public JFXButton ButtonDeleteCentral;
    public HBox HBoxCentrals;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void TittleBarMouseDragged(MouseEvent mouseEvent) {
    }

    public void TittleBarMousePressed(MouseEvent mouseEvent) {
    }
    public void ButtonCloseMouseEntered(MouseEvent mouseEvent){

    }
    public void ButtonCloseAction(ActionEvent actionEvent) {
    }

    public void ButtonCloseMouseExited(MouseDragEvent mouseDragEvent) {
    }

    public void ButtonCentralPageAction(ActionEvent actionEvent) {
    }

    public void ButtonClientPageAction(ActionEvent actionEvent) {
    }

    public void ButtonOptionsPageAction(ActionEvent actionEvent) {
    }

    public void ButtonHelpPageAction(ActionEvent actionEvent) {
    }

    public void ButtonCreateCentralAction(ActionEvent actionEvent) {
    }

    public void ButtonDeleteCentralAction(ActionEvent actionEvent) {
    }
}