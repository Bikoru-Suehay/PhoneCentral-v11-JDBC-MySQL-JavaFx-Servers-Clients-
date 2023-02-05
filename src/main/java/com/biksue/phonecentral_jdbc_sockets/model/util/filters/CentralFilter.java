package com.biksue.phonecentral_jdbc_sockets.model.util.filters;

import com.biksue.phonecentral_jdbc_sockets.model.entity.Central;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;

import java.util.ArrayList;

public class CentralFilter {

    public static ArrayList<Central> list() throws DAOException {
        return Constants.mySQLDAOManager.getCentralDAO().getAll();
    }
    public static ArrayList<Central> filterById(Long id) throws DAOException {
        if (id == null) return list();
        ArrayList<Central> list = new ArrayList<>();
        for (Central c : list())
            if (c.getId().equals(id)) list.add(c);
        return list;
    }
    public static ArrayList<Central> filterByName(String name) throws DAOException {
        if (name == null) return list();
        ArrayList<Central> list = new ArrayList<>();
        for (Central c : list())
            if (c.getName().equals(name)) list.add(c);
        return list;
    }
    public static ArrayList<Central> filterByCountry(String countryName) throws DAOException {
        if (countryName == null) return list();
        ArrayList<Central> list = new ArrayList<>();
        for (Central c : list())
            for(Country auxCountry:CountryFilter.filterByName(countryName))
                if (auxCountry.getName().equals(countryName)) list.add(c);
        return list;
    }
}
