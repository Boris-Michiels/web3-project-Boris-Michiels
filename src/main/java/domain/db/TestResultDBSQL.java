package domain.db;

import domain.model.TestResult;
import util.DBConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestResultDBSQL implements TestResultDB {
    private Connection connection;
    private String schema;

    public TestResultDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(TestResult testResult) {
        if (testResult == null) throw new DbException("Contact is null");
        String sql = String.format("INSERT INTO %s.testresult (userid, date) VALUES (?, ?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, testResult.getUserid());
            statementSQL.setDate(2, Date.valueOf(testResult.getDate()));
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) throw new DbException("Contact already exists");
            throw new DbException(e);
        }
    }

    @Override
    public List<TestResult> getAll() {
        List<TestResult> testResults = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.testresult", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                testResults.add(createTestResult(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return testResults;
    }

    @Override
    public List<TestResult> get(String userid) {
        if (userid == null || userid.isEmpty()) throw new DbException("No id given");
        List<TestResult> testResults = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.testresult WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                testResults.add(createTestResult(result));
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return testResults;
    }

    @Override
    public TestResult getOne(int testResultid) {
        String sql = String.format("SELECT * FROM %s.testresult WHERE testresultid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, testResultid);
            ResultSet result = statementSQL.executeQuery();
            if (result.next()) return createTestResult(result);
            else throw new DbException("Test Result not found");
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public TestResult getLatestTestResult(String userid) {
        List<TestResult> testResults = get(userid);
        if (testResults.isEmpty()) return null;
        TestResult latestTestResult = Collections.max(testResults, Comparator.comparing(TestResult::getDate));
        return latestTestResult;
    }

    @Override
    public void remove(String userid) {
        String sql = String.format("DELETE FROM %s.testresult WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, userid);
            statementSQL.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private TestResult createTestResult(ResultSet result) throws SQLException {
        int testResultid = result.getInt("testresultid");
        String userid = result.getString("userid");
        LocalDate date = result.getDate("date").toLocalDate();
        return new TestResult(testResultid, userid, date);
    }
}