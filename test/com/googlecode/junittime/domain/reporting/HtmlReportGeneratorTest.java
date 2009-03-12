package com.googlecode.junittime.domain.reporting;

import com.googlecode.junittime.domain.TestCaseRepository;
import com.googlecode.junittime.fixture.TestCaseRepositoryFixture;
import com.googlecode.junittime.utils.FileUtil;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

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
}
