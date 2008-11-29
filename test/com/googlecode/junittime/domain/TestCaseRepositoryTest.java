package com.googlecode.junittime.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.apache.commons.io.FileUtils;

import java.util.List;

import com.googlecode.junittime.domain.reporting.CSVReportGenerator;
import com.googlecode.junittime.domain.reporting.Lines;

public class TestCaseRepositoryTest {
    @Test
    public void shouldSortTestsAccordingToDurationDesc() {
        TestCase test1 = new TestCase("package.class1", "test", 1d);
        TestCase test2 = new TestCase("package.class2", "test", 2d);
        TestCase test3 = new TestCase("package.class3", "test", 1.5d);

        TestCaseRepository testCases = new TestCaseRepository();
        testCases.add(test1);
        testCases.add(test2);
        testCases.add(test3);

        List<TestCase> allSorted = testCases.byDurationDesc();
        assertThat(allSorted.size(), is(3));
        assertThat(allSorted.get(0), is(test2));
        assertThat(allSorted.get(1), is(test3));
        assertThat(allSorted.get(2), is(test1));
    }
}
