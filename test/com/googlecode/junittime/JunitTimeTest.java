package com.googlecode.junittime;

import com.googlecode.junittime.domain.reporting.Lines;
import com.googlecode.junittime.utils.FileUtil;
import static junit.framework.Assert.fail;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.io.File;
import java.io.IOException;

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

    @Test
    public void shouldGenerateReport() {
        assertThat(toDir.listFiles().length, is(0));

        jt.execute();

        assertThat(toDir.listFiles().length, is(1));
        assertThat(toDir.listFiles()[0].getName(), is(JunitTime.REPORT_HTML));
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
    @Ignore("need to think of a better way to swap reports")
    public void shouldExtractTestResultAndGenerateCSVReport() throws IOException {
        jt.execute();

        File csv = toDir.listFiles()[0];
        Lines lines = new Lines(FileUtils.readLines(csv));

        assertThat(lines.size(), is(6));
        assertThat(lines.at(0), is("com.googlecode.junittime.JunitTimeTest, shouldGenerateReport, 0.029"));
        assertThat(lines.at(1), is("com.googlecode.junittime.JunitTimeTest, shouldCreateToDirIfNotExist, 0.002"));
        assertThat(lines.at(2), is("com.googlecode.junittime.domain.TestCaseRepositoryTest, shouldSortTestsAccordingToDurationDesc, 0.002"));
        assertThat(lines.at(3), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnOneIfAIsSlowerThanB, 0.001"));
        assertThat(lines.at(4), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnMinusOneIfAIsFasterThanB, 0.001"));
        assertThat(lines.at(5), is("com.googlecode.junittime.domain.TestCaseTest, shouldReturnZeroIfAEqualsToB, 0.001"));
    }

    @Test
    public void shouldThrowIfFailToExtract() throws IOException {
        FileUtils.touch(new File(testResultsDir, "invalid.xml"));

        try {
            jt.execute();
            fail("should throw when fail to extract");
        } catch (BuildException e) {            
        }
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
}
