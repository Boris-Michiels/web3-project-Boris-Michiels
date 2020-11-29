package domain.db;

import domain.model.TestResult;

import java.util.List;

public interface TestResultDB {
    void add(TestResult testResult);
    List<TestResult> getAll();
    List<TestResult> get(String userid);
    TestResult getOne(int testResultid);
    TestResult getLatestTestResult(String userid);
    void remove(String userid);
}