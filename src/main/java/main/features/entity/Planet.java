package main.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.InvalidPropertiesFormatException;

@Entity
@Table(name = "planet")
@Getter
@Setter
public class Planet {
    // Planet id in range between  A-Z and 0-9
    // Planet name in range between 1-500


    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    public Planet(){

    }
    public Planet(String id, String name) throws InvalidPropertiesFormatException {
        if (checkUppercaseOrDigits(id) & (name.length() >= 1 & name.length() <= 500)) {
            this.id = id;
            this.name = name;
        } else {
            throw new InvalidPropertiesFormatException("Invalid name or id. " +
                    "Id must be in upper case and can contain digits\n" + ", name must be in range from 1 to 500");
        }
    }


    public static boolean checkUppercaseOrDigits(String str) {
        // regular expression to match at least one uppercase letter and all characters are in uppercase, or only digits
        String pattern = "^(?=.*[A-Z])[A-Z0-9]+$";

        // check if the string matches the pattern
        if (str.matches(pattern) && str.equals(str.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }
}
