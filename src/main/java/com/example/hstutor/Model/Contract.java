package com.example.hstutor.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String status;

    @Column
    private Double total;

    @Temporal(TemporalType.DATE)
    @Column
    private Date validity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "hirer_id")
    private Hirer hirer;

    @ManyToOne
    @JoinColumn(nullable = false, name = "manager_id")
    private Manager manager;

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Hirer getHirer() {
        return hirer;
    }

    public void setHirer(Hirer hirer) {
        this.hirer = hirer;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
