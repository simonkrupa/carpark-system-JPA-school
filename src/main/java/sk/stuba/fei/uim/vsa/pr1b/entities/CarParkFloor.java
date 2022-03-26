package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CarParkFloor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carParkFloorId;

    private String floorIdentifier;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots;

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot){
        if(parkingSpots==null)
            parkingSpots = new ArrayList<>();
        parkingSpots.add(parkingSpot);
    }

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
