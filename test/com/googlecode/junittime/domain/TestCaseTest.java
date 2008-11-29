package com.googlecode.junittime.domain;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.*;
import static com.googlecode.junittime.domain.Slowness.*;

public class TestCaseTest {
    @Test
    public void shouldReturnSlowerIfAIsSlowerThanB() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 6.0d);
        assertThat(testCase1.compareDuration(testCase2), is(SLOWER));
    }

    @Test
    public void shouldReturnFasterIfAIsFasterThanB() {
        TestCase testCase1 = new TestCase("className", "testName", 6.0d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1.compareDuration(testCase2), is(FASTER));
    }
    
    @Test
    public void shouldReturnSameIfAEqualsToB() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1.compareDuration(testCase2), is(SAME));
    }

    @Test
    public void equalityTest() {
        TestCase testCase1 = new TestCase("className", "testName", 10.5d);
        TestCase testCase2 = new TestCase("className", "testName", 10.5d);
        assertThat(testCase1, is(testCase2));
    }
}
