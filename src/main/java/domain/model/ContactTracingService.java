package domain.model;

import domain.db.PersonDBSQL;

import java.util.List;

public class ContactTracingService {
    private PersonDBSQL personDBSQL = new PersonDBSQL();

    public ContactTracingService() {
    }

    public Person getPerson(String personId) {
        return getPersonDBSQL().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDBSQL().getAll();
    }

    public void addPerson(Person person) {
        getPersonDBSQL().add(person);
    }

    public void updatePerson(Person person) {
        getPersonDBSQL().update(person);
    }

    public void deletePerson(String id) {
        getPersonDBSQL().delete(id);
    }

    private PersonDBSQL getPersonDBSQL() {
        return personDBSQL;
    }
}