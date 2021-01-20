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

    public boolean hasDependency() {
        return dependency != null;
    }

    @Override
    public String toString() {
        return hasDependency() ? getId() + " => " + getDependency() : getId();
    }
}
