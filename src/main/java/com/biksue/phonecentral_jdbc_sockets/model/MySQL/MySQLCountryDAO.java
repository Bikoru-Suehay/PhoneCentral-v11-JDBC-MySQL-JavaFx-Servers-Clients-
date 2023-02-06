package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.CountryDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO country( name, rate) VALUES( ?, ?)";
    final String UPDATE = "UPDATE country SET name=?, rate=? WHERE id=?";
    final String DELETE = "DELETE FROM country WHERE id=?";
    final String GETALL = "SELECT * FROM country";
    final String GETONE = "SELECT * FROM country WHERE id=?";
    final String GETONENAME = "SELECT * FROM country WHERE name=?";

    public MySQLCountryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Country country) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, country.getName());
            statement.setLong(2, country.getRate());
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
    public void modify(Country country) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, country.getName());
            statement.setLong(2, country.getRate());
            statement.setLong(3, country.getId());

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
    public void delete(Country country) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, country.getId());
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
    public ArrayList<Country> getAll() throws DAOException {
        ArrayList<Country> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setName(resultSet.getString("name"));
                country.setRate(resultSet.getLong("rate"));

                list.add(country);
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
    public Country get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setName(resultSet.getString("name"));
                country.setRate(resultSet.getLong("rate"));
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
        return country;
    }
    public Country get(String name) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            statement = connection.prepareStatement(GETONENAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getLong("id"));
                country.setName(resultSet.getString("name"));
                country.setRate(resultSet.getLong("rate"));
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
        return country;
    }
}
