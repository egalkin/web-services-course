package com.itmo.egalkin.model;

import com.itmo.egalkin.util.ConnectionUtils;
import com.itmo.egalkin.util.DeviceUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    private static String DEFAULT_SQL_QUERY = "select * from device";

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

    private interface NumberParseFunction<T, R> {
        R apply(T t) throws NumberFormatException;
    }

}