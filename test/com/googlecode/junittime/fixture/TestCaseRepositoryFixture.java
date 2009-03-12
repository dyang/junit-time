package com.googlecode.junittime.fixture;

import com.googlecode.junittime.domain.TestCase;
import com.googlecode.junittime.domain.TestCaseRepository;
import com.googlecode.junittime.domain.TestSuite;

public class TestCaseRepositoryFixture {
    public static final TestCase TEST1 = new TestCase("package.class1", "test", 1d);
    public static final TestCase TEST2 = new TestCase("package.class2", "test", 2d);
    public static final TestCase TEST3 = new TestCase("package.class3", "test", 1.5d);

    public static TestCaseRepository createDefaultRepository() {
        return createRepositoryWith(TEST1, TEST2, TEST3);
    }

    public static TestCaseRepository createRepositoryWith(TestCase... testCases) {
        TestSuite suite = new TestSuite();
        for (TestCase testCase : testCases) {
            suite.add(testCase);
        }
        TestCaseRepository repository = new TestCaseRepository();
        repository.add(suite);
        return repository;
    }

}
