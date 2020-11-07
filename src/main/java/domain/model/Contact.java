package domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String userid;
    private String firstName;
    private String lastName;
    private LocalDate date;
    private LocalTime time;
    private String phoneNumber;
    private String email;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter dateFormatterJDBC = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter timeFormatterJDBC = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH'h'");

    public Contact(String userid, String firstName, String lastName, LocalDate date, LocalTime time, String phoneNumber, String email) {
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setDate(date);
        setTime(time);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public Contact() {
    }

    public void setUserid(String userid) {
        if (userid == null || userid.isEmpty()) throw new DomainException("No userid given");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) throw new DomainException("No firstname given");
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) throw new DomainException("No last name given");
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("No date given");
        this.date = date;
    }

    public void setDateString(String date) {
        if (date == null || date.trim().isEmpty()) throw new DomainException("No date given");
        this.date = LocalDate.parse(date, dateFormatterJDBC);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return date.format(dateFormatter);
    }

    public String getDateStringJDBC() {
        return date.format(dateFormatterJDBC);
    }

    public void setTime(LocalTime time) {
        if (time == null) throw new DomainException("No time given");
        this.time = time;
    }

    public void setTimeString(String time) {
        if (time == null || time.trim().isEmpty()) throw new DomainException("No time given");
        this.time = LocalTime.parse(time, timeFormatter);
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTimeString() {
        return time.format(timeFormatter);
    }

    public String getTimeStringJDBC() {
        return time.format(timeFormatterJDBC);
    }

    public String getHour() {
        return time.format(hourFormatter);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) throw new DomainException("No phone number given");
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) throw new DomainException("No email given");
        String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) throw new DomainException("Email not valid");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}