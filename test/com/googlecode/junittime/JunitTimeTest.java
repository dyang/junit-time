package com.googlecode.junittime;

import com.googlecode.junittime.utils.FileUtil;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.Project;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.File;
import java.io.IOException;

public class JunitTimeTest {
    private static final String TEST_DATA = "test/data";

    private JunitTime jt;
    private FileSet testReports;
    private File toDir;
    private File testResultsDir;

    @Before
    public void setup() throws IOException {
        testReports = createJunitReportFiles();
        toDir = FileUtil.createTempFolder();
        jt = new JunitTime();
    }

    @After
    public void tearDown() {
        FileUtil.delete(testResultsDir);
        FileUtil.delete(toDir);
    }

    private FileSet createJunitReportFiles() throws IOException {
        FileSet testResults = new FileSet();
        testResults.setProject(new Project());
        testResultsDir = generateTestResults();
        testResults.setDir(testResultsDir);
        return testResults;
    }

    private File generateTestResults() throws IOException {
        File testResultsDir = FileUtil.createTempFolder();
        FileUtils.copyDirectory(new File(TEST_DATA), testResultsDir);
        return testResultsDir;
    }

    @Test
    public void shouldGenerateReport() {
        jt.addFileSet(testReports);
        jt.setToDir(toDir);

        assertThat(toDir.listFiles().length, is(0));
        jt.execute();
        assertThat(toDir.listFiles().length, is(1));
        assertThat(toDir.listFiles()[0].getName(), is(JunitTime.REPORT_CSV));
    }

    @Test
    public void shouldCreateToDirIfNotExist() {
        toDir = new File(toDir, "report");
        toDir.deleteOnExit();

        jt.addFileSet(testReports);
        jt.setToDir(toDir);

        assertThat(toDir.exists(), is(false));
        jt.execute();
        assertThat(toDir.exists(), is(true));        
    }
}
