package com.biksue.phonecentral_jdbc_sockets.model.util;

import com.biksue.phonecentral_jdbc_sockets.model.MySQL.MySQLDAOManager;

import java.sql.SQLException;

public class Constants {
    public static final MySQLDAOManager mySQLDAOManager;
    static{
        try {
            mySQLDAOManager=new MySQLDAOManager("localHost","root", ConnectionTool.JDBC_PASSWORD,"phoneCentral");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
