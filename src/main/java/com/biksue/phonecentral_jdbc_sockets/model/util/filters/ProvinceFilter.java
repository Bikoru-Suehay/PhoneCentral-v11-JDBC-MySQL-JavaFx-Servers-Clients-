package com.biksue.phonecentral_jdbc_sockets.model.util.filters;

import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.Constants;

import java.util.ArrayList;

public class ProvinceFilter {
    public static ArrayList<Province> list() throws DAOException {
        return Constants.mySQLDAOManager.getProvinceDAO().getAll();
    }
    public static ArrayList<Province> filterById(Long id) throws DAOException {
        if (id == null) return list();
        ArrayList<Province> list = new ArrayList<>();
        for (Province p : list())
            if (p.getId().equals(id)) list.add(p);
        return list;
    }
    public static ArrayList<Province> filterByName(String name) throws DAOException {
        if (name == null) return list();
        if (name.isBlank()) return list();
        final String aux=name.toLowerCase();
        ArrayList<Province> list = new ArrayList<>();
        for (Province p : list())
            if (p.getName().toLowerCase().contains(aux)) list.add(p);
        return list;
    }
    public static ArrayList<Province> filterByIdCountry(Long idCountry) throws DAOException {
        if (idCountry == null) return list();
        ArrayList<Province> list = new ArrayList<>();
        for (Province p : list())
            if (p.getIdCountry().equals(idCountry)) list.add(p);
        return list;
    }
}
