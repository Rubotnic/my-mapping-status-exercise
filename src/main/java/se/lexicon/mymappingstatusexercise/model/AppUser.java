package se.lexicon.mymappingstatusexercise.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "appusers")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(length = 200,nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Car.class)
    private Collection<Car> ownedCars = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, mappedBy = "cars")
    private Collection<Status> statuses = new ArrayList<>();


    protected AppUser() {}

    public AppUser(int userId, String email, String name, String password, Address address, Collection<Car> ownedCars, Collection<Status> statuses) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.ownedCars = ownedCars;
        this.statuses = statuses;
    }

    public AppUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public AppUser(String email, String name, String password, Address address, Collection<Car> ownedCars, Collection<Status> statuses) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.statuses = statuses;
        this.ownedCars = ownedCars;
    }



    public void addCar(Car ownedcars){
        if(ownedCars == null) throw new IllegalArgumentException("Invalid parameter: Car null");
        if(ownedCars == null) ownedCars = new HashSet<>();
        ownedCars.add(ownedcars);
        ownedcars.setOwner(this);
    }

    public void removeCar(Car ownedcars){
        if(ownedCars == null) throw new IllegalArgumentException("Invalid parameter: Car null");
        if(ownedCars != null){
            if (ownedCars.contains(null)){
                ownedcars.setOwner(null);
                ownedCars.remove(ownedcars);
            }
        }
    }

    public void addStatus(Status status){
        if(status == null) throw new IllegalArgumentException("Invalid parameter: Status null");
        if(statuses == null) statuses = new ArrayList<>();

        status.getStatusCode().equals(this);
        statuses.add(status);
    }

    public void removeStatus(Status status){
        if(status == null) throw new IllegalArgumentException("Invalid parameter: Status null");

        status.getStatusCode().equals(this);
        statuses.add(status);
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Collection<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    public Collection<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Collection<Status> statuses) {
        this.statuses = statuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return userId == appUser.userId && Objects.equals(email, appUser.email) && Objects.equals(name, appUser.name) && Objects.equals(password, appUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, name, password);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", ownedCars=" + ownedCars +
                ", statuses=" + statuses +
                '}';
    }
}