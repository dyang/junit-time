package com.googlecode.junittime.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.Before;
import org.apache.commons.io.FileUtils;

import java.util.List;

import com.googlecode.junittime.domain.reporting.CSVReportGenerator;
import com.googlecode.junittime.domain.reporting.Lines;

public class TestCaseRepositoryTest {
    private TestCase test1;
    private TestCase test2;
    private TestCase test3;
    private TestCaseRepository repository;

    @Before
    public void setup() {
        test1 = new TestCase("package.class1", "test", 1d);
        test2 = new TestCase("package.class2", "test", 2d);
        test3 = new TestCase("package.class3", "test", 1.5d);

        repository = new TestCaseRepository();
        repository.add(test1);
        repository.add(test2);
        repository.add(test3);
    }

    @Test
    public void shouldSortTestsAccordingToDurationDesc() {
        List<TestCase> allSorted = repository.byDurationDesc();
        assertThat(allSorted.size(), is(3));
        assertThat(allSorted.get(0), is(test2));
        assertThat(allSorted.get(1), is(test3));
        assertThat(allSorted.get(2), is(test1));
    }
}
