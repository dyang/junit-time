package com.googlecode.junittime.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestCaseRepository {
    private List<TestSuite> testSuites = new ArrayList<TestSuite>();

    public List<TestCase> byDurationDesc() {
        List<TestCase> allTestCases = allTestCases();
        Collections.sort(allTestCases, new Comparator<TestCase>() {
            public int compare(TestCase testCase1, TestCase testCase2) {
                return testCase1.compareDuration(testCase2).toInt();
            }
        });
        return allTestCases;
    }

    private List<TestCase> allTestCases() {
        List<TestCase> allTestCases = new ArrayList<TestCase>();
        for (TestSuite testSuite : testSuites) {
            allTestCases.addAll(testSuite.getTestCases());
        }
        return allTestCases;
    }

    public void add(TestSuite testSuite) {
        testSuites.add(testSuite);
    }
}
