package com.example.project2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "master_type")
public class ServicePrice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="price")
    private Integer price;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Service service;

    public ServicePrice() {}

    public ServicePrice(Integer id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServicePrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
