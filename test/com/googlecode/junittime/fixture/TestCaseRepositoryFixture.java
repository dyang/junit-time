package com.googlecode.junittime.fixture;

import com.googlecode.junittime.domain.TestCase;
import com.googlecode.junittime.domain.TestCaseRepository;

public class TestCaseRepositoryFixture {
    public static final TestCase TEST1 = new TestCase("package.class1", "test", 1d);
    public static final TestCase TEST2 = new TestCase("package.class2", "test", 2d);
    public static final TestCase TEST3 = new TestCase("package.class3", "test", 1.5d);

    public static TestCaseRepository createDefaultRepository() {
        TestCaseRepository repository = new TestCaseRepository();
        repository.add(TEST1);
        repository.add(TEST2);
        repository.add(TEST3);
        return repository;
    }

    public static TestCaseRepository createRepositoryWith(TestCase... testCases) {
        TestCaseRepository repository = new TestCaseRepository();
        for (TestCase testCase : testCases) {
            repository.add(testCase);
        }
        return repository;
    }

}
