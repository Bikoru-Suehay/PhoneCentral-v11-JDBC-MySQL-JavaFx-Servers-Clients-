package com.biksue.phonecentral_jdbc_sockets.controller.auxWindows;

import com.biksue.phonecentral_jdbc_sockets.model.entity.places.City;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateLocationController {
    public FontAwesomeIconView FontAweClose;
    public TextField TexFieCountryName;
    public TextField TexFieProvinceName;
    public TextField TexFieCityName;
    public JFXButton ButCreate;
    public Label LabException;
    public TextField TexFieCountryRate;
    private double XOffset;
    private double YOffset;

    public void AncPanTitleBarMouseDragged(MouseEvent mouseEvent) {
        this.FontAweClose.getParent().getScene().getWindow().setX(mouseEvent.getScreenX() - XOffset);
        this.FontAweClose.getParent().getScene().getWindow().setY(mouseEvent.getScreenY() - YOffset);
    }

    public void AncPanTitleBarMousePressed(MouseEvent mouseEvent) {
        XOffset = mouseEvent.getSceneX();
        YOffset = mouseEvent.getSceneY();
    }

    public void FontAweCloseMouseClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) this.FontAweClose.getParent().getScene().getWindow();
        stage.close();
    }

    public void ButCreateAction(ActionEvent actionEvent) throws DAOException {
        if (this.TexFieCountryName.getCharacters().isEmpty() || this.TexFieProvinceName.getCharacters().isEmpty() || this.TexFieCityName.getCharacters().isEmpty() || this.TexFieCountryRate.getCharacters().isEmpty()) {
            this.LabException.setTextFill(new Color(0.98, 0.32, 0.32, 1));
            return;
        }
        if (this.TexFieCountryName.getText().isBlank() || this.TexFieProvinceName.getText().isBlank() || this.TexFieCityName.getText().isBlank() || this.TexFieCountryRate.getText().isBlank()) {
            this.LabException.setTextFill(new Color(0.98, 0.32, 0.32, 1));
            return;
        }
        if (!this.TexFieCountryName.getText().matches("[a-zA-Z]+") || !this.TexFieProvinceName.getText().matches("[a-zA-Z]+") || !this.TexFieCityName.getText().matches("[a-zA-Z]+") || !this.TexFieCountryRate.getText().matches("[0-9]+")) {
            this.LabException.setTextFill(new Color(0.98, 0.32, 0.32, 1));
            return;
        }
        Country aux2=new Country(this.TexFieCountryName.getText());
        aux2.setRate(Long.parseLong(this.TexFieCountryRate.getText()));
        Constants.mySQLDAOManager.getCountryDAO().insert(aux2);
        Country aux = Constants.mySQLDAOManager.getCountryDAO().get(this.TexFieCountryName.getText());
        Constants.mySQLDAOManager.getProvinceDAO().insert(new Province(this.TexFieProvinceName.getText(), aux.getId()));
        Province aux1 = Constants.mySQLDAOManager.getProvinceDAO().get(this.TexFieProvinceName.getText());
        Constants.mySQLDAOManager.getCityDAO().insert(new City(this.TexFieCityName.getText(), aux.getId(), aux1.getId()));
        Stage stage = (Stage) this.FontAweClose.getParent().getScene().getWindow();
        stage.close();
    }
}
