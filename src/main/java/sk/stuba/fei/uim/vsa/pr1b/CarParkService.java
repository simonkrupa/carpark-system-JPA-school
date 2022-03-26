package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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
        Query query = manager.createNamedQuery("findAllCarParks");
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
        EntityManager manager = emf.createEntityManager();
        CarParkFloor carParkFloor = manager.find(CarParkFloor.class, carParkFloorId);
        return carParkFloor;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        System.out.println(carPark.getFloors());
        return Collections.singletonList(carPark.getFloors());
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        return null;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        EntityManager manager = emf.createEntityManager();
        CarParkFloor carParkFloor = manager.find(CarParkFloor.class, carParkFloorId);
        CarPark carPark = manager.find(CarPark.class, 2L); //zleeee
        carPark.getFloors().remove(carParkFloor);
        if (carParkFloor != null) {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(carParkFloor);
            transaction.commit();
            return carParkFloor;
        }else {
            return null;
        }
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        if(carPark != null){
            CarParkFloor carParkFloor = carPark.getByFloorIdentifier(floorIdentifier);
            if (carParkFloor != null){
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotIdentifier(spotIdentifier);
                carParkFloor.addParkingSpot(parkingSpot);
                manager.getTransaction().begin();
                manager.persist(carParkFloor);
                manager.getTransaction().commit();
                return parkingSpot;
            }
        }
        return null;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        return parkingSpot;
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        CarParkFloor carParkFloor = carPark.getByFloorIdentifier(floorIdentifier);
        List<ParkingSpot> parkingSpots = carParkFloor.getParkingSpots();
        return Collections.singletonList(parkingSpots);
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        Map<String, List<Object>> map = new HashMap<>(Collections.emptyMap());
        if (carPark != null){
            for (CarParkFloor carParkFloor: carPark.getFloors()) {
                String entryString = carParkFloor.getFloorIdentifier();
                List<Object> entryList = new ArrayList<>();
                entryList.addAll(carParkFloor.getParkingSpots());
                map.put(entryString, entryList);
                System.out.println("smt");
            }
        }
        return map;
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
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        user.addCar(car);
        //if
        car.setUser(user);
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        return car;
    }

    @Override
    public Object getCar(Long carId) {
        EntityManager manager = emf.createEntityManager();
        Car car = manager.find(Car.class, carId);
        return car;
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        try {
            EntityManager manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findByPlate");
            query.setParameter("plate", vehicleRegistrationPlate);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getCars(Long userId) {
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        if (user!= null){
            return user.getCars().stream().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Object updateCar(Object car) {
        return null;
    }

    @Override
    public Object deleteCar(Long carId) {
        EntityManager manager = emf.createEntityManager();
        Car car = manager.find(Car.class, carId);
        if (car != null){
            car.getUser().getCars().remove(car);
            manager.getTransaction().begin();
            manager.remove(car);
            manager.getTransaction().commit();

            return car;
        }
        return null;
    }

    @Override
    public Object createUser(String firstname, String lastname, String email) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        persist(user);
        return user;
    }

    @Override
    public Object getUser(Long userId) {
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        return user;
    }

    @Override
    public Object getUser(String email) {
        try {
            EntityManager manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findByEmail");
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Object> getUsers() {
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createNamedQuery("findAllUsers");
        return query.getResultList();
    }

    @Override
    public Object updateUser(Object user) {
        return null;
    }

    @Override
    public Object deleteUser(Long userId) {
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        if (user != null){
            manager.getTransaction().begin();
            manager.remove(user);
            manager.getTransaction().commit();
            return user;//try catch
        }
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
