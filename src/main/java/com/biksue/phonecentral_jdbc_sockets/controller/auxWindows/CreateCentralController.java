package com.biksue.phonecentral_jdbc_sockets.controller.auxWindows;

import com.biksue.phonecentral_jdbc_sockets.model.entity.places.City;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.CityFilter;
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
    private long idCountry;
    private long idProvince;
    private long idCity;

    public JFXListView<Label> LisVieCountries;
    public JFXListView<Label> LisVieProvinces;
    public JFXListView<Label> LisVieCities;
    public Label LabException;
    public JFXButton ButCreateCentral;
    private double XOffset;
    private double YOffset;

    public void LisVieCountriesItemSelected(MouseEvent mouseEvent) {
        idProvince = 0;
        idCity = 0;
        updateProvinceListView();
    }

    public void LisVieProvincesItemSelected(MouseEvent mouseEvent) {
        idCity = 0;
        if (this.LisVieCountries.getSelectionModel().getSelectedItem() != null)
            updateCityListView();
    }

    public void LisVieCitiesItemSelected(MouseEvent mouseEvent) {
        idCity = Long.parseLong(this.LisVieCities.getSelectionModel().getSelectedItem().getAccessibleText());
    }

    public void ButCreateCentralMouseClicked(MouseEvent mouseEvent) {
        try {
            if (idCountry == 0 || idProvince == 0 || idCity == 0) throw new DAOException("Gato exception, XD...");
        }catch (DAOException e){
            this.LabException.setTextFill(Color.RED);
        }
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
        try {
            this.LisVieCities.getItems().clear();
            idProvince = Long.parseLong(this.LisVieProvinces.getSelectionModel().getSelectedItem().getAccessibleText());
            ArrayList<City> aux = Constants.mySQLDAOManager.getCityDAO().getAll();
            Label label;
            for (City c : CityFilter.filterByIdProvince(idProvince)) {
                System.out.println("gato");
                label = new Label();
                label.setText(c.getName());
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font(null, FontWeight.BOLD, 14));
                label.setAccessibleText(c.getId() + "");
                this.LisVieCities.getItems().add(label);
            }
        } catch (DAOException e) {
            this.LabException.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    private void updateProvinceListView() {
        try {
            this.LisVieProvinces.getItems().clear();
            idCountry = Long.parseLong(this.LisVieCountries.getSelectionModel().getSelectedItem().getAccessibleText());
            ArrayList<Province> aux = Constants.mySQLDAOManager.getProvinceDAO().getAll();
            Label label;
            for (Province c : aux) {
                if (c.getIdCountry().equals(idCountry)) {
                    System.out.println("gato");
                    label = new Label();
                    label.setText(c.getName());
                    label.setTextFill(Color.WHITE);
                    label.setFont(Font.font(null, FontWeight.BOLD, 14));
                    label.setAccessibleText(c.getId() + "");
                    label.setAccessibleRoleDescription(c.getIdCountry() + "");
                    this.LisVieProvinces.getItems().add(label);
                }
            }
        } catch (DAOException e) {
            this.LabException.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    private void updateCountryListView() throws DAOException {
        this.LisVieCountries.getItems().clear();
        ArrayList<Country> aux = Constants.mySQLDAOManager.getCountryDAO().getAll();
        Label label;
        for (Country c : aux) {
            label = new Label();
            label.setText(c.getName());
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font(null, FontWeight.BOLD, 14));
            label.setAccessibleText(c.getId() + "");
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
