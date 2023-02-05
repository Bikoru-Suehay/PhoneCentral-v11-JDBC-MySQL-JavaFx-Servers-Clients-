package com.biksue.phonecentral_jdbc_sockets.model.util.filters;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Place;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;

import java.util.ArrayList;

public class Filter {
    public static ArrayList<Place> ById(ArrayList<Place> list, Long id){
        if (id == null) return list;
        ArrayList<Place> aux = new ArrayList<>();
        for (Place p : list)
            if (p.getId().equals(id)) aux.add(p);
        return aux;
    }
    public static ArrayList<Place> byName(ArrayList<Place> list, String name){
        if (name == null) return list;
        ArrayList<Place> aux = new ArrayList<>();
        for (Place p : list)
            if (p.getName().contains(name)) aux.add(p);
        return aux;
    }
}
