package com.biksue.phonecentral_jdbc_sockets.model.util.filters;

import com.biksue.phonecentral_jdbc_sockets.model.entity.places.City;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;

import java.util.ArrayList;

public class CityFilter {
    public static ArrayList<City> list() throws DAOException {
        return Constants.mySQLDAOManager.getCityDAO().getAll();
    }
    public static ArrayList<City> filterById(Long id) throws DAOException {
        if (id == null) return list();
        ArrayList<City> list = new ArrayList<>();
        for (City c : list())
            if (c.getId().equals(id)) list.add(c);
        return list;
    }
    public static ArrayList<City> filterByName(String name) throws DAOException {
        if (name == null) return list();
        if (name.isBlank()) return list();
        final String aux=name.toLowerCase();
        ArrayList<City> list = new ArrayList<>();
        for (City c : list())
            if (c.getName().toLowerCase().contains(aux)) list.add(c);
        return list;
    }
    public static ArrayList<City> filterByIdCountry(Long idCountry) throws DAOException {
        if (idCountry == null) return list();
        ArrayList<City> list = new ArrayList<>();
        for (City c : list())
            if (c.getIdCountry().equals(idCountry)) list.add(c);
        return list;
    }
    public static ArrayList<City> filterByIdProvince(Long idProvince) throws DAOException {
        if (idProvince == null) return list();
        ArrayList<City> list = new ArrayList<>();
        for (City c : list())
            if (c.getIdProvince().equals(idProvince)) list.add(c);
        return list;
    }
}
