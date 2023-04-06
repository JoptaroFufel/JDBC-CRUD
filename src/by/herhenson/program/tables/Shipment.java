package by.herhenson.program.tables;

import java.util.Date;

public class Shipment {
    private String number;
    private String contract_id;
    private int departure_point;
    private int destination_point;
    private Date shipping_date;
    private Date arrival_date;
    private String status;

    public Shipment() {
    }

    public Shipment(String number, String contract_id, int departure_point, int destination_point, Date shipping_date, Date arrival_date, String status) {
        this.number = number;
        this.contract_id = contract_id;
        this.departure_point = departure_point;
        this.destination_point = destination_point;
        this.shipping_date = shipping_date;
        this.arrival_date = arrival_date;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public int getDeparture_point() {
        return departure_point;
    }

    public void setDeparture_point(int departure_point) {
        this.departure_point = departure_point;
    }

    public int getDestination_point() {
        return destination_point;
    }

    public void setDestination_point(int destination_point) {
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
        return "Shipment{" +
                "number='" + number + '\'' +
                ", contract_id='" + contract_id + '\'' +
                ", departure_point=" + departure_point +
                ", destination_point=" + destination_point +
                ", shipping_date=" + shipping_date +
                ", arrival_date=" + arrival_date +
                ", status='" + status + '\'' +
                '}';
    }
}
