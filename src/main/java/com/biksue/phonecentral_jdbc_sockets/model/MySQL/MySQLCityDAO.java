package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.CityDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.City;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Country;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCityDAO implements CityDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO city( name, idCountry, idProvince) VALUES( ?, ?, ?)";
    final String UPDATE = "UPDATE city SET name=?, idCountry=?, idProvince=? WHERE id=?";
    final String DELETE = "DELETE FROM city WHERE id=?";
    final String GETALL = "SELECT * FROM city";
    final String GETONE = "SELECT * FROM city WHERE id=?";
    final String GETONENAME = "SELECT * FROM city WHERE name=?";


    public MySQLCityDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(City city) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, city.getName());
            statement.setLong(2,city.getIdCountry());
            statement.setLong(3, city.getIdProvince());
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
    public void modify(City city) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, city.getName());
            statement.setLong(2, city.getIdCountry());
            statement.setLong(3, city.getIdProvince());
            statement.setLong(4, city.getId());

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
    public void delete(City city) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, city.getId());
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
    public ArrayList<City> getAll() throws DAOException {
        ArrayList<City> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getLong("id"));
                city.setName(resultSet.getString("name"));
                city.setIdCountry(resultSet.getLong("idCountry"));
                city.setIdProvince(resultSet.getLong("idProvince"));
                list.add(city);
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
    public City get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getLong("id"));
                city.setName(resultSet.getString("name"));
                city.setIdCountry(resultSet.getLong("idCountry"));
                city.setIdProvince(resultSet.getLong("idProvince"));
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
        return city;
    }

    public City get(String name) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            statement = connection.prepareStatement(GETONENAME);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getLong("id"));
                city.setName(resultSet.getString("name"));
                city.setIdCountry(resultSet.getLong("idCountry"));
                city.setIdProvince(resultSet.getLong("idProvince"));
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
        return city;
    }
}
