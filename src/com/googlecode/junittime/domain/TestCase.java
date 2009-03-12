package com.googlecode.junittime.domain;

import org.apache.commons.lang.StringUtils;
import static com.googlecode.junittime.domain.Slowness.*;

public class TestCase {
    private String packageName;
    private String className;
    private String testName;
    private double duration;

    public TestCase(String className, String testName, double duration) {
        this("", className, testName, duration);
    }

    public TestCase(String packageName, String className, String testName, double duration) {
        this.packageName = packageName;
        this.className = className;
        this.testName = testName;
        this.duration = duration;
    }

    public String getClassName() {
        return className;
    }

    public String getTestName() {
        return testName;
    }

    public double getDuration() {
        return duration;
    }

    public Slowness compareDuration(TestCase anotherTestCase) {
        double slowness = duration - anotherTestCase.duration;

        if (slowness > 0) {
            return SLOWER;
        } else if (slowness == 0) {
            return SAME;
        } else {
            return FASTER;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        if (Double.compare(testCase.duration, duration) != 0) return false;
        if (className != null ? !className.equals(testCase.className) : testCase.className != null) return false;
        if (packageName != null ? !packageName.equals(testCase.packageName) : testCase.packageName != null)
            return false;
        if (testName != null ? !testName.equals(testCase.testName) : testCase.testName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        temp = duration != +0.0d ? Double.doubleToLongBits(duration) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String toString() {
        return String.format("[%s | %s | %s | %f]", packageName, className, testName, duration);
    }
}
