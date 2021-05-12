package com.itmo.egalkin.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "device", propOrder = {
    "available",
    "name",
    "price",
    "releaseYear",
    "type"
})
public class Device {

    protected boolean available;
    protected String name;
    protected double price;
    protected int releaseYear;
    protected String type;

    /**
     * Gets the value of the available property.
     *
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the value of the available property.
     *
     */
    public void setAvailable(boolean value) {
        this.available = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the price property.
     *
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the releaseYear property.
     *
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the value of the releaseYear property.
     *
     */
    public void setReleaseYear(int value) {
        this.releaseYear = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return "Device{" + "name=" + name +
            ", price=" + price +
            ", type=" + type +
            ", available=" +
            available +
            ", release year=" + releaseYear +
            '}';
    }

}
