package com.googlecode.junittime;

import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileSet;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JunitTime extends Task {
    static final String REPORT_CSV = "junit-time-report.csv";

    private File toDir;
    private FileSet from;

    public void addFileSet(FileSet from) {
        this.from = from;
    }

    public void setToDir(File toDir) {
        this.toDir = toDir;
    }

    public void execute() throws BuildException {
        ensureTestResultsExist();
        generateReport();
    }

    private void generateReport() {
        File reportFile = reportFile();
        try {
            createFile(reportFile);
        } catch (IOException e) {
            throw new BuildException("Unable to create report at " + reportFile.getAbsolutePath());
        }
    }

    private void ensureTestResultsExist() {
        if (!from.getDir().exists()) {
            throw new BuildException("Directory " + from.getDir().getAbsolutePath() + " not found");
        }
    }

    private File reportFile() {
        return new File(toDir, REPORT_CSV);
    }

    private void createFile(File file) throws IOException {
        FileUtils.touch(file);
    }
}
