package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.ClientDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Client;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLClientDAO implements ClientDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO clients( centralId, name, lastName, type, idCountry) VALUES( ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE clients SET centralId=?, name=?, lastName=?, type=?, idCountry=? WHERE id=?";
    final String DELETE = "DELETE FROM clients WHERE id=?";
    final String GETALL = "SELECT * FROM clients";
    final String GETONE = "SELECT * FROM clients WHERE id=?";

    public MySQLClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Client client) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1, client.getCentralId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getType());
            statement.setLong(5, client.getIdCountry());

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
    public void modify(Client client) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setLong(1, client.getCentralId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getLastName());
            statement.setString(4, client.getType());
            statement.setLong(5, client.getIdCountry());
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
    public void delete(Client client) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, client.getId());
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
    public ArrayList<Client> getAll() throws DAOException {
        ArrayList<Client> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setCentralId(resultSet.getLong("centralId"));
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setType(resultSet.getString("type"));
                client.setIdCountry(resultSet.getLong("idCountry"));
                list.add(client);
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
            if (resultSet != null) {
                try {
                    ConnectionTool.close(resultSet);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
        return list;
    }

    @Override
    public Client get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setCentralId(resultSet.getLong("centralId"));
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setType(resultSet.getString("type"));
                client.setIdCountry(resultSet.getLong("idCountry"));
            } else throw new DAOException("No se ha encontrado el registro.");

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
            if (resultSet != null) {
                try {
                    ConnectionTool.close(resultSet);
                } catch (SQLException e) {
                    throw new DAOException("Error en SQL", e);
                }
            }
        }
        return client;
    }

    @Override
    public Client get(String name) throws DAOException {
        return null;
    }
}
