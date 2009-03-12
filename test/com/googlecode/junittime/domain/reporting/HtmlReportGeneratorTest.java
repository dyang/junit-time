package com.googlecode.junittime.domain.reporting;

import com.googlecode.junittime.domain.TestCaseRepository;
import com.googlecode.junittime.fixture.TestCaseRepositoryFixture;
import com.googlecode.junittime.utils.FileUtil;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.junit.matchers.StringContains.containsString;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class HtmlReportGeneratorTest {
    @Test
    public void shouldGenerateReportThatHighlightSlowTests() throws IOException {
        File reportFile = FileUtil.aTempFile();
        TestCaseRepository repository = TestCaseRepositoryFixture.createDefaultRepository();
        new HtmlReportGenerator().generate(reportFile, repository);

        assertThat(reportFile.exists(), is(true));        
    }

    @Test
    public void shouldGenerateReportThatHighlightTotalTestDuration() throws IOException {
        File reportFile = FileUtil.aTempFile();
        TestCaseRepository repository = TestCaseRepositoryFixture.createDefaultRepository();
        new HtmlReportGenerator().generate(reportFile, repository);

        String fileContent = FileUtils.readFileToString(reportFile);
        assertThat(fileContent, containsString(String.valueOf(repository.duration())));
    }
}
