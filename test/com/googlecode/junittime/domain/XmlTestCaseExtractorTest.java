package com.googlecode.junittime.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlTestCaseExtractorTest {
    private static final String TEST_DATA = "test/data";
    private static final String FILE_WITH_ONE_TESTCASE = "TEST-com.googlecode.junittime.domain.TestCaseRepositoryTest.xml";
    private static final String FILE_WITH_TWO_TESTCASES = "TEST-com.googlecode.junittime.JunitTimeTest.xml";

    @Test
    public void shouldExtractFromXmlTestResultFile() throws IOException, SAXException, ParserConfigurationException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_ONE_TESTCASE));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        List<TestCase> testCases = repository.byDurationDesc();
        assertThat(testCases.size(), is(1));
        assertThat(testCases.get(0),
                is(new TestCase("com.googlecode.junittime.domain.TestCaseRepositoryTest", "shouldSortTestsAccordingToDurationDesc", 0.0020)));
    }

    @Test
    public void shouldExtractFromXmlTestResultFileWithMultipleTestCases() throws IOException, SAXException, ParserConfigurationException {
        XmlTestCaseExtractor extractor = new XmlTestCaseExtractor(new File(TEST_DATA, FILE_WITH_TWO_TESTCASES));
        TestCaseRepository repository = new TestCaseRepository();
        extractor.extractTo(repository);

        List<TestCase> testCases = repository.byDurationDesc();
        assertThat(testCases.size(), is(2));
        assertThat(testCases.get(0),
                is(new TestCase("com.googlecode.junittime.JunitTimeTest", "shouldCreateToDirIfNotExist", 0.0020)));
        assertThat(testCases.get(1),
                is(new TestCase("com.googlecode.junittime.JunitTimeTest", "shouldGenerateReport", 0.029)));
    }
}
