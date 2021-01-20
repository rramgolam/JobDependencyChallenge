package com.challenge.jobdependency;

public class JobFactory {

    public static Job createJob(char id) {
        return new Job(id);
    }
}
