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
import com.googlecode.junittime.fixture.TestCaseRepositoryFixture;

public class CSVReportGeneratorTest {
    @Test
    public void shouldGenerateReportInCSVFormat() throws IOException {
        File reportFile = FileUtil.aTempFile();
        TestCaseRepository repository = TestCaseRepositoryFixture.createRepositoryWith(
                new TestCase("class1", "test1", 1.00d),
                new TestCase("class2", "test2", 2.00d),
                new TestCase("class3", "test3", 3.00d));
        new CSVReportGenerator().generate(reportFile, repository);

        assertThat(reportFile.exists(), is(true));
        Lines lines = new Lines(FileUtils.readLines(reportFile));
        assertThat(lines.size(), is(3));
        assertThat(lines.at(0), is("class3, test3, 3.000"));
        assertThat(lines.at(1), is("class2, test2, 2.000"));
        assertThat(lines.at(2), is("class1, test1, 1.000"));
    }
}
