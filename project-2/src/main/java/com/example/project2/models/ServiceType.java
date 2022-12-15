package com.example.project2.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "type")
public class ServiceType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.EAGER)
    private List<ServicePrice> servicePrices;

    public ServiceType() {}

    public ServiceType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ServicePrice> getServicePrices() {
        return servicePrices;
    }

    public void setServicePrices(List<ServicePrice> servicePrices) {
        this.servicePrices = servicePrices;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

