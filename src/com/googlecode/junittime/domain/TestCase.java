package com.googlecode.junittime.domain;

public class TestCase {
    private String className;
    private String testName;
    private double duration;

    public TestCase(String className, String testName, double duration) {
        this.className = className;
        this.testName = testName;
        this.duration = duration;
    }

    public int compareDuration(TestCase anotherTestCase) {
        double slowness = duration - anotherTestCase.duration;

        if (slowness > 0) {
            return 1;
        } else if (slowness == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
