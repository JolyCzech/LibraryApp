package ru.elikhanov.library.models;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Elikhanov
 */


@Data
public class Book {

    private int bookId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, message = "Title should longer than 2")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, message = "Author should longer than 2")
    private String author;

    @NotNull(message = "Year Of Release should not be empty")
    @Min(value = 1800, message = "Age should be more than 1800")
    private int yearOfRelease;

    public Book(int id, String title, String author, int yearOfRelease) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
    }

    public Book() {
    }
}
