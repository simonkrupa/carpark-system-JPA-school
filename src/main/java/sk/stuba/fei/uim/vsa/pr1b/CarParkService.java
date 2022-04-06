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
        manager.close();
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
        manager.close();
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
            if (((CarPark) carPark).getCarParkId() == null) {
                return null;
            }
            EntityManager manager = emf.createEntityManager();
            CarPark carPark1 = manager.find(CarPark.class, ((CarPark) carPark).getCarParkId());
            if (carPark1 != null) {
                if (((CarPark) carPark).getName() != null) {
                    carPark1.setName(((CarPark) carPark).getName());
                } else {
                    manager.close();
                    return null;
                }
                carPark1.setAddress(((CarPark) carPark).getAddress());
                carPark1.setPricePerHour(((CarPark) carPark).getPricePerHour());
                carPark1.setFloors(((CarPark) carPark).getFloors());
                try {
                    manager.getTransaction().begin();
                    manager.merge(carPark1);
                    manager.getTransaction().commit();
                    manager.close();
                    return carPark1;
                }catch (Exception e){
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        if (carPark != null) {
            carPark.getFloors().forEach(floor -> floor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getReservations().forEach(reservation -> endReservation(reservation.getReservationId()))));
            carPark.getFloors().forEach(floor -> floor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getReservations().forEach(reservation -> reservation.setParkingSpot(null))));
            carPark.getFloors().forEach(floor -> floor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getCarType().getParkingSpots().remove(parkingSpot)));
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(carPark);
            transaction.commit();
            manager.close();
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
                    manager.close();
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
            manager.close();
            return carParkFloor;
        }
        return null;
    }

    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        EntityManager manager = emf.createEntityManager();
        CarParkFloor carParkFloor = manager.find(CarParkFloor.class, carParkFloorId);
        manager.close();
        return carParkFloor;
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        manager.close();
        return new ArrayList<>(carPark.getFloors());
    }

    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        if (carParkFloor instanceof CarParkFloor) {
            EntityManager manager = emf.createEntityManager();
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
            carParkFloor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getReservations().forEach(reservation -> endReservation(reservation.getReservationId())));
            carParkFloor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getReservations().forEach(reservation -> reservation.setParkingSpot(null)));
            carParkFloor.getParkingSpots().forEach(parkingSpot -> parkingSpot.getCarType().getParkingSpots().remove(parkingSpot));
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(carParkFloor);
            transaction.commit();
            manager.close();
            return carParkFloor;
        }else {
            manager.close();
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
                    manager.close();
                    return null;
                }
                for (CarParkFloor cpf : carPark.getFloors()) {
                    for (ParkingSpot ps : cpf.getParkingSpots().stream().collect(Collectors.toList())) {
                        if (ps.getSpotIdentifier().equals(spotIdentifier)) {
                            manager.close();
                            return null;
                        }
                    }
                }
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotIdentifier(spotIdentifier);
                parkingSpot.setFloor(carParkFloor);
                Object carType = createDefaultCarType();
                parkingSpot.setCarType((CarType) carType);
                ((CarType) carType).addParkingSpot(parkingSpot);
                carParkFloor.addParkingSpot(parkingSpot);
                manager.getTransaction().begin();
                manager.persist(parkingSpot);
                manager.getTransaction().commit();
                manager.getTransaction().begin();
                manager.merge(carType);
                manager.getTransaction().commit();
                manager.close();
                return parkingSpot;
            }
        }
        manager.close();
        return null;
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        manager.close();
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
                manager.close();
                return parkingSpots.stream().collect(Collectors.toList());
            }
        }
        manager.close();
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
        manager.close();
        return map;
    }

    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        Object carPark = getCarPark(carParkName);
        if(carPark instanceof CarPark){
            Map<String, List<Object>> map = new HashMap<>();
            List<CarParkFloor> carParkFloors = ((CarPark) carPark).getFloors();
            for (CarParkFloor carParkFloor: carParkFloors){
                List<ParkingSpot> parkingSpots = carParkFloor.getParkingSpots();
                List<ParkingSpot> parkingSpotsEntry = new ArrayList<>();
                for(ParkingSpot parkingSpot: parkingSpots){
                    if(parkingSpot.isAvailable()){
                        parkingSpotsEntry.add(parkingSpot);
                    }
                }
                map.put(carParkFloor.getFloorIdentifier(), Arrays.asList(parkingSpotsEntry.toArray()));
            }
            return map;
        }
        return null;
    }

    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        Object carPark = getCarPark(carParkName);
        if(carPark instanceof CarPark){
            Map<String, List<Object>> map = new HashMap<>();
            List<CarParkFloor> carParkFloors = ((CarPark) carPark).getFloors();
            for (CarParkFloor carParkFloor: carParkFloors){
                List<ParkingSpot> parkingSpots = carParkFloor.getParkingSpots();
                List<ParkingSpot> parkingSpotsEntry = new ArrayList<>();
                for(ParkingSpot parkingSpot: parkingSpots){
                    if(parkingSpot.isOccupied()){
                        parkingSpotsEntry.add(parkingSpot);
                    }
                }
                map.put(carParkFloor.getFloorIdentifier(), Arrays.asList(parkingSpotsEntry.toArray()));
            }
            return map;
        }
        return null;
    }

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        if(parkingSpot instanceof ParkingSpot){
            if(((ParkingSpot) parkingSpot).getParkingSpotId() != null){
                EntityManager manager = emf.createEntityManager();
                ParkingSpot parkingSpot1 = manager.find(ParkingSpot.class, ((ParkingSpot) parkingSpot).getParkingSpotId());
                if (parkingSpot1 != null) {
                    if (((ParkingSpot) parkingSpot).getSpotIdentifier() != null) {
                        if (!((ParkingSpot) parkingSpot).getSpotIdentifier().equals(parkingSpot1.getSpotIdentifier())) {
                            for (CarParkFloor cpf : parkingSpot1.getFloor().getCarPark().getFloors()) {
                                for (ParkingSpot ps : cpf.getParkingSpots()) {
                                    if (ps.getSpotIdentifier().equals(((ParkingSpot) parkingSpot).getSpotIdentifier())) {
                                        return null;
                                    }
                                }
                            }
                            parkingSpot1.setCarType(((ParkingSpot) parkingSpot).getCarType());
                            parkingSpot1.setSpotIdentifier(((ParkingSpot) parkingSpot).getSpotIdentifier());
                            manager.getTransaction().begin();
                            manager.merge(parkingSpot1);
                            manager.getTransaction().commit();
                            manager.close();
                            return parkingSpot1;
                        }

                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        if(parkingSpot!=null){
            CarParkFloor carParkFloor = parkingSpot.getFloor();
            carParkFloor.getParkingSpots().remove(parkingSpot);
            parkingSpot.getReservations().forEach(reservation -> endReservation(reservation.getReservationId()));
            parkingSpot.getReservations().forEach(reservation -> reservation.setParkingSpot(null));
            parkingSpot.getCarType().getParkingSpots().remove(parkingSpot);
            manager.getTransaction().begin();
            manager.remove(parkingSpot);
            manager.getTransaction().commit();
            manager.close();
            return parkingSpot;
        }
        manager.close();
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
                    Object carType = getCarType("Benzin");
                    if(carType == null){
                        carType = createDefaultCarType();
                    }
                    car.setCarType((CarType) carType);
                    ((CarType) carType).addCar(car);
                    user.addCar(car);
                    car.setUser(user);
                    manager.getTransaction().begin();
                    manager.persist(car);
                    manager.getTransaction().commit();
                    manager.getTransaction().begin();
                    manager.merge(carType);
                    manager.getTransaction().commit();
                    manager.close();
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
        manager.close();
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
        manager.close();
        if (user!= null){
            return user.getCars().stream().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Object updateCar(Object car) {
        if(car instanceof Car){
            if(((Car) car).getCarId() != null){
                EntityManager manager = emf.createEntityManager();
                Car car1 = manager.find(Car.class, ((Car) car).getCarId());
                if(car1!=null){
                    car1.setBrand(((Car) car).getBrand());
                    car1.setColour(((Car) car).getColour());
                    car1.setModel(((Car) car).getModel());
                    car1.setVehicleRegistrationPlate(((Car) car).getVehicleRegistrationPlate());
                    car1.setCarType(((Car) car).getCarType());
                    try{
                        manager.getTransaction().begin();
                        manager.merge(car1);
                        manager.getTransaction().commit();
                        manager.close();
                        return car1;
                    }catch (Exception e){
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object deleteCar(Long carId) {
        EntityManager manager = emf.createEntityManager();
        Car car = manager.find(Car.class, carId);
        if (car != null){
            car.getUser().getCars().remove(car);
            car.getReservations().forEach(reservation -> endReservation(reservation.getReservationId()));
            car.getReservations().forEach(reservation -> reservation.setCar(null));
            car.getCarType().getCars().remove(car);
            manager.getTransaction().begin();
            manager.remove(car);
            manager.getTransaction().commit();
            manager.close();
            return car;
        }
        manager.close();
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
                manager.close();
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
        manager.close();
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
                user.getCars().forEach(car -> car.getReservations().forEach(reservation -> endReservation(reservation.getReservationId())));
                user.getCars().forEach(car -> car.getReservations().forEach(reservation -> reservation.setCar(null)));
                user.getCars().forEach(car -> car.getCarType().getCars().remove(car));
                manager.getTransaction().begin();
                manager.remove(user);
                manager.getTransaction().commit();
                manager.close();
                return user;
            }catch (Exception e){
                return null;
            }
        }
        manager.close();
        return null;
    }

    @Override
    public Object createReservation(Long parkingSpotId, Long carId) {
        EntityManager manager = emf.createEntityManager();
        Car car = manager.find(Car.class, carId);
        if(car != null){
            if(car.getActiveReservation() == null) {
                ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
                if (parkingSpot != null) {
                    if (parkingSpot.getCarType().equals(car.getCarType())) {
                        if (parkingSpot.getActiveReservation() == null) {
                            Reservation reservation = new Reservation();
                            reservation.setCar(car);
                            reservation.setParkingSpot(parkingSpot);
                            reservation.setStartDate(new Date());
                            parkingSpot.addReservation(reservation);
                            car.addReservation(reservation);
                            manager.getTransaction().begin();
                            manager.persist(reservation);
                            manager.getTransaction().commit();
                            manager.close();
                            return reservation;
                        }
                    }
                }
            }
        }
        manager.close();
        return null;
    }

    @Override
    public Object endReservation(Long reservationId) {
        EntityManager manager = emf.createEntityManager();
        Reservation reservation = manager.find(Reservation.class, reservationId);
        if (reservation != null){
            if(reservation.getEndDate() == null) {
                Date endDate = new Date();
                reservation.setEndDate(endDate);
                Long secs = (endDate.getTime() - reservation.getStartDate().getTime()) / 1000;
                Long hours = secs / 3600;
                if (secs > 0) {
                    hours++;
                }
                Integer hours2 = hours.intValue();
                Integer price = reservation.getParkingSpot().getFloor().getCarPark().getPricePerHour();
                Integer cost = price * hours2;
                reservation.setCost(cost);
                manager.getTransaction().begin();
                manager.persist(reservation);
                manager.getTransaction().commit();
                manager.close();
                return reservation;
            }
        }
        manager.close();
        return null;
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        EntityManager manager = emf.createEntityManager();
        ParkingSpot parkingSpot = manager.find(ParkingSpot.class, parkingSpotId);
        manager.close();
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
        manager.close();
        if(user!=null){
            List<Reservation> myActiveReservations = new ArrayList<>();
            List<Car> cars = user.getCars();
            for(Car c: cars){
                Reservation myReservation = c.getActiveReservation();
                if(myReservation!=null) {
                    myActiveReservations.add(myReservation);
                }
            }
            return myActiveReservations.stream().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Object updateReservation(Object reservation) {
        return null;
    }

    @Override
    public Object createCarType(String name) {
        if (name!=null) {
            try {
                EntityManager manager = emf.createEntityManager();
                CarType carType = new CarType();
                carType.setName(name);
                manager.getTransaction().begin();
                manager.persist(carType);
                manager.getTransaction().commit();
                manager.close();
                return carType;
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Object> getCarTypes() {
        EntityManager manager = emf.createEntityManager();
        Query query = manager.createNamedQuery("findAllCarTypes");
        return query.getResultList();
    }

    @Override
    public Object getCarType(Long carTypeId) {
        if(carTypeId!=null){
            EntityManager manager = emf.createEntityManager();
            CarType carType = manager.find(CarType.class, carTypeId);
            manager.close();
            return carType;
        }
        return null;
    }

    @Override
    public Object getCarType(String name) {
        if(name!=null){
            try {
                EntityManager manager = emf.createEntityManager();
                Query query = manager.createNamedQuery("findCarTypeByName");
                query.setParameter("name", name);
                return query.getSingleResult();
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    @Override
    public Object deleteCarType(Long carTypeId) {
        EntityManager manager = emf.createEntityManager();
        CarType carType = manager.find(CarType.class, carTypeId);
        if(carType!=null){
            if(!carType.getParkingSpots().isEmpty()){
                return null;
            }
            if(!carType.getCars().isEmpty()){
                return null;
            }
            manager.getTransaction().begin();
            manager.remove(carType);
            manager.getTransaction().commit();
            manager.close();
            return carType;
        }
        manager.close();
        return null;
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        if(vehicleRegistrationPlate!=null) {
            try {
                Car car = new Car();
                car.setBrand(brand);
                car.setModel(model);
                car.setColour(colour);
                car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
                EntityManager manager = emf.createEntityManager();
                CarType carType = manager.find(CarType.class, carTypeId);
                if(carType != null) {
                    User user = manager.find(User.class, userId);
                    if (user != null) {
                        car.setCarType(carType);
                        carType.addCar(car);
                        user.addCar(car);
                        car.setUser(user);
                        manager.getTransaction().begin();
                        manager.persist(car);
                        manager.getTransaction().commit();
                        manager.close();
                        return car;
                    }
                }
            }catch(Exception e){
                return null;
            }
        }
        return null;
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId) {
        EntityManager manager = emf.createEntityManager();
        CarPark carPark = manager.find(CarPark.class, carParkId);
        if(carPark != null){
            CarParkFloor carParkFloor = carPark.getByFloorIdentifier(floorIdentifier);
            if (carParkFloor != null){
                if(spotIdentifier == null){
                    manager.close();
                    return null;
                }
                for (ParkingSpot ps : carParkFloor.getParkingSpots().stream().collect(Collectors.toList())){
                    if (ps.getSpotIdentifier().equals(spotIdentifier)){
                        manager.close();
                        return null;
                    }
                }
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setSpotIdentifier(spotIdentifier);
                parkingSpot.setFloor(carParkFloor);
                CarType carType = manager.find(CarType.class, carTypeId);
                if(carType==null){
                    manager.close();
                    return null;
                }
                carParkFloor.addParkingSpot(parkingSpot);
                parkingSpot.setCarType(carType);
                carType.addParkingSpot(parkingSpot);
                manager.getTransaction().begin();
                manager.persist(parkingSpot);
                manager.getTransaction().commit();
                manager.getTransaction().begin();
                manager.merge(carType);
                manager.getTransaction().commit();
                manager.close();
                return parkingSpot;
            }
        }
        manager.close();
        return null;
    }

    public Object createDefaultCarType(){
        if(getCarType("Benzin")!=null){
            return getCarType("Benzin");
        }
        EntityManager manager = emf.createEntityManager();
        CarType carType = new CarType();
        carType.createDefaultCarType();
        manager.getTransaction().begin();
        manager.persist(carType);
        manager.getTransaction().commit();
        manager.close();
        return carType;
    }
}
