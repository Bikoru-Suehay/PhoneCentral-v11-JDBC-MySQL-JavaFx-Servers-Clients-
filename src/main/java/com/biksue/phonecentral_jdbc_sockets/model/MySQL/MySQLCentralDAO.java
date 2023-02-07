package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.CentralDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.Central;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCentralDAO implements CentralDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO central( name, idCountry, idProvince, idCity) VALUES( ?, ?, ?, ?)";
    final String UPDATE = "UPDATE central SET name=?, idCountry=?, idProvince=?, idCity=? WHERE id=?";
    final String DELETE = "DELETE FROM central WHERE id=?";
    final String GETALL = "SELECT * FROM central";
    final String GETONE = "SELECT * FROM central WHERE id=?";

    public MySQLCentralDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Central central) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, central.getName());
            statement.setLong(2, central.getCountry());
            statement.setLong(3, central.getProvince());
            statement.setLong(4, central.getCity());
            if (statement.executeUpdate() == 0) {
                System.out.println("pinga");
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
    public void modify(Central central) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, central.getName());
            statement.setLong(2, central.getCountry());
            statement.setLong(3, central.getProvince());
            statement.setLong(4, central.getCity());
            statement.setLong(5, central.getId());
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
    public void delete(Central central) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, central.getId());
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
    public ArrayList<Central> getAll() throws DAOException {
        ArrayList<Central> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Central central = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                central = new Central();
                central.setId(resultSet.getLong("id"));
                central.setName(resultSet.getString("name"));
                central.setCountry(resultSet.getLong("idCountry"));
                central.setProvince(resultSet.getLong("idProvince"));
                central.setCity(resultSet.getLong("idCity"));
                list.add(central);
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
    public Central get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Central central = null;
        try {
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                central = new Central();
                central.setId(resultSet.getLong("id"));
                central.setName(resultSet.getString("name"));
                central.setCountry(resultSet.getLong("idCountry"));
                central.setProvince(resultSet.getLong("idProvince"));
                central.setCity(resultSet.getLong("idCity"));
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
        return central;
    }

    @Override
    public Central get(String name) throws DAOException {
        return null;
    }
}
