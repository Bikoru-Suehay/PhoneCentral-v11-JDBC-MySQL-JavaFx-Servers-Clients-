package com.biksue.phonecentral_jdbc_sockets.controller.components;

import javafx.scene.control.Label;

public class CentralViewController {
    public Label LabCentralId;
    public Label LabCentralName;
    public Label LabCountryName;
    public Label LabProvinceName;
    public Label LabCityName;

    public void ini(String id, String name, String country, String province, String city){
        this.LabCentralId.setText(id);
        this.LabCentralName.setText(name);
        this.LabCountryName.setText(country);
        this.LabProvinceName.setText(province);
        this.LabCityName.setText(city);
    }
}
