package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.CallDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.abstracts.Call;
import com.biksue.phonecentral_jdbc_sockets.model.entity.calls.InternationalCall;
import com.biksue.phonecentral_jdbc_sockets.model.entity.calls.NationalCall;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCallDAO implements CallDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO calls( remittentId, destinationId, lifeTime, date, nationality) VALUES( ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE calls SET lifeTime=?, date=?, nationality=? WHERE remittentId=?, destinationId=?";
    final String DELETE = "DELETE FROM calls WHERE id=?";
    final String GETALL = "SELECT * FROM calls";
    final String GETALLPER = "SELECT * FROM calls WHERE date >= ? and date<?";
    final String GETALLREM = "SELECT * FROM calls WHERE remittentId=?";

    public MySQLCallDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Call call) throws DAOException {
        PreparedStatement statement = null;
        final String REM = "SELECT idCountry FROM clients WHERE id=" + call.getRemittentId();
        final String DES = "SELECT idCountry FROM clients WHERE id=" + call.getDestinationId();
        try {
            statement = connection.prepareStatement(REM);
            Long rem = statement.executeQuery().getLong("nationality");
            statement = connection.prepareStatement(DES);
            Long des = statement.executeQuery().getLong("nationality");
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1, call.getRemittentId());
            statement.setLong(2, call.getDestinationId());
            statement.setLong(3, call.getLifeTime());
            statement.setLong(4, call.getDate());
            statement.setBoolean(5, rem.equals(des));
            if (statement.executeUpdate() == 0) {
                throw new DAOException("No se han guardado cambios.");
            }
        } catch (SQLException e) {
            throw new DAOException("No se han guardado cambios.");
        } finally {
            if (statement != null) {
                try {
                    ConnectionTool.close(statement);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
    }

    @Override
    public void modify(Call call) throws DAOException {
    }

    @Override
    public void delete(Call call) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, call.getId());
            if (statement.executeUpdate() == 0) {
                throw new DAOException("No se han guardado cambios.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    ConnectionTool.close(statement);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
    }

    @Override
    public ArrayList<Call> getAll() throws DAOException {
        ArrayList<Call> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Call call = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean("nationality")) call = new NationalCall();
                else call = new InternationalCall();
                call.setId(resultSet.getLong("id"));
                call.setRemittentId(resultSet.getLong("remittentId"));
                call.setDestinationId(resultSet.getLong("destinationId"));
                call.setLifeTime(resultSet.getLong("lifeTime"));
                call.setDate(resultSet.getLong("date"));
                call.estimateRate();
                call.estimatePrice();
                list.add(call);
            }

        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    ConnectionTool.close(resultSet);
                    ConnectionTool.close(statement);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
        return list;
    }

    @Override
    public ArrayList<Call> getAll(Long first, Long last) throws DAOException {
        ArrayList<Call> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Call call = null;
        try {
            statement = connection.prepareStatement(GETALLPER);
            statement.setLong(1, first);
            statement.setLong(2, last);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean("nationality")) call = new NationalCall();
                else call = new InternationalCall();
                call.setId(resultSet.getLong("id"));
                call.setRemittentId(resultSet.getLong("remittentId"));
                call.setDestinationId(resultSet.getLong("destinationId"));
                call.setLifeTime(resultSet.getLong("lifeTime"));
                call.setDate(resultSet.getLong("date"));
                call.estimateRate();
                call.estimatePrice();
                list.add(call);
            }

        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    ConnectionTool.close(resultSet);
                    ConnectionTool.close(statement);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
        return list;
    }

    @Override
    public ArrayList<Call> getAll(Long idRem) throws DAOException {
        ArrayList<Call> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Call call = null;
        try {
            statement = connection.prepareStatement(GETALLREM);
            statement.setLong(1, idRem);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean("nationality")) call = new NationalCall();
                else call = new InternationalCall();
                call.setId(resultSet.getLong("id"));
                call.setRemittentId(resultSet.getLong("remittentId"));
                call.setDestinationId(resultSet.getLong("destinationId"));
                call.setLifeTime(resultSet.getLong("lifeTime"));
                call.setDate(resultSet.getLong("date"));
                call.estimateRate();
                call.estimatePrice();
                list.add(call);
            }

        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            if (statement != null) {
                try {
                    ConnectionTool.close(resultSet);
                    ConnectionTool.close(statement);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
        return list;
    }

    @Override
    public Call get(Long id) throws DAOException {
        return null;
    }
}
