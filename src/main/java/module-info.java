module com.biksue.phonecentral_jdbc_sockets {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.biksue.phonecentral_jdbc_sockets to javafx.fxml,com.jfoenix,javafx.controls;
    opens com.biksue.phonecentral_jdbc_sockets.controller to javafx.fxml,com.jfoenix,javafx.controls;
    exports com.biksue.phonecentral_jdbc_sockets;
    exports com.biksue.phonecentral_jdbc_sockets.controller;

}