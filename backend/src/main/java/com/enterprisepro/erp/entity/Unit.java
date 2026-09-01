package com.enterprisepro.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name; // Pieces, Kilograms, Liters, Boxes, Meters

    @Column(nullable = false, unique = true, length = 20)
    private String shortCode; // PCS, KG, LTR, BOX, MTR

    private boolean active = true;

    public Unit() {}

    public Unit(String name, String shortCode, boolean active) {
        this.name = name;
        this.shortCode = shortCode;
        this.active = active;
    }

    public Unit(String name, String shortCode) {
        this.name = name;
        this.shortCode = shortCode;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
