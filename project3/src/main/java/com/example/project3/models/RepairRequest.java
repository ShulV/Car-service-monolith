package com.example.project3.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "request")
public class RepairRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_name")
    String authorName;

    @Column(name = "phone")
    String phone;

    @Column(name = "date_request")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateRequest;

    @Column(name = "date_time_work")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateTimeWork;

    @Transient
    private String dateTimeWorkFromInput;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private CarServiceType carServiceType;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private CarService carService;

    public RepairRequest() { }

    public RepairRequest(Integer id, String authorName, String phone, LocalDate dateRequest, Timestamp dateTimeWork) {
        this.id = id;
        this.authorName = authorName;
        this.phone = phone;
        this.dateRequest = dateRequest;
        this.dateTimeWork = dateTimeWork;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Timestamp getDateTimeWork() {
        return dateTimeWork;
    }

    public void setDateTimeWork(Timestamp dateTimeWork) {
        this.dateTimeWork = dateTimeWork;
    }

    public CarServiceType getServiceType() {
        return carServiceType;
    }

    public void setServiceType(CarServiceType carServiceType) {
        this.carServiceType = carServiceType;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDateTimeWorkFromInput() {
        return dateTimeWorkFromInput;
    }

    public void setDateTimeWorkFromInput(String dateTimeWorkFromInput) {
        this.dateTimeWorkFromInput = dateTimeWorkFromInput;
    }

    @Override
    public String toString() {
        return "RepairRequest{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", phone='" + phone + '\'' +
                ", dateRequest=" + dateRequest +
                ", dateTimeWork=" + dateTimeWork +
                '}';
    }
}
