package sk.stuba.fei.uim.vsa.pr1b.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "findAllCarTypes", query = "SELECT ct from CarType ct")
@NamedQuery(name = "findCarTypeByName", query = "select ct from CarType ct where ct.name =:name")
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carTypeId;
    @Column(unique = true)
    private String name;

    public CarType(String name) {
        this.name = name;
    }

    public CarType() {
    }

    public Long getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Long carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
