package main.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.InvalidPropertiesFormatException;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client {

    // name validation between 2 and 200

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Client(){

    }

    public Client(Long id){
        this.id = id;
    }

    public Client(String name) throws InvalidPropertiesFormatException {
        if(name.length() <= 500 & name.length() >= 2){
            this.name = name;
        }else {
            throw new InvalidPropertiesFormatException("Invalid name length! Name should be between 2 and 500");
        }
    }

    public Client(String name, Long id) throws InvalidPropertiesFormatException {
        if(name.length() <= 500 & name.length() >= 2){
            this.name = name;
            this.id = id;
        }else {
            throw new InvalidPropertiesFormatException("Invalid name length! Name should be between 2 and 500");
        }
    }

    public void setName(String name) throws InvalidPropertiesFormatException {
        if(name.length() <= 500 & name.length() >= 2){
            this.name = name;
        }else {
            throw new InvalidPropertiesFormatException("Invalid name length! Name should be between 2 and 500");
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
