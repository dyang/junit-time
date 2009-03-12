package com.googlecode.junittime.domain;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TestCaseHandler extends DefaultHandler {
    private static final String TESTCASE = "testcase";
    private static final String CLASSNAME = "classname";
    private static final String TESTNAME = "name";
    private static final String DURATION = "time";
    private TestCaseRepository repository;

    public TestCaseHandler(TestCaseRepository repository) {
        this.repository = repository;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (TESTCASE.equals(qName)) {
            String packageNameAndClassName = attributes.getValue(CLASSNAME);
            int separatorIndex = packageNameAndClassName.lastIndexOf(".") + 1;
            String packageName = packageNameAndClassName.substring(0, separatorIndex);
            String className = packageNameAndClassName.substring(separatorIndex, packageNameAndClassName.length());
            String testName = attributes.getValue(TESTNAME);
            Double duration = Double.valueOf(attributes.getValue(DURATION));
            repository.add(new TestCase(packageName, className, testName, duration));
        }
    }

}
