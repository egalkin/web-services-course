package com.itmo.egalkin.model;

import com.itmo.egalkin.exception.DeviceNotFoundException;
import com.itmo.egalkin.exception.IllegalNameException;
import com.itmo.egalkin.exception.InvalidReleaseYearException;
import com.itmo.egalkin.exception.NegativePriceException;
import com.itmo.egalkin.exception.fault.DeviceNotFoundFault;
import com.itmo.egalkin.exception.fault.IllegalNameFault;
import com.itmo.egalkin.exception.fault.InvalidReleaseYearFault;
import com.itmo.egalkin.exception.fault.NegativePriceFault;
import com.itmo.egalkin.util.ConnectionUtils;
import com.itmo.egalkin.util.DeviceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author egalkin
 * @since 22.03.2021
 */
public class PostgreSQLDAO {

    private static final String INSERTION_QUERY = "insert into device(name, price, type, available, releaseYear) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE device \n" +
        "SET name = ?,\n" +
        "    price = ?,\n" +
        "    type = ?,\n" +
        "    available = ?,\n" +
        "    releaseyear = ?\n" +
        "WHERE id = ?";
    private static final String GET_QUERY = "select * from device where id = ?";
    private static final String DELETE_QUERY = "delete from device where id = ?";
    private static final String DEFAULT_SQL_QUERY = "select * from device";
    private static final Calendar calendar = Calendar.getInstance();

    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        try (Connection connection = ConnectionUtils.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(DEFAULT_SQL_QUERY);
            while (rs.next()) {
                devices.add(DeviceUtils.extractDevice(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return devices;
    }

    public List<Device> getDevices(String name,
                                   String price,
                                   String type,
                                   String available,
                                   String releaseYear) {
        List<Device> devices = new ArrayList<>();
        HashMap<String, String> processedParams = processParams(name, price, type, available, releaseYear);
        String sqlQuery = buildSqlQuery(processedParams);
        try (Connection connection = ConnectionUtils.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                devices.add(DeviceUtils.extractDevice(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return devices;
    }

    public Device getDevice(long id) throws DeviceNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(GET_QUERY);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return DeviceUtils.extractDevice(rs);
            } else {
                throw new DeviceNotFoundException("Device not found", DeviceNotFoundFault.buildInstance(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public long createDevice(String name,
                             double price,
                             String type,
                             boolean available,
                             int releaseYear) throws NegativePriceException, InvalidReleaseYearException, IllegalNameException  {
        checkName(name);
        checkPrice(price);
        checkReleaseYear(releaseYear);
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(INSERTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            prepareStatement(stmt, name, price,type, available, releaseYear);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public String updateDevice(long id,
                               String name,
                               double price,
                               String type,
                               boolean available,
                               int releaseYear) throws NegativePriceException, InvalidReleaseYearException, IllegalNameException, DeviceNotFoundException {
        checkName(name);
        checkPrice(price);
        checkReleaseYear(releaseYear);
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);
            prepareStatement(stmt, name, price, type, available, releaseYear);
            stmt.setLong(6, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows != 0) {
                return "Updated";
            } else {
                throw new DeviceNotFoundException("Device not found", DeviceNotFoundFault.buildInstance(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    public String deleteDevice(long id) throws DeviceNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);
            stmt.setLong(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows != 0) {
                return "Deleted";
            } else {
                throw new DeviceNotFoundException("Device not found", DeviceNotFoundFault.buildInstance(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    private void prepareStatement(PreparedStatement stmt,
                                  String name,
                                  double price,
                                  String type,
                                  boolean available,
                                  int releaseYear) throws SQLException{
        stmt.setString(1, name);
        stmt.setDouble(2, price);
        stmt.setString(3, type);
        stmt.setBoolean(4, available);
        stmt.setInt(5, releaseYear);
    }

    private HashMap<String, String> processParams(String name,
                                                  String price,
                                                  String type,
                                                  String available,
                                                  String releaseYear) {
        HashMap<String, String> params = new HashMap<String, String>() {{
            put("name", name);
            put("price", price);
            put("type", type);
            put("available", available);
            put("releaseYear", releaseYear);
        }};
        List<String> keysToRemove = new ArrayList<>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null || value.isEmpty()) {
                keysToRemove.add(key);
                continue;
            }
            if (key.equals("price") && detectInvalidNumericParameter(value, Double::parseDouble)) {
                keysToRemove.add(key);
            } else if (key.equals("releaseYear") && detectInvalidNumericParameter(value, Integer::parseInt)) {
                keysToRemove.add(key);
            }
        }
        params.keySet().removeAll(keysToRemove);
        return params;
    }

    private String buildSqlQuery(HashMap<String, String> params) {
        String sqlQuery = DEFAULT_SQL_QUERY;
        if (!params.isEmpty()) {
            StringJoiner whereClause = new StringJoiner(" and ", " where ", "");
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (key.equals("available")) {
                    whereClause.add(key + "=" + Boolean.parseBoolean(value));
                } else if (key.equals("price") || key.equals("releaseYear")) {
                    whereClause.add(key + "=" + value);
                } else {
                    whereClause.add(key + "=" + "'" + value + "'");
                }
            }
            sqlQuery = String.format("%s%s", DEFAULT_SQL_QUERY, whereClause.toString());
        }
        return sqlQuery;
    }

    private <T> boolean detectInvalidNumericParameter(String value,
                                                      NumberParseFunction<String, T> parseFunction) {
        try {
            parseFunction.apply(value);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }

    private void checkPrice(double price) throws NegativePriceException {
        if (price < 0.0) {
            throw new NegativePriceException("Negative price", NegativePriceFault.buildInstance(price));
        }
    }

    private void checkReleaseYear(int year) throws InvalidReleaseYearException {
        if (year < 1950 || year > calendar.get(Calendar.YEAR)) {
            throw new InvalidReleaseYearException("Invalid year", InvalidReleaseYearFault.buildInstance(year));
        }
    }

    private void checkName(String name) throws IllegalNameException {
        if (name == null || name.isEmpty()) {
            throw new IllegalNameException("Device name isn't specified", IllegalNameFault.buildInstance());
        }
    }


    private interface NumberParseFunction<T, R> {
        R apply(T t) throws NumberFormatException;
    }

}