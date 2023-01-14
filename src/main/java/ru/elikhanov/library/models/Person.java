package ru.elikhanov.library.models;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Elikhanov
 */

@Data
public class Person {
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should longer than 2")
    private String name;

    @NotNull(message = "Year Of Release should not be empty")
    @Range(min = 14, message= "age may not be empty or less than 14")
    private int age;

    public Person(int personId, String name, int age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
}
