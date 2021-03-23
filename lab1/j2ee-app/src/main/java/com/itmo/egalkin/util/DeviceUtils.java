package com.itmo.egalkin.util;

import com.itmo.egalkin.model.Device;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author egalkin
 * @since 22.03.2021
 */
public class DeviceUtils {

    public static Device extractDevice(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        String type = rs.getString("type");
        boolean available = rs.getBoolean("available");
        int releaseYear = rs.getInt("releaseYear");
        return new Device(name, price, type, available, releaseYear);
    }
}
