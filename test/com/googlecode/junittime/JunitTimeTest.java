package com.googlecode.junittime;

import com.googlecode.junittime.utils.FileUtil;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.Project;
import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JunitTimeTest {
    private FileSet testReports;
    private JunitTime jt;
    private File toDir;

    @Before
    public void setup() throws IOException {
        testReports = createJunitReportFiles();
        toDir = FileUtil.createTempFolder();
        jt = new JunitTime();
    }

    private FileSet createJunitReportFiles() {
        FileSet reports = new FileSet();
        reports.setProject(new Project());
        reports.setDir(generateTestReports());
        return reports;
    }

    private File generateTestReports() {
        File testReportsDir = FileUtil.createTempFolder();
        
        return testReportsDir;
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
        File toBeCreated = new File(toDir, "report");
        toBeCreated.deleteOnExit();

        jt.addFileSet(testReports);
        jt.setToDir(toBeCreated);

        assertThat(toBeCreated.exists(), is(false));
        jt.execute();
        assertThat(toBeCreated.exists(), is(true));        
    }
}
