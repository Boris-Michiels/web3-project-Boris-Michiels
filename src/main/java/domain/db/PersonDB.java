package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    void add(Person person);
    List<Person> getAll();
    Person get(String userid);
    void update(Person person);
    void delete(String userid);
}