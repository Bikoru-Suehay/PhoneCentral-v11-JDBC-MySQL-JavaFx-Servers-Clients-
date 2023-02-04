package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.*;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Client;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLDAOManager implements DAOManager {
    private Connection connection;
    private ClientDAO clientDAO=null;
    private CentralDAO centralDAO=null;
    private CountryDAO countryDAO=null;
    private NumbersDAO numbersDAO=null;
    private CallDAO callDAO=null;
    private ProvinceDAO provinceDAO=null;
    private CityDAO cityDAO=null;
    public MySQLDAOManager(String host, String userName, String password, String dataBase) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dataBase+"?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true", userName, password);
    }

    @Override
    public ClientDAO getClientDAO() {
        if(clientDAO==null){
            clientDAO=new MySQLClientDAO(connection);
        }
        return clientDAO;
    }

    @Override
    public CentralDAO getCentralDAO() {
        if(centralDAO==null){
            centralDAO=new MySQLCentralDAO(connection);
        }
        return centralDAO;
    }

    @Override
    public CountryDAO getCountryDAO() {
        if(countryDAO==null){
            countryDAO=new MySQLCountryDAO(connection);
        }
        return countryDAO;
    }

    @Override
    public NumbersDAO getNumbersDAO() {
        if(numbersDAO==null){
            numbersDAO=new MySQLNumbersDAO(connection);
        }
        return numbersDAO;
    }

    @Override
    public CallDAO getCallDAO() {
        if(callDAO==null){
            callDAO=new MySQLCallDAO(connection);
        }
        return callDAO;
    }

    @Override
    public ProvinceDAO getProvinceDAO() {
        if(provinceDAO==null){
            provinceDAO=new MySQLProvinceDAO(connection);
        }
        return provinceDAO;
    }

    @Override
    public CityDAO getCityDAO() {
        if(cityDAO==null){
            cityDAO=new MySQLCityDAO(connection);
        }
        return cityDAO;
    }

    public static void main(String[] args) throws SQLException, DAOException {
        MySQLDAOManager man=new MySQLDAOManager("localHost","root", ConnectionTool.JDBC_PASSWORD,"phoneCentral");
        ArrayList<Client> clients=man.getClientDAO().getAll();
        for(Client c:clients)
            System.out.println(c.getName());
    }
}
