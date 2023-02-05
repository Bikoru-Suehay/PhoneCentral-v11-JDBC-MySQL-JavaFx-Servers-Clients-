package com.biksue.phonecentral_jdbc_sockets.model.util.filters;

import com.biksue.phonecentral_jdbc_sockets.model.entity.Central;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;

import java.util.ArrayList;

public class CountryFilter {
    public static ArrayList<Country> list() throws DAOException {
        return Constants.mySQLDAOManager.getCountryDAO().getAll();
    }
    public static ArrayList<Country> filterById(Long id) throws DAOException {
        if (id == null) return list();
        ArrayList<Country> list = new ArrayList<>();
        for (Country c : list())
            if (c.getId().equals(id)) list.add(c);
        return list;
    }
    public static ArrayList<Country> filterByName(String name) throws DAOException {
        if (name == null) return list();
        ArrayList<Country> list = new ArrayList<>();
        for (Country c : list())
            if (c.getName().contains(name)) list.add(c);
        return list;
    }
}
