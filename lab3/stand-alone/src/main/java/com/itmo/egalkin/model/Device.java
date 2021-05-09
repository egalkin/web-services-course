package com.itmo.egalkin.model;

/**
 * @author egalkin
 * @since 22.03.2021
 */
public class Device {
    private long id;
    private String name;
    private double price;
    private String type;
    private boolean available;
    private int releaseYear;

    public Device() {

    }

    public Device(long id,
                  String name,
                  double price,
                  String type,
                  boolean availability,
                  int releaseYear) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.available = availability;
        this.releaseYear = releaseYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Device{" + "name=" + name +
            ", price=" + price +
            ", type=" + type +
            ", available=" +
            available +
            ", release year =" + releaseYear +
            '}';
    }
}
