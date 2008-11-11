package com.googlecode.junittime.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestCaseRepository {
    List<TestCase> testCases = new ArrayList<TestCase>();

    public void add(TestCase testCase) {
        testCases.add(testCase);
    }

    public List<TestCase> byDurationDesc() {
        Collections.sort(testCases, new Comparator<TestCase>() {

            public int compare(TestCase testCase1, TestCase testCase2) {
                return testCase1.compareDuration(testCase2);                
            }
        });
        return testCases;
    }
}
