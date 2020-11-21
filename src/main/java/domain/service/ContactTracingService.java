package domain.service;

import domain.db.ContactDBSQL;
import domain.db.PersonDBSQL;
import domain.model.Contact;
import domain.model.Person;

import java.util.List;

public class ContactTracingService {
    private PersonDBSQL personDBSQL = new PersonDBSQL();
    private ContactDBSQL contactDBSQL = new ContactDBSQL();

    public ContactTracingService() {
    }

    public Person getPerson(String userid) {
        return getPersonDBSQL().get(userid);
    }

    public List<Person> getAllPersons() {
        return getPersonDBSQL().getAll();
    }

    public void addPerson(Person person) {
        getPersonDBSQL().add(person);
    }

    public void updatePerson(Person person) {
        getPersonDBSQL().update(person);
    }

    public void deletePerson(Person person) {
        getPersonDBSQL().delete(person);
    }

    private PersonDBSQL getPersonDBSQL() {
        return personDBSQL;
    }



    public List<Contact> getContacts(String userid) {
        return getContactDBSQL().get(userid);
    }

    public List<Contact> getAllContacts() {
        return getContactDBSQL().getAll();
    }

    public Contact getOneContact(int contactid) {
        return getContactDBSQL().getOne(contactid);
    }

    public void addContact(Contact contact) {
        getContactDBSQL().add(contact);
    }

    public void removeContacts(String userid) {
        getContactDBSQL().remove(userid);
    }

    public void removeAllContacts() {
        getContactDBSQL().removeAll();
    }

    public void removeOneContact(int contactid) {
        getContactDBSQL().removeOne(contactid);
    }

    private ContactDBSQL getContactDBSQL() {
        return contactDBSQL;
    };
}