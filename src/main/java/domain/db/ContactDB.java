package domain.db;

import domain.model.Contact;

import java.util.List;

public interface ContactDB {
    void add(Contact contact);
    List<Contact> getAll();
    List<Contact> get(String userid);
    Contact getOne(String userid, String firstName, String lastName, String date, String time);
    void removeAll();
    void remove(String userid);
    void removeOne(Contact contact);
}