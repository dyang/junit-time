package com.googlecode.junittime;

import com.googlecode.junittime.utils.FileUtil;
import org.apache.tools.ant.types.FileSet;
import org.hamcrest.core.Is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JunitTimeTest {
    private FileSet testReports;
    private JunitTime jt;
    private File resultFile;

    @Before
    public void setup() throws IOException {
        testReports = new FileSet();
        testReports.setDir(FileUtil.createTempFolder());
        resultFile = FileUtil.aTempFile();

        jt = new JunitTime();
    }
    
    @Test
    public void shouldGenerateReport() {
        assertThat(resultFile.exists(), Is.is(false));

        jt.addFileSet(testReports);
        jt.setResultFile(resultFile);

        assertThat(resultFile.exists(), Is.is(true));
    }
}
