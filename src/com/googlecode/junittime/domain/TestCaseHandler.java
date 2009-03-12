package com.googlecode.junittime.domain;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class TestCaseHandler extends DefaultHandler {
    private static final String TESTCASE = "testcase";
    private static final String CLASSNAME = "classname";
    private static final String TESTNAME = "name";
    private static final String DURATION = "time";
    private TestSuite testSuite;

    public TestCaseHandler(TestCaseRepository repository) {
        testSuite = new TestSuite();
        repository.add(testSuite);
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (TESTCASE.equals(qName)) {
            String packageNameAndClassName = attributes.getValue(CLASSNAME);
            int separatorIndex = packageNameAndClassName.lastIndexOf(".");

            String packageName = packageNameAndClassName.substring(0, packageNameEndIndex(separatorIndex));
            String className = packageNameAndClassName.substring(classNameStartIndex(separatorIndex), packageNameAndClassName.length());
            String testName = attributes.getValue(TESTNAME);
            Double duration = Double.valueOf(attributes.getValue(DURATION));
            testSuite.add(new TestCase(packageName, className, testName, duration));
        }
    }

    private int classNameStartIndex(int separatorIndex) {
        return separatorIndex == -1 ? 0 : separatorIndex + 1;
    }

    private int packageNameEndIndex(int separatorIndex1) {
        return separatorIndex1 == -1 ? 0 : separatorIndex1;
    }

}
