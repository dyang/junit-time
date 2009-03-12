package com.googlecode.junittime.domain;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class XmlTestCaseExtractorTest {
    private static final String TEST_DATA = "test/data";
    private static final String FILE_WITH_ONE_TESTCASE = "TEST-com.googlecode.junittime.domain.TestCaseRepositoryTest.xml";
    private static final String FILE_WITH_TWO_TESTCASES = "TEST-com.googlecode.junittime.JunitTimeTest.xml";
    private static final String FILE_WITH_NO_PACKAGENAME = "TEST-com.googlecode.junittime.domain.reporting.HtmlReportGeneratorTest.xml";

    @Test
    public void shouldExtractFromXmlTestResultFile() throws ExtractionException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_ONE_TESTCASE));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        List<TestCase> testCases = repository.byDurationDesc();
        assertThat(testCases.size(), is(1));
        assertThat(testCases.get(0),
                is(new TestCase("com.googlecode.junittime.domain", "TestCaseRepositoryTest", "shouldSortTestsAccordingToDurationDesc", 0.0020)));
    }

    @Test
    public void shouldExtractFromXmlTestResultFileWithMultipleTestCases() throws ExtractionException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_TWO_TESTCASES));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        List<TestCase> testCases = repository.byDurationDesc();
        assertThat(testCases.size(), is(2));
        assertThat(testCases.get(0),
                is(new TestCase("com.googlecode.junittime", "JunitTimeTest", "shouldGenerateReport", 0.029)));
        assertThat(testCases.get(1),
                is(new TestCase("com.googlecode.junittime", "JunitTimeTest", "shouldCreateToDirIfNotExist", 0.0020)));
    }

    @Test
    public void shouldThrowWhenExtractionFails() {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, "fileNotExist"));
        TestCaseRepository repository = new TestCaseRepository();
        try {
            extractor.extractTo(repository);
            fail("should have thrown when file is not found");
        } catch (ExtractionException e) {
        }
    }

    @Test
    public void shouldExtractTestCasesWithoutPackageName() throws ExtractionException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_NO_PACKAGENAME));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        List<TestCase> testCases = repository.byDurationDesc();
        assertThat(testCases.size(), is(1));
        assertThat(testCases.get(0),
                is(new TestCase("", "HtmlReportGeneratorTest", "shouldGenerateReportThatHighlightSlowTests", 0.001)));
    }

    @Test
    public void shouldExtractTestSuiteDuration() throws ExtractionException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_TWO_TESTCASES));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        double duration = repository.duration();
        assertThat(duration, is(0.331d));
        
    }
}
