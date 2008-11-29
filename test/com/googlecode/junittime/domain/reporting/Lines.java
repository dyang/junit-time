package com.googlecode.junittime.domain.reporting;

import java.util.List;

public class Lines {
    private List lines;

    public Lines(List lines) {
        this.lines = lines;
    }

    public String at(int index) {
        return (String) lines.get(index);
    }

    public int size() {
        return lines.size();
    }
}
