package com.example.project3.models;

import jakarta.persistence.*;

@Entity
@Table(name = "master_type")
public class CarServicePrice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="price")
    private Integer price;

    public CarServiceType getServiceType() {
        return carServiceType;
    }

    public void setServiceType(CarServiceType carServiceType) {
        this.carServiceType = carServiceType;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private CarServiceType carServiceType;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private CarService carService;

    public CarServicePrice() {}

    public CarServicePrice(Integer id, Integer price) {
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

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String toString() {
        return "CarServicePrice{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
