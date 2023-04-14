package com.example.hstutor.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class SVSPK implements Serializable {
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false, name = "shipper_id")
    private Shipper shipper;

    public SVSPK() {
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }
}
