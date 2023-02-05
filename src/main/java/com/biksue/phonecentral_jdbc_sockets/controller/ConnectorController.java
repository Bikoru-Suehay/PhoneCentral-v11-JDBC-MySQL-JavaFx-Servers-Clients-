package com.biksue.phonecentral_jdbc_sockets.controller;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.DAOManager;
import com.biksue.phonecentral_jdbc_sockets.model.MySQL.MySQLDAOManager;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectorController implements Initializable {
    private double XOffset;
    private double YOffset;
    public AnchorPane AncPanLeftButtons;
    public AnchorPane AncPanStart;
    public AnchorPane AncPanStartButtons;
    public JFXButton AncPanStartButCreateCentral;
    public JFXButton AncPanStartButModifyCentral;
    public JFXButton AncPanStartButDeleteCentral;
    public JFXButton AncPanStartButInformation;
    public JFXButton AncPanStartButWorkCentral;
    public HBox AncPanHBoxCentralContainer;
    public TextField AncPanTexFieFilterByName;
    public TextField AncPanTexFieFilterByCountry;
    public Label AncPanLabNumberOfCentrals;
    public FontAwesomeIconView FonAweClose;

    public void AncPanStartButCreateCentralAction(ActionEvent actionEvent) {
    }

    public void AncPanStartButModifyCentralAction(ActionEvent actionEvent) {
    }

    public void AncPanStartButDeleteCentralAction(ActionEvent actionEvent) {
    }

    public void AncPanStartButInformationAction(ActionEvent actionEvent) {
    }

    public void AncPanStartButWorkCentralAction(ActionEvent actionEvent) {
    }

    public void AncPanTitleBarMouseDragged(MouseEvent mouseEvent) {
        this.FonAweClose.getParent().getScene().getWindow().setX(mouseEvent.getScreenX() - XOffset);
        this.FonAweClose.getParent().getScene().getWindow().setY(mouseEvent.getScreenY() - YOffset);
    }

    public void AncPanTitleBarMousePressed(MouseEvent mouseEvent) {
        XOffset = mouseEvent.getSceneX();
        YOffset = mouseEvent.getSceneY();
    }

    public void FonAweCloseAction(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}