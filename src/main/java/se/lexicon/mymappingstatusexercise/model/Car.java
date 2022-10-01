package se.lexicon.mymappingstatusexercise.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column(nullable = false, length = 100)
    private String regNumber;
    @Column(nullable = false, length = 100)
    private String brand;
    @Column(nullable = false, length = 100)
    private String model;
    @CreationTimestamp
    @Column(name = "register_date", columnDefinition = "TIMESTAMP")
    private LocalDate regDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private AppUser owner;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinTable(name = "j_car_status",
            joinColumns = {
            @JoinColumn(name = "car_id", referencedColumnName = "carId")},
            inverseJoinColumns = {
            @JoinColumn(name = "status_id", referencedColumnName = "statusId")})
    private Collection<Status> statusCodes = new HashSet<>();

    public Car() {}


    public Car(int carId, String regNumber, String brand, String model, LocalDate regDate, AppUser owner, Collection<Status> statusCodes) {
        this.carId = carId;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
        this.owner = owner;
        this.statusCodes = statusCodes;
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate, AppUser owner, Collection<Status> statusCodes) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
        this.owner = owner;
        this.statusCodes = statusCodes;
    }

    public void addStatusCode(Status statcode){

        if (statcode == null) throw new IllegalArgumentException("Invalid parameter: Status was null");
        if (statusCodes == null) statusCodes = new ArrayList<>();

        statusCodes.add(statcode);
        statcode.getCars();
    }

    public void removeStatusCode(Status statcode){
        if (statcode == null) throw new IllegalArgumentException("Invalid parameter: Status was null");

        if (statusCodes != null){
            if (statusCodes.contains(statcode)){
                statcode.setCars(null);
                statusCodes.remove(statcode);
            }
        }
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Collection<Status> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Collection<Status> statusCodes) {
        this.statusCodes = statusCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && Objects.equals(regNumber, car.regNumber) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(regDate, car.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, regNumber, brand, model, regDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                ", statusCodes=" + statusCodes +
                '}';
    }
}