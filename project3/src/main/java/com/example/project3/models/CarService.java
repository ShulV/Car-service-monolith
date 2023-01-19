package com.example.project3.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "master")
public class CarService {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @Transient
    private Double averageRating;

    @OneToMany(mappedBy = "carService")
    private List<CarServicePrice> carServicePrices;

    @OneToMany(mappedBy = "carService", cascade = CascadeType.PERSIST)
    private List<Review> reviews;

    @OneToMany(mappedBy = "carService", cascade = CascadeType.PERSIST)
    private List<RepairRequest> repairRequests;

    public CarService(Integer id, String name, String email, String password, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public CarService() { }

    public void calcAverageRating() {
        int ratingSum = reviews.stream().filter(o -> o.getMark() > 0).mapToInt(Review::getMark).sum();
        double res = (double)ratingSum / reviews.size();
        this.averageRating = Math.ceil(res * 10) / 10; //Округление до десятых

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CarServicePrice> getCarServicePrices() {
        return carServicePrices;
    }

    public void setCarServicePrices(List<CarServicePrice> carServicePrices) {
        this.carServicePrices = carServicePrices;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        if(this.reviews == null) {
            this.reviews = new ArrayList<>();
        }
        this.reviews.add(review);
    }

    public List<RepairRequest> getRepairRequests() {
        return repairRequests;
    }

    public void setRepairRequests(List<RepairRequest> repairRequests) {
        this.repairRequests = repairRequests;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "CarService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
