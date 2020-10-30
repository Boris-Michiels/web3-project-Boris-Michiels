package domain.db;

import domain.model.Person;
import util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Person is null");
        }
        String sql = String.format("INSERT INTO %s.person (userid, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getPassword());
            statementSQL.setString(4, person.getFirstName());
            statementSQL.setString(5, person.getLastName());
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) {
                throw new DbException("User id already taken");
            }
            throw new DbException(e);
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                persons.add(createPerson(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return persons;
    }

    @Override
    public Person get(String userid) {
        if (userid == null || userid.isEmpty()) {
            throw new DbException("No id given");
        }
        String sql = String.format("SELECT * FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) {
                return createPerson(result);
            } else throw new DbException("Userid not found");
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("Person is null");
        }
        String sql = String.format("UPDATE %s.person SET password = ? WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getPassword());
            statementSQL.setString(2, person.getUserid());
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(String userid) {
        if (userid == null) {
            throw new DbException("No id given");
        }
        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Person createPerson(ResultSet result) throws SQLException {
        String userid = result.getString("userid");
        String email = result.getString("email");
        String hashedPassword = result.getString("password");
        String firstname = result.getString("firstname");
        String lastname = result.getString("lastname");
        return new Person(userid, email, hashedPassword, firstname, lastname);
    }
}