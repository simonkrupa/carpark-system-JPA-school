package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Car implements Serializable {
    @Id
    private Long carId;
    private String brand;
    private String model;
    private String colour;
    private Long vehicleRegistrationPlate;


}
