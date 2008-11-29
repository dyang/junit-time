package com.googlecode.junittime.domain.reporting;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.apache.commons.io.FileUtils;
import static org.hamcrest.core.Is.is;

import java.io.File;
import java.io.IOException;

import com.googlecode.junittime.utils.FileUtil;
import com.googlecode.junittime.domain.TestCaseRepository;
import com.googlecode.junittime.domain.TestCase;

public class CSVReportGeneratorTest {
    @Test
    public void shouldGenerateReportInCSVFormat() throws IOException {
        File reportFile = FileUtil.aTempFile();
        TestCaseRepository repository = new TestCaseRepository();
        repository.add(new TestCase("class1", "test1", 1.00d));
        repository.add(new TestCase("class2", "test2", 2.00d));
        repository.add(new TestCase("class3", "test3", 3.00d));
        new CSVReportGenerator().generate(reportFile, repository);

        assertThat(reportFile.exists(), is(true));
        Lines lines = new Lines(FileUtils.readLines(reportFile));
        assertThat(lines.size(), is(3));
        assertThat(lines.at(0), is("class3, test3, 3.00"));
        assertThat(lines.at(1), is("class2, test2, 2.00"));
        assertThat(lines.at(2), is("class1, test1, 1.00"));        
    }
}
