package domain.db;

import domain.model.TestResult;
import util.DBConnectionService;

import java.sql.*;

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
}