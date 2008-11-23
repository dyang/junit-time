package com.googlecode.junittime.domain;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.*;

public class TestCaseTest {
    @Test
    public void shouldReturnOneIfAIsSlowerThanB() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 6.0d);
        assertThat(testCase1.compareDuration(testCase2), is(1));
    }

    @Test
    public void shouldReturnMinusOneIfAIsFasterThanB() {
        TestCase testCase1 = new TestCase("className", "testName", 6.0d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1.compareDuration(testCase2), is(-1));
    }
    
    @Test
    public void shouldReturnZeroIfAEqualsToB() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1.compareDuration(testCase2), is(0));
    }

    @Test
    public void equalityTest() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1, is(testCase2));
    }
}
