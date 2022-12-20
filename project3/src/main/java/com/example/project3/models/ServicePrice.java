package com.example.project3.models;

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
    private CarService carService;

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

    public CarService getService() {
        return carService;
    }

    public void setService(CarService carService) {
        this.carService = carService;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String toString() {
        return "ServicePrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
