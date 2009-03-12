package com.googlecode.junittime.domain.reporting;

import com.googlecode.junittime.domain.TestCase;
import com.googlecode.junittime.domain.TestCaseRepository;

import java.io.*;
import java.util.List;
import static java.lang.String.format;

public class HtmlReportGenerator implements ReportGenerator {
    private static final int MAX_NUMBER_OF_TEST_CASES_ON_REPORT = 10;

    public void generate(File reportFile, TestCaseRepository testCaseRepository) throws IOException {
        writeToFile(reportFile, testCaseRepository.byDurationDesc(), testCaseRepository.duration());
    }

    // TODO - use stringTemplate? 
    private void writeToFile(File reportFile, List<TestCase> testCases, double duration) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(reportFile));

            writer.append("<html>");
            writer.newLine();
            writer.append("<body>");
            writer.newLine();

            writer.append("<h1>Tests</h2>");
            writer.newLine();

            writer.append(format("<h2>Total duration</h2>"));
            writer.newLine();
            writer.append(format("%f seconds", duration));
            writer.newLine();

            writer.append("<h2>Slow tests</h2>");
            writer.newLine();
            writer.append("<ol>");
            writer.newLine();
            
            int numberOfTestCasesToDisplay = numberOfTestCasesToDisplay(testCases);
            for (int i = 0; i < numberOfTestCasesToDisplay; i++) {
                TestCase testCase = testCases.get(i);
                writer.append("<li>");
                writer.append(format("%s %s: %.2f seconds", testCase.getClassName(), testCase.getTestName(), testCase.getDuration()));
                writer.append("</li>");
                writer.newLine();
            }

            writer.append("</body>");
            writer.newLine();
            writer.append("</html>");
            writer.newLine();
            
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private int numberOfTestCasesToDisplay(List<TestCase> testCases) {
        return testCases.size() >= MAX_NUMBER_OF_TEST_CASES_ON_REPORT ?
            MAX_NUMBER_OF_TEST_CASES_ON_REPORT : testCases.size();
    }
}
