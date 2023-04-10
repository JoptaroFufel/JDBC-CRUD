package by.herhenson.program.tables;

import java.util.Date;

public class Contract implements Table {
    private String name;
    private String status;
    private double total;
    private Date date;
    private int hirer_id;
    private int manager_id;

    public Contract() {
    }

    public Contract(String name, String status, double total, Date date, int hirer_id, int manager_id) {
        this.name = name;
        this.status = status;
        this.total = total;
        this.date = date;
        this.hirer_id = hirer_id;
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHirer_id() {
        return hirer_id;
    }

    public void setHirer_id(int hirer_id) {
        this.hirer_id = hirer_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public String toString() {
        return "CONTRACT:" + name +
                "\n   STATUS: " + status +
                "\n   TOTAL:  " + total +
                "\n   DATE:   " + date;
    }
}
