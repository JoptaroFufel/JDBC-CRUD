package by.herhenson.program.tables;

import java.util.Date;

public class Shipment implements Table {
    private String name;
    private String contract_id;
    private String departure_point;
    private String destination_point;
    private Date shipping_date;
    private Date arrival_date;
    private String status;

    public Shipment() {
    }

    public Shipment(String name, String contract_id, String departure_point, String destination_point, Date shipping_date, Date arrival_date, String status) {
        this.name = name;
        this.contract_id = contract_id;
        this.departure_point = departure_point;
        this.destination_point = destination_point;
        this.shipping_date = shipping_date;
        this.arrival_date = arrival_date;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getDeparture_point() {
        return departure_point;
    }

    public void setDeparture_point(String departure_point) {
        this.departure_point = departure_point;
    }

    public String getDestination_point() {
        return destination_point;
    }

    public void setDestination_point(String destination_point) {
        this.destination_point = destination_point;
    }

    public Date getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(Date shipping_date) {
        this.shipping_date = shipping_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SHIPMENT: " + name +
                "\n    DEPARTURE POINT:   " + departure_point +
                "\n    DESTINATION POINT: " + destination_point +
                "\n    SHIPPING DATE:     " + shipping_date +
                "\n    ARRIVAL DATE:      " + arrival_date +
                "\n    STATUS:            " + status;
    }
}
