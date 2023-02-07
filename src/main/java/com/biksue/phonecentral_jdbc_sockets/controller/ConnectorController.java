package com.biksue.phonecentral_jdbc_sockets.controller;

import com.biksue.phonecentral_jdbc_sockets.PHOCEv11;
import com.biksue.phonecentral_jdbc_sockets.controller.components.CentralViewController;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Central;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.biksue.phonecentral_jdbc_sockets.model.util.filters.CentralFilter;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConnectorController implements Initializable {
    public JFXButton AncPanStartButCreateLocation;
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
        try {
            FXMLLoader loader = new FXMLLoader(PHOCEv11.class.getResource("createCentral-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.initStyle(StageStyle.UNDECORATED);
            stg.setScene(scene);
            stg.showAndWait();
            this.updateAncPanHBoxCentralContainer(null, null);
        } catch (IOException | DAOException e) {
            e.printStackTrace();
        }
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
            updateAncPanHBoxCentralContainer(null, null);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    private void updateAncPanHBoxCentralContainer(String name, String country) throws DAOException {
        this.AncPanHBoxCentralContainer.getChildren().clear();
        ArrayList<Central> auxCentrals;
        if (name != null) auxCentrals = CentralFilter.filterByName(name);
        else auxCentrals = CentralFilter.filterByCountry(country);
        int centralNum = auxCentrals.size();
        this.AncPanLabNumberOfCentrals.setText(centralNum + "");
        Node[] auxNodes = new Node[centralNum];
        for (int i = 0; i < auxNodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(PHOCEv11.class.getResource("central-view.fxml"));
                auxNodes[i] = loader.load();
                CentralViewController controller = loader.getController();
                controller.ini(
                        auxCentrals.get(i).getId() + "",
                        auxCentrals.get(i).getName(),
                        Constants.mySQLDAOManager.getCountryDAO().get(auxCentrals.get(i).getCountry()).getName(),
                        Constants.mySQLDAOManager.getProvinceDAO().get(auxCentrals.get(i).getProvince()).getName(),
                        Constants.mySQLDAOManager.getCityDAO().get(auxCentrals.get(i).getCity()).getName()
                );
                auxNodes[i].setUserData(auxCentrals.get(i));
                auxNodes[i].setTranslateY(6);
                this.AncPanHBoxCentralContainer.getChildren().add(auxNodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        eventAncPanHBoxCentralContainer();
    }

    private void eventAncPanHBoxCentralContainer() {
        for (int j = 0; j < this.AncPanHBoxCentralContainer.getChildren().size(); j++) {
            final int o = j;
            this.AncPanHBoxCentralContainer.getChildren().get(j).setOnMouseExited(event -> {
                this.AncPanHBoxCentralContainer.getChildren().get(o).setStyle("-fx-background-color :  rgba(85, 85, 85,0.20)");
                this.AncPanHBoxCentralContainer.getChildren().get(o).setTranslateY(3);
            });
            this.AncPanHBoxCentralContainer.getChildren().get(j).setOnMouseEntered(event -> {
                this.AncPanHBoxCentralContainer.getChildren().get(o).setStyle("-fx-background-color : rgba(125, 179, 91, 0.20)");
                this.AncPanHBoxCentralContainer.getChildren().get(o).setTranslateY(-3);
            });
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

    public void AncPanTexFieFilterByNameTextChanged(KeyEvent inputMethodEvent) throws DAOException {
        updateAncPanHBoxCentralContainer(this.AncPanTexFieFilterByName.getText(), null);
    }

    public void AncPanTexFieFilterByCountryTextChanged(KeyEvent inputMethodEvent) throws DAOException {
        updateAncPanHBoxCentralContainer(null, this.AncPanTexFieFilterByCountry.getText());
    }

    public void AncPanStartButCreateLocationAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(PHOCEv11.class.getResource("createLocation-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.initStyle(StageStyle.UNDECORATED);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}