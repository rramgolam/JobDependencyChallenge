package com.challenge.jobdependency;

public class Job {

    private char id;
    private Job dependency = null;

    public Job(char id) {
        this.id = id;
    }

    public char getId() {
        return this.id;
    }

    public void setDependency(Job dependency) {
        this.dependency = dependency;
    }

    public Job getDependency() {
        return dependency;
    }
}
