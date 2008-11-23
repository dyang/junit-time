package com.googlecode.junittime;

import com.googlecode.junittime.utils.FileUtil;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.BuildException;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.fail;

public class JunitTimeTest {
    private static final String TEST_DATA = "test/data";

    private JunitTime jt;
    private File toDir;
    private File testResultsDir;

    @Before
    public void setup() throws IOException {
        jt = new JunitTime();

        toDir = FileUtil.createTempFolder();
        jt.setToDir(toDir);
        testResultsDir = generateTestResults();
        jt.addFileSet(createJunitReportFiles(testResultsDir));
    }

    @After
    public void tearDown() {
        FileUtil.delete(testResultsDir);
        FileUtil.delete(toDir);
    }

    private FileSet createJunitReportFiles(File dir) throws IOException {
        FileSet testResults = new FileSet();
        testResults.setProject(new Project());
        testResults.setDir(dir);
        return testResults;
    }

    private File generateTestResults() throws IOException {
        File testResultsDir = FileUtil.createTempFolder();
        FileUtils.copyDirectory(new File(TEST_DATA), testResultsDir);
        return testResultsDir;
    }

    @Test
    public void shouldGenerateReport() {
        assertThat(toDir.listFiles().length, is(0));

        jt.execute();

        assertThat(toDir.listFiles().length, is(1));
        assertThat(toDir.listFiles()[0].getName(), is(JunitTime.REPORT_CSV));
    }

    @Test
    public void shouldCreateToDirIfNotExist() {
        toDir = new File(toDir, "report");
        jt.setToDir(toDir);

        assertThat(toDir.exists(), is(false));

        jt.execute();

        assertThat(toDir.exists(), is(true));
    }

    @Test
    public void shouldThrowIfTestResultsDirNotExist() throws IOException {
        testResultsDir = new File(testResultsDir, "dirNotExist");
        jt.addFileSet(createJunitReportFiles(testResultsDir));

        assertThat(testResultsDir.exists(), is(false));

        try {
            jt.execute();
            fail("should have thrown when test results are not found");
        } catch (BuildException e) {

        }
    }

    @Test
    @Ignore("end2end test - implementation in progress")
    public void shouldExtractTestResultAndGenerateCSVReport() throws IOException {
        jt.execute();

        File csv = toDir.listFiles()[0];
        Lines lines = new Lines(FileUtils.readLines(csv));

        assertThat(lines.size(), is(6));
        assertThat(lines.at(0), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnOneIfAIsSlowerThanB, 0.0010"));
        assertThat(lines.at(1), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnMinusOneIfAIsFasterThanB, 0.0010"));
        assertThat(lines.at(2), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnZeroIfAEqualsToB, 0.0010"));
        assertThat(lines.at(3), is("com.googlecode.junittime.domain.TestCaseRepositoryTest, shouldSortTestsAccordingToDurationDesc, 0.0020"));
        assertThat(lines.at(4), is("com.googlecode.junittime.JunitTimeTest, shouldCreateToDirIfNotExist, 0.0020"));
        assertThat(lines.at(5), is("com.googlecode.junittime.JunitTimeTest, com.googlecode.junittime.JunitTimeTest, 0.029"));
    }

    private class Lines{
        private List lines;

        Lines(List lines) {
            this.lines = lines;
        }

        String at(int index) {
            return (String) lines.get(index);
        }

        int size() {
            return lines.size();
        }
    }
}
