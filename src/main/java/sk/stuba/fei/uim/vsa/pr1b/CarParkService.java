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
        if (name == null){
            return null;
        }
        if (pricePerHour == null){
            return null;
        }
        List<Object> carParks = getCarParks();
        for (Object cp: carParks) {
           if(cp instanceof CarPark){
               if(name.equals(((CarPark) cp).getName())){
                   System.out.println("uz existuje");
                   return null;
               }
           }
        }
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
        if(carPark instanceof CarPark) {
            EntityManager manager = emf.createEntityManager();
            if (((CarPark) carPark).getCarParkId() == null) {
                return null;
            }
            CarPark carPark1 = manager.find(CarPark.class, ((CarPark) carPark).getCarParkId());
            if (carPark1 != null) {
                if (((CarPark) carPark).getName() != null) {
                    List<Object> carParks = getCarParks();
                    for (Object cp : carParks) {
                        if (cp instanceof CarPark) {
                            if (((CarPark) carPark).getName().equals(((CarPark) cp).getName()) && ((CarPark) carPark).getCarParkId() != carPark1.getCarParkId()) {
                                System.out.println("uz existuje");
                                return null;
                            }
                        }
                    }
                    carPark1.setName(((CarPark) carPark).getName());
                } else {
                    return null;
                }
                carPark1.setAddress(((CarPark) carPark).getAddress());
                carPark1.setPricePerHour(((CarPark) carPark).getPricePerHour());

                manager.getTransaction().begin();
                manager.merge(carPark1);
                manager.getTransaction().commit();
                return carPark1;

            }
        }
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
            List<CarParkFloor> floors = carPark.getFloors();
            for (CarParkFloor floor : floors) {
                if(floor.getFloorIdentifier().equals(floorIdentifier)){
                    return null;
                }
            }
            CarParkFloor carParkFloor = new CarParkFloor();
            carParkFloor.setFloorIdentifier(floorIdentifier);
            carParkFloor.setCarPark(carPark);
            carPark.addFloor(carParkFloor);
            manager.getTransaction().begin();
            manager.persist(carParkFloor);
            manager.getTransaction().commit();
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
        return carPark.getFloors().stream().collect(Collectors.toList());
        //return Collections.singletonList(carPark.getFloors());
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        EntityManager manager = emf.createEntityManager();
        if (carParkFloor instanceof CarParkFloor) {
            CarParkFloor cp = manager.find(CarParkFloor.class, ((CarParkFloor) carParkFloor).getCarParkFloorId());
            if(cp != null){
                if(((CarParkFloor) carParkFloor).getFloorIdentifier().equals(cp.getFloorIdentifier())){
                    return cp;
                }
                CarPark carPark = cp.getCarPark();
                for (CarParkFloor cpf: carPark.getFloors()) {
                    if(((CarParkFloor) carParkFloor).getFloorIdentifier().equals(cpf.getFloorIdentifier())){
                        return null;
                    }
                }
                cp.setFloorIdentifier(((CarParkFloor) carParkFloor).getFloorIdentifier());
                manager.getTransaction().begin();
                manager.merge(cp);
                manager.getTransaction().commit();
                return cp;
            }
        }
        return null;
    }

    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        EntityManager manager = emf.createEntityManager();
        CarParkFloor carParkFloor = manager.find(CarParkFloor.class, carParkFloorId);
        if (carParkFloor != null) {
            CarPark carPark = carParkFloor.getCarPark();
            carPark.getFloors().remove(carParkFloor);
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
                if(spotIdentifier == null){
                    return null;
                }
                for (ParkingSpot ps : carParkFloor.getParkingSpots().stream().collect(Collectors.toList())){
                    if (ps.getSpotIdentifier().equals(spotIdentifier)){
                        return null;
                    }
                }
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotIdentifier(spotIdentifier);
                parkingSpot.setFloor(carParkFloor);
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
        if(carPark!=null) {
            CarParkFloor carParkFloor = carPark.getByFloorIdentifier(floorIdentifier);
            if(carParkFloor!= null) {
                List<ParkingSpot> parkingSpots = carParkFloor.getParkingSpots();
                //return Collections.singletonList(parkingSpots);
                return parkingSpots.stream().collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
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
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        if(parkingSpot!=null){
            CarParkFloor carParkFloor = parkingSpot.getFloor();
            carParkFloor.getParkingSpots().remove(parkingSpot);
            manager.getTransaction().begin();
            manager.remove(parkingSpot);
            manager.getTransaction().commit();
            return parkingSpot;
        }
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        if(vehicleRegistrationPlate!=null) {
            try {
                Car car = new Car();
                car.setBrand(brand);
                car.setModel(model);
                car.setColour(colour);
                car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
                EntityManager manager = emf.createEntityManager();
                User user = manager.find(User.class, userId);
                if(user!=null) {
                    user.addCar(car);
                    car.setUser(user);
                    manager.getTransaction().begin();
                    manager.persist(car);
                    manager.getTransaction().commit();
                    return car;
                }
            }catch(Exception e){
                return null;
            }
        }
        return null;
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
        if (email != null) {
            try {
                EntityManager manager = emf.createEntityManager();
                User user = new User();
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setEmail(email);
                manager.getTransaction().begin();
                manager.persist(user);
                manager.getTransaction().commit();
                return user;
            }catch (Exception e){
                return null;
            }

        }
        return null;
    }

    @Override
    public Object getUser(Long userId) {
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        return user;
    }

    @Override
    public Object getUser(String email) {
        if(email!=null) {
            try {
                EntityManager manager = emf.createEntityManager();
                Query query = manager.createNamedQuery("findByEmail");
                query.setParameter("email", email);
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
        return null;
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
            try {
                manager.getTransaction().begin();
                manager.remove(user);
                manager.getTransaction().commit();
                return user;
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long carId) {
        EntityManager manager = emf.createEntityManager();
        Car car = manager.find(Car.class, carId);
        if(car != null){
            ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
            if (parkingSpot != null){
                Reservation reservation = new Reservation();
                reservation.setCar(car);
                reservation.setParkingSpot(parkingSpot);
                reservation.setStartDate(new Date());
                parkingSpot.addReservation(reservation);
                manager.getTransaction().begin();
                manager.persist(reservation);
                manager.getTransaction().commit();
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Object endReservation(Long reservationId) {
        EntityManager manager = emf.createEntityManager();
        Reservation reservation = manager.find(Reservation.class, reservationId);
        if (reservation != null){
            Date endDate = new Date();
            reservation.setEndDate(endDate);
            long secs = (endDate.getTime() - reservation.getStartDate().getTime()) / 1000;
            long hours = secs / 3600;
            if(secs>0) {
                hours++;
            }
            System.out.println(hours);
            reservation.getParkingSpot();
            for (Object carPark: getCarParks()) {
                if(carPark instanceof CarPark) {
                    for (Object carParkFloor : ((CarPark) carPark).getFloors()) {
                        if(carParkFloor instanceof CarParkFloor){
                            boolean b = ((CarParkFloor) carParkFloor).getParkingSpots().stream().filter(o -> o.getParkingSpotId().equals(reservation.getParkingSpot().getParkingSpotId())).findFirst().isPresent();
                            if (b) {
                               reservation.setCost(((CarPark) carPark).getPricePerHour());
                               break;
                            }
                        }
                    }
                }

            }
            manager.getTransaction().begin();
            manager.persist(reservation);
            manager.getTransaction().commit();
            return reservation;
        }
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        if(parkingSpot!= null){
            List<Reservation> reservations = new ArrayList<>();
            reservations.addAll(parkingSpot.getReservations());
            List<Object> result = new ArrayList<>();
            for (Reservation res:reservations) {
                if(date.getDay() == res.getStartDate().getDay()){
                    result.add(res);
                }
            }
            return result;
        }
        return null;
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        EntityManager manager = emf.createEntityManager();
        User user = manager.find(User.class, userId);
        if(user!=null){
            if(user.getCars()!=null){
                List<Car> u = new ArrayList<>(user.getCars());
                List<Object> reservations = new ArrayList<>();
                for (Car c: u) {
                    reservations.add(c.getReservation());
                }
                return reservations;
            }
        }
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
