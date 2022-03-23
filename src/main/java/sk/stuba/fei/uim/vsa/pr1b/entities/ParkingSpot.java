package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class ParkingSpot implements Serializable {
    @Id
    private Long parkingSpotId;
    private Long carParkId;
    private String floorIdentifier;
    private String spotIdentifier;


}
