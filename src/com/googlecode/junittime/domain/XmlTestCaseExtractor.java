package com.googlecode.junittime.domain;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlTestCaseExtractor implements TestCaseExtractor {
    private final File file;

    public XmlTestCaseExtractor(File file) {
        this.file = file;
    }

    public void extractTo(TestCaseRepository repository) throws ExtractionException {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(file, new TestCaseHandler(repository));
        } catch (Exception e) {
            throw new ExtractionException(
                    "Unable to extract test information from file " + file.getAbsolutePath(), e);
        }
    }
}
