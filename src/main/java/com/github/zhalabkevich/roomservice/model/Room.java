package com.github.zhalabkevich.roomservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Long.class)
public class Room {

    private Long id;
    private String name;
    private Country country;
    private boolean isLampOn;

    public Room() {
    }

    public Room(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    //@JsonManagedReference
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isLampOn() {
        return isLampOn;
    }

    public void setLampOn(boolean lampOn) {
        isLampOn = lampOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isLampOn() == room.isLampOn() &&
                getName().equals(room.getName()) &&
                getCountry().equals(room.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), isLampOn());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", isLampOn=" + isLampOn +
                '}';
    }
}
