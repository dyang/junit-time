package com.googlecode.junittime.domain.reporting;

import com.googlecode.junittime.domain.TestCaseRepository;

import java.io.File;
import java.io.IOException;

public interface ReportGenerator {
    void generate(File reportFile, TestCaseRepository testCaseRepository) throws IOException;
}
