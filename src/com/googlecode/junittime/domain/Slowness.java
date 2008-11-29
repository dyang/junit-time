package com.googlecode.junittime.domain;


public enum Slowness {
    SLOWER(-1), SAME(0), FASTER(1);

    private int slownessFactor;

    Slowness(int slownessFactor) {
        this.slownessFactor = slownessFactor;
    }

    public int toInt() {
        return slownessFactor;
    }
}
