
package com.halas.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for position complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="position">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coordinateX" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="coordinateY" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="coordinateZ" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "position", propOrder = {
    "coordinateX",
    "coordinateY",
    "coordinateZ"
})
public class Position {

    protected double coordinateX;
    protected double coordinateY;
    protected double coordinateZ;

    /**
     * Gets the value of the coordinateX property.
     * 
     */
    public double getCoordinateX() {
        return coordinateX;
    }

    /**
     * Sets the value of the coordinateX property.
     * 
     */
    public void setCoordinateX(double value) {
        this.coordinateX = value;
    }

    /**
     * Gets the value of the coordinateY property.
     * 
     */
    public double getCoordinateY() {
        return coordinateY;
    }

    /**
     * Sets the value of the coordinateY property.
     * 
     */
    public void setCoordinateY(double value) {
        this.coordinateY = value;
    }

    /**
     * Gets the value of the coordinateZ property.
     * 
     */
    public double getCoordinateZ() {
        return coordinateZ;
    }

    /**
     * Sets the value of the coordinateZ property.
     * 
     */
    public void setCoordinateZ(double value) {
        this.coordinateZ = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.coordinateX, coordinateX) == 0 &&
                Double.compare(position.coordinateY, coordinateY) == 0 &&
                Double.compare(position.coordinateZ, coordinateZ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY, coordinateZ);
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", coordinateZ=" + coordinateZ +
                '}';
    }
}
