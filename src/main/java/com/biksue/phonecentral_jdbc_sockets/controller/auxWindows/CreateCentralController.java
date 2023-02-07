package com.biksue.phonecentral_jdbc_sockets.controller.auxWindows;

import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateCentralController implements Initializable {
    public JFXListView<Label> LisVieCountries;
    public JFXListView<Label> LisVieProvinces;
    public JFXListView<Label> LisVieCities;
    public Label LabException;
    public JFXButton ButCreateCentral;
    private double XOffset;
    private double YOffset;

    public void LisVieCountriesItemSelected(MouseEvent mouseEvent) {
        updateProvinceListView();
    }

    public void LisVieProvincesItemSelected(MouseEvent mouseEvent) {
        if (this.LisVieCountries.getSelectionModel().getSelectedItem() != null)
            updateCityListView();
    }

    public void LisVieCitiesItemSelected(MouseEvent mouseEvent) {
        if (this.LisVieProvinces.getSelectionModel().getSelectedItem() != null) {

        }
    }

    public void ButCreateCentralMouseClicked(MouseEvent mouseEvent) {
    }

    public void FonAweCloseMouseClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) this.ButCreateCentral.getParent().getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateCountryListView();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private void updateCityListView() {
    }

    private void updateProvinceListView() {
    }

    private void updateCountryListView() throws DAOException {
        ArrayList<Country> aux = Constants.mySQLDAOManager.getCountryDAO().getAll();
        Label label;
        for (Country c : aux) {
            label = new Label();
            label.setText(c.getName());
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font(null, FontWeight.BOLD, 14));
            this.LisVieCountries.getItems().add(label);
        }
    }

    public void AncPanTitleBarMouseDragged(MouseEvent mouseEvent) {
        this.ButCreateCentral.getParent().getScene().getWindow().setX(mouseEvent.getScreenX() - XOffset);
        this.ButCreateCentral.getParent().getScene().getWindow().setY(mouseEvent.getScreenY() - YOffset);
    }

    public void AncPanTitleBarMousePressed(MouseEvent mouseEvent) {
        XOffset = mouseEvent.getSceneX();
        YOffset = mouseEvent.getSceneY();
    }
}
