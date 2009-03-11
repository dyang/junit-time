package com.googlecode.junittime.domain.reporting;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.googlecode.junittime.utils.FileUtil;
import com.googlecode.junittime.domain.TestCaseRepository;
import com.googlecode.junittime.domain.TestCase;

public class HtmlReportGeneratorTest {
    @Test
    public void shouldGenerateReportThatHighlightSlowTests() throws IOException {
        File reportFile = FileUtil.aTempFile();
        TestCaseRepository repository = new TestCaseRepository();
        repository.add(new TestCase("class1", "test1", 1.00d));
        repository.add(new TestCase("class2", "test2", 2.00d));
        repository.add(new TestCase("class3", "test3", 3.00d));
        new HtmlReportGenerator().generate(reportFile, repository);

        assertThat(reportFile.exists(), is(true));        
    }
}
