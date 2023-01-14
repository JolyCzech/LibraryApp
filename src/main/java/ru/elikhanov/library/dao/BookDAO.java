package ru.elikhanov.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.elikhanov.library.models.Book;
import ru.elikhanov.library.models.Person;
import java.util.List;
import java.util.Optional;


/**
 * @author Elikhanov
 */

@AllArgsConstructor
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;


    public List<Book> index() {
        return jdbcTemplate.query("select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from Book where book_id =?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into Book(title, author, year_Of_Release) values(?,?,?)",
                book.getTitle(),book.getAuthor(),book.getYearOfRelease());
    }
    public void update(int id,Book updatedBook) {
        jdbcTemplate.update("update Book set title = ?, author=?,year_Of_Release  = ? where book_id = ?",
                updatedBook.getTitle(),updatedBook.getAuthor(),updatedBook.getYearOfRelease(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book where book_id=?", id);
    }


    public Optional<Person> getUser(int id) {
        return jdbcTemplate.query("select Person.* from  Person join Book on Person.person_id=Book.person_id where book_id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void deleteBookUser(int id) {
        jdbcTemplate.update("update book set person_id = null where book_id =?", id);
    }

    public void addBookUser(Person newUser, int id) {
        jdbcTemplate.update("update book set person_id = ? where book_id =?", newUser.getPersonId(),id);
    }

}

