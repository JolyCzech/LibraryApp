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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        return jdbcTemplate.query("select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from Person where person_id =?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into Person(name,age) values(?,?)",
                person.getName(), person.getAge());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set name = ?, age = ? where person_id = ?",
                updatedPerson.getName(), updatedPerson.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Person where person_id=?", id);
    }

    public Optional<Person> checkName(String name){
        return jdbcTemplate.query("select * from person where name=?",new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("select * from Book where person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
