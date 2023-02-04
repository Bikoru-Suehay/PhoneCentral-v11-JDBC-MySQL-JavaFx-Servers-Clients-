package com.biksue.phonecentral_jdbc_sockets.model.DAO;

import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Call;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;

import java.util.ArrayList;

public interface CallDAO extends DAO<Call, Long> {
    ArrayList<Call> getAll(Long first, Long last) throws DAOException;

    ArrayList<Call> getAll(Long idRem) throws DAOException;

}
