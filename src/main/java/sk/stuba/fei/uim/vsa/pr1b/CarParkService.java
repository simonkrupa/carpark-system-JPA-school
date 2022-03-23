package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.Car;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarParkFloor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CarParkService extends  AbstractCarParkService{

    public void persist(Object... entities) {
        EntityManager manager = emf.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        for (Object entity : entities) {
            manager.persist(entity);
        }
        transaction.commit();
    }


    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        CarPark carPark = new CarPark();
        carPark.setName(name);
        carPark.setAddress(address);
        carPark.setPricePerHour(pricePerHour);
        persist(carPark);
        return carPark;
    }

    @Override
    public Object getCarPark(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        return carPark;
    }

    @Override
    public Object getCarPark(String carParkName) {
        try {
            EntityManager manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findByName");
            query.setParameter("name", carParkName);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getCarParks() {
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createNamedQuery("findAll");
        return query.getResultList();
    }

    @Override
    public Object updateCarPark(Object carPark) {
//        EntityManager manager = emf.createEntityManager();
//        CarPark carPark1 = manager.find(CarPark.class, ((CarPark) carPark).getCarParkId());
//        if (carPark1 != null){
//            if (((CarPark) carPark).getAddress() != null){
//                carPark1.setAddress(((CarPark) carPark).getAddress());
//            }
//            if (((CarPark) carPark).getName() != null){
//                carPark1.setName(((CarPark) carPark).getName());
//            }
//            if (((CarPark) carPark).getPricePerHour()!= null){
//                carPark1.setPricePerHour(((CarPark) carPark).getPricePerHour());
//            }
//            manager.getTransaction().begin();
//            manager.persist(carPark1);
//            manager.getTransaction().commit();
//        }
//        return carPark1;
        return null;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        if (carPark != null) {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(carPark);
            transaction.commit();
            return carPark;
        }else {
            return null;
        }
    }

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        if (carPark != null){
            CarParkFloor carParkFloor = new CarParkFloor();
            carParkFloor.setFloorIdentifier(floorIdentifier);
            carPark.addFloor(carParkFloor);
            manager.getTransaction().begin();
            manager.persist(carParkFloor);
            manager.getTransaction().commit();
            //persist(carParkFloor);
            //persist(carParkFloor,carPark);
            return carParkFloor;
        }
        return null;
    }

    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        return null;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        return Collections.singletonList(carPark.getFloors());
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        return null;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        return null;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        return null;
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        return null;
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        return null;
    }

    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        return null;
    }

    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        return null;
    }

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        return null;
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        return null;
    }

    @Override
    public Object getCar(Long carId) {
        return null;
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        return null;
    }

    @Override
    public List<Object> getCars(Long userId) {
        return null;
    }

    @Override
    public Object updateCar(Object car) {
        return null;
    }

    @Override
    public Object deleteCar(Long carId) {
        return null;
    }

    @Override
    public Object createUser(String firstname, String lastname, String email) {
        return null;
    }

    @Override
    public Object getUser(Long userId) {
        return null;
    }

    @Override
    public Object getUser(String email) {
        return null;
    }

    @Override
    public List<Object> getUsers() {
        return null;
    }

    @Override
    public Object updateUser(Object user) {
        return null;
    }

    @Override
    public Object deleteUser(Long userId) {
        return null;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        return null;
    }

    @Override
    public Object endReservation(Long reservationId) {
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        return null;
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        return null;
    }

    @Override
    public Object updateReservation(Object reservation) {
        return null;
    }

    @Override
    public Object createCarType(String name) {
        return null;
    }

    @Override
    public List<Object> getCarTypes() {
        return null;
    }

    @Override
    public Object getCarType(Long carTypeId) {
        return null;
    }

    @Override
    public Object getCarType(String name) {
        return null;
    }

    @Override
    public Object deleteCarType(Long carTypeId) {
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
        return null;
    }
}
