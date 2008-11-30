package com.googlecode.junittime.domain.reporting;

import com.googlecode.junittime.domain.TestCase;
import com.googlecode.junittime.domain.TestCaseRepository;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import static java.lang.String.format;
import java.util.List;

// job: generate csv report according to testCaseRepository
public class CSVReportGenerator implements ReportGenerator {
    public void generate(File reportFile, TestCaseRepository testCaseRepository) throws IOException {
        // TODO - should be able to specify which report to generate instead of
        // hard coding byDurationDesc here
        writeBufferToFile(reportFile, generateCSVInBuffer(testCaseRepository.byDurationDesc()));
    }

    private StringBuilder generateCSVInBuffer(List<TestCase> testCases) {
        StringBuilder buffer = new StringBuilder();
        for (TestCase testCase : testCases) {
            buffer.append(generateLine(testCase));
        }
        return buffer;
    }

    private String generateLine(TestCase testCase) {
        return format("%s, %s, %.3f%s",
                testCase.getClassName(),
                testCase.getTestName(),
                testCase.getDuration(),
                System.getProperty("line.separator"));
    }

    private void writeBufferToFile(File reportFile, StringBuilder buffer) throws IOException {
        FileUtils.writeStringToFile(reportFile, buffer.toString());
    }
}
