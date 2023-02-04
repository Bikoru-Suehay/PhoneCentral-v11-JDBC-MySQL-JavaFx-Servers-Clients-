package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.NumbersDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Client;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Numbers;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLNumbersDAO implements NumbersDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO numbers( clientId, number) VALUES( ?, ?)";
    final String UPDATE = "UPDATE numbers SET clientId=? WHERE number=?";
    final String DELETE = "DELETE FROM numbers WHERE number=?";
    final String GETALL = "SELECT * FROM numbers";
    final String GETONE = "SELECT * FROM numbers WHERE clientId=?";

    public MySQLNumbersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Numbers numbers) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1, numbers.getIdClient());
            statement.setString(2, numbers.getNumber());

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
    public void modify(Numbers numbers) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setLong(1, numbers.getIdClient());
            statement.setString(2, numbers.getNumber());

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
    public void delete(Numbers numbers) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setString(1, numbers.getNumber());
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
    public ArrayList<Numbers> getAll() throws DAOException {
        ArrayList<Numbers> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Numbers numbers = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numbers = new Numbers();
                numbers.setIdClient(resultSet.getLong("clientId"));
                numbers.setNumber(resultSet.getString("number"));

                list.add(numbers);
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
    public Numbers get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Numbers numbers = null;
        try {
            statement = connection.prepareStatement(GETONE);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numbers = new Numbers();
                numbers.setIdClient(resultSet.getLong("clientId"));
                numbers.setNumber(resultSet.getString("number"));
            } else throw new DAOException("No se ha encontrado el registro.");
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
        return numbers;
    }
}
