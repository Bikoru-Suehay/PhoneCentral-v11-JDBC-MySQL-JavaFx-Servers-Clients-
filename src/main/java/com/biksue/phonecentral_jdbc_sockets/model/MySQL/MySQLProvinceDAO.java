package com.biksue.phonecentral_jdbc_sockets.model.MySQL;

import com.biksue.phonecentral_jdbc_sockets.model.DAO.ProvinceDAO;
import com.biksue.phonecentral_jdbc_sockets.model.entity.places.Province;
import com.biksue.phonecentral_jdbc_sockets.model.exceptions.DAOException;
import com.biksue.phonecentral_jdbc_sockets.model.util.ConnectionTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLProvinceDAO implements ProvinceDAO {
    private Connection connection;
    final String INSERT = "INSERT INTO province( id, name, idCountry) VALUES( ?, ?, ?)";
    final String UPDATE = "UPDATE province SET name=?, idCountry=? WHERE id=?";
    final String DELETE = "DELETE FROM province WHERE id=?";
    final String GETALL = "SELECT * FROM province";
    final String GETONE = "SELECT * FROM province WHERE id=?";

    public MySQLProvinceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Province province) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setLong(1, province.getId());
            statement.setString(2, province.getName());
            statement.setLong(3, province.getIdCountry());
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
    public void modify(Province province) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, province.getName());
            statement.setLong(2, province.getIdCountry());
            statement.setLong(3, province.getId());

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
    public void delete(Province province) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setLong(1, province.getId());
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
    public ArrayList<Province> getAll() throws DAOException {
        ArrayList<Province> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Province province = null;
        try {
            statement = connection.prepareStatement(GETALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                province = new Province();
                province.setId(resultSet.getLong("id"));
                province.setName(resultSet.getString("name"));
                province.setIdCountry(resultSet.getLong("idCountry"));

                list.add(province);
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
    public Province get(Long id) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Province province = null;
        try {
            statement = connection.prepareStatement(GETONE);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                province = new Province();
                province.setId(resultSet.getLong("id"));
                province.setName(resultSet.getString("name"));
                province.setIdCountry(resultSet.getLong("idCountry"));
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
        return province;
    }
}
