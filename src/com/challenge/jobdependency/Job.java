package com.challenge.jobdependency;

public class Job {

    private String id;
    private Job dependency = null;

    public Job(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setDependency(Job dependency) {
        this.dependency = dependency;
    }

    public Job getDependency() {
        return dependency;
    }
}
