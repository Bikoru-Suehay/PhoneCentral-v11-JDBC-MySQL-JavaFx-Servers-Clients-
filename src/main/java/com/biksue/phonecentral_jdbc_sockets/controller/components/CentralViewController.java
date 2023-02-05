package com.biksue.phonecentral_jdbc_sockets.controller.components;

import javafx.scene.control.Label;

public class CentralViewController {
    public Label LabCentralId1;
    public Label LabCentralName1;
    public Label LabCountryName1;
    public Label LabProvinceName1;
    public Label LabCityName1;

    public void ini(String id, String name, String country, String province, String city){
        this.LabCentralId1.setText("ID: "+id);
        this.LabCentralName1.setText("NA: "+name);
        this.LabCountryName1.setText("CO: "+country);
        this.LabProvinceName1.setText("PR: "+province);
        this.LabCityName1.setText("CI: "+city);
    }
}
