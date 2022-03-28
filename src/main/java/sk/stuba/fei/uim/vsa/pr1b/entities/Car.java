package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "findByPlate", query = "SELECT c from Car c WHERE c.vehicleRegistrationPlate=:plate")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    private String brand;
    private String model;
    private String colour;
    @Column(nullable = false, unique = true)
    private String vehicleRegistrationPlate;

    @OneToOne
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @ManyToOne
    private User user;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car(String brand, String model, String colour, String vehicleRegistrationPlate) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public Car() {
    }
}
