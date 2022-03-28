package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class ParkingSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkingSpotId;
    private String spotIdentifier;

    @OneToMany(mappedBy = "parkingSpot")
    private List<Reservation> reservations;

    @ManyToOne
    private CarParkFloor floor;

    public CarParkFloor getFloor() {
        return floor;
    }

    public void setFloor(CarParkFloor floor) {
        this.floor = floor;
    }

    public void addReservation(Reservation reservation){
        if (reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

    public Reservation getActiveReservation(){
        if(reservations!=null){
            if(reservations.size()>0) {
                if (reservations.get(reservations.size() - 1).getEndDate() == null) {
                    return reservations.get(reservations.size() - 1);
                }
            }
        }
        return null;
    }

    public boolean isAvailable(){
        if(reservations==null){
            return true;
        }
        if(reservations.size()==0){
            return true;
        }
        if(!reservations.isEmpty()){
            if(reservations.get(reservations.size()-1).getEndDate()!=null){
                return true;
            }
        }
        return false;
    }

    public boolean isOccupied(){
        if(!reservations.isEmpty()){
            if(reservations.get(reservations.size()-1).getEndDate()==null){
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ParkingSpot(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }

    public ParkingSpot() {
    }

    public Long getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(Long parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public String getSpotIdentifier() {
        return spotIdentifier;
    }

    public void setSpotIdentifier(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }
}
