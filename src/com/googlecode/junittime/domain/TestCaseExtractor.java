package com.googlecode.junittime.domain;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface TestCaseExtractor {
    public void extractTo(TestCaseRepository repository) throws SAXException, ParserConfigurationException, IOException;
}
