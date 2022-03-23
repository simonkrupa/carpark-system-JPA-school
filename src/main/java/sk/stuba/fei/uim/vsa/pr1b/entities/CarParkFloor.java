package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CarParkFloor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carParkFloorId;

    private String floorIdentifier;

    public CarParkFloor(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public CarParkFloor() {
    }

    public Long getCarParkFloorId() {
        return carParkFloorId;
    }

    public void setCarParkFloorId(Long carParkFloorId) {
        this.carParkFloorId = carParkFloorId;
    }


    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }
}
