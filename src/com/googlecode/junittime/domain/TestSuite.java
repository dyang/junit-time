package com.googlecode.junittime.domain;

import java.util.List;
import java.util.ArrayList;

public class TestSuite {
    private List<TestCase> testCases = new ArrayList<TestCase>();
    private double duration;

    public void add(TestCase testCase) {
        testCases.add(testCase);
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    // TODO - not sure if i want to expose testCases in such a direct way
    // what happens if i change the way how testCases are stored?
    public List<TestCase> getTestCases() {
        return testCases;
    }
}
