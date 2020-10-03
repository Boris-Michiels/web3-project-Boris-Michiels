package domain.model;

import domain.db.PersonService;

import java.util.List;

public class ContactTracingService {
    private PersonService personService = new PersonService();

    public ContactTracingService() {
    }

    public Person getPerson(String personId) {
        return getPersonService().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonService().getAll();
    }

    public void addPerson(Person person) {
        getPersonService().add(person);
    }

    public void updatePersons(Person person) {
        getPersonService().update(person);
    }

    public void deletePerson(String id) {
        getPersonService().delete(id);
    }

    private PersonService getPersonService() {
        return personService;
    }
}