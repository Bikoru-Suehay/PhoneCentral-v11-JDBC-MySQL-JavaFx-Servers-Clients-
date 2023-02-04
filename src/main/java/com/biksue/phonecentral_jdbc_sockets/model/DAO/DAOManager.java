package com.biksue.phonecentral_jdbc_sockets.model.DAO;

public interface DAOManager {
    ClientDAO getClientDAO();
    CentralDAO getCentralDAO();
    CountryDAO getCountryDAO();
    NumbersDAO getNumbersDAO();
    CallDAO getCallDAO();
    ProvinceDAO getProvinceDAO();
    CityDAO getCityDAO();
}
