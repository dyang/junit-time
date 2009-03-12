package com.googlecode.junittime.domain;

import static com.googlecode.junittime.fixture.TestCaseRepositoryFixture.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCaseRepositoryTest {
    private TestCaseRepository repository;

    @Before
    public void setup() {
        repository = createDefaultRepository();
    }

    @Test
    public void shouldSortTestsAccordingToDurationDesc() {
        List<TestCase> allSorted = repository.byDurationDesc();
        assertThat(allSorted.size(), is(3));
        assertThat(allSorted.get(0), is(TEST2));
        assertThat(allSorted.get(1), is(TEST3));
        assertThat(allSorted.get(2), is(TEST1));
    }
}
