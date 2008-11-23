package com.googlecode.junittime;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.io.IOException;

public class JunitTime extends Task {
    static final String REPORT_CSV = "junit-time-report.csv";
    
    private File toDir;

    public void addFileSet(FileSet from) {
        
    }

    public void setToDir(File toDir) {
        this.toDir = toDir;
    }

    public void execute() throws BuildException {
        createFile(reportFile());
    }

    private File reportFile() {
        return new File(toDir, REPORT_CSV);
    }

    private void createFile(File file) {
        file.mkdirs();
    }
}
