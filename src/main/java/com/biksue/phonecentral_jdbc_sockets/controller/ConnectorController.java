package com.biksue.phonecentral_jdbc_sockets.controller;

import com.biksue.phonecentral_jdbc_sockets.controller.components.CentralViewController;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Central;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.CentralFilter;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.CityFilter;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.CountryFilter;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.ProvinceFilter;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        try {
            updateAncPanHBoxCentralContainer(null,null);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    private void updateAncPanHBoxCentralContainer(String name, String country) throws DAOException {
        this.AncPanHBoxCentralContainer.getChildren().clear();
        ArrayList<Central> auxCentrals;
        if (name != null) auxCentrals = CentralFilter.filterByName(name);
        else auxCentrals = CentralFilter.filterByCountry(country);

        Node[] auxNodes = new Node[auxCentrals.size()];
        for (int i = 0; i < auxNodes.length; i++) {
            try{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("central-view.fxml"));
                auxNodes=loader.load();
                CentralViewController controller=loader.getController();
                controller.ini(
                        auxCentrals.get(i).getId()+"",
                        auxCentrals.get(i).getName(),
                        Constants.mySQLDAOManager.getCountryDAO().get(auxCentrals.get(i).getCountry()).getName(),
                        Constants.mySQLDAOManager.getProvinceDAO().get(auxCentrals.get(i).getProvince()).getName(),
                        Constants.mySQLDAOManager.getCityDAO().get(auxCentrals.get(i).getCity()).getName()
                        );
                auxNodes[i].setUserData(auxCentrals.get(i));
                this.AncPanHBoxCentralContainer.getChildren().add(auxNodes[i]);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void AncPanTexFieFilterByCountryMouseClicked(MouseEvent mouseEvent) throws DAOException {
        this.AncPanTexFieFilterByName.setText("");
        updateAncPanHBoxCentralContainer(null, null);
    }

    public void AncPanTexFieFilterByNameMouseClicked(MouseEvent mouseEvent) throws DAOException {
        this.AncPanTexFieFilterByCountry.setText("");
        updateAncPanHBoxCentralContainer(null, null);
    }
}