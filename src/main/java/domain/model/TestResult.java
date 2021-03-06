package domain.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TestResult {
    private int testResultid;
    private String userid;
    private LocalDate date;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateFormatterString = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TestResult(int testResultid, String userid, LocalDate date) {
        setTestResultid(testResultid);
        setUserid(userid);
        setDate(date);
    }

    public TestResult() {
    }

    private void setTestResultid(int testResultid) {
        this.testResultid = testResultid;
    }

    public int getTestResultid() {
        return testResultid;
    }

    public void setUserid(String userid) {
        if (userid == null || userid.trim().isEmpty()) throw new DomainException("No userid given");
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setDate(LocalDate date) {
        if (date == null) throw new DomainException("No date given");
        if (date.isAfter(LocalDate.now())) throw new DomainException("The date you gave was in the future");
        this.date = date;
    }

    public void setDateString(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) throw new DomainException("No date given");
        try {
            setDate(LocalDate.parse(dateString, dateFormatter));
        } catch (DateTimeParseException d) {
            throw new DomainException("Date is not valid");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        return date.format(dateFormatterString);
    }
}