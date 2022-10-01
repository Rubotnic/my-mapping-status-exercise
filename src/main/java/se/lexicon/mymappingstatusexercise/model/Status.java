package se.lexicon.mymappingstatusexercise.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(length = 100)
    private String statusCode;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Collection<Car> cars = new ArrayList<>();


    public Status(int statusId, String statusCode, Collection<Car> cars) {
        this.statusId = statusId;
        this.statusCode = statusCode;
        this.cars = cars;
    }

    public Status(String statusCode) {
        this.statusCode = statusCode;
    }

    public Status(String statusCode, Collection<Car> cars) {
        this.statusCode = statusCode;
        this.cars = cars;
    }

    public Status() {}


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return statusId == status.statusId && Objects.equals(statusCode, status.statusCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, statusCode);
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode='" + statusCode + '\'' +
                ", cars=" + cars +
                '}';
    }
}