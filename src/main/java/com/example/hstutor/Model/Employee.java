package com.example.hstutor.Model;

import jakarta.persistence.*;

@Entity
@Table (name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullname;

    @Column
    private String passport_data;

    @Column
    private String contact_data;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public void setPassport_data(String passport_data) {
        this.passport_data = passport_data;
    }

    public String getContact_data() {
        return contact_data;
    }

    public void setContact_data(String contact_data) {
        this.contact_data = contact_data;
    }
}
