package com.challenge.jobdependency;

public class Job {

    private final String id;
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
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Job) {
            return this.getId().equals(((Job) obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        return hasDependency() ? getId() + " => " + getDependency() : getId();
    }
}
