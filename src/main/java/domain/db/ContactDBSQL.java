package domain.db;

import domain.model.Contact;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Contact contact) {
        if (contact == null) throw new DbException("Contact is null");
        String sql = String.format("INSERT INTO %s.contact (userid, firstname, lastname, date, time, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getUserid());
            statementSQL.setString(2, contact.getFirstName());
            statementSQL.setString(3, contact.getLastName());
            statementSQL.setDate(4, Date.valueOf(contact.getDate()));
            statementSQL.setTime(5, Time.valueOf(contact.getTime()));
            statementSQL.setString(6, contact.getPhoneNumber());
            statementSQL.setString(7, contact.getEmail());
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) throw new DbException("Contact already exists");
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                contacts.add(createContact(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public List<Contact> get(String userid) {
        if (userid == null || userid.isEmpty()) throw new DbException("No id given");
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                contacts.add(createContact(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    @Override
    public Contact getOne(String userid, String firstName, String lastName, String date, String time) {
        if (userid == null || userid.isEmpty()) throw new DbException("No id given");
        String sql = String.format("SELECT * FROM %s.contact WHERE userid = ? AND firstname = ? AND lastname = ? AND date = ? AND time = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.setString(2, firstName);
            statementSQL.setString(3, lastName);
            statementSQL.setDate(4, Date.valueOf(date));
            statementSQL.setTime(5, Time.valueOf(time));
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) return createContact(result);
            else throw new DbException("Contact not found");
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeAll() {
        String sql = String.format("DELETE FROM %s.contact *", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void remove(String userid) {
        String sql = String.format("DELETE FROM %s.contact WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void removeOne(Contact contact) {
        if (contact == null) throw new DbException("No contact given");
        String sql = String.format("DELETE FROM %s.contact WHERE userid = ? AND firstname = ? AND lastname = ? AND date = ? AND time = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getUserid());
            statementSQL.setString(2, contact.getFirstName());
            statementSQL.setString(3, contact.getLastName());
            statementSQL.setDate(4, Date.valueOf(contact.getDate()));
            statementSQL.setTime(5, Time.valueOf(contact.getTime()));
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Contact createContact(ResultSet result) throws SQLException {
        String userid = result.getString("userid");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        LocalDate date = result.getDate("date").toLocalDate();
        LocalTime time = result.getTime("time").toLocalTime();
        String phone = result.getString("phone");
        String email = result.getString("email");
        return new Contact(userid, firstname, lastname, date, time, phone, email);
    }
}