package ru.elikhanov.library.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.elikhanov.library.dao.PersonDAO;
import ru.elikhanov.library.models.Person;

/**
 * @author Elikhanov
 */

@AllArgsConstructor
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person= (Person) o;

        if (personDAO.checkName(person.getName()).isPresent()){
            errors.rejectValue("name","","This name is already taken");
        }
    }
}
