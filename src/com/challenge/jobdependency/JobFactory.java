package com.challenge.jobdependency;

import java.util.List;

public class JobFactory {

    public static Job createJob(String id) {
        return new Job(id);
    }

    public static List<Job> getJobs(String input)
            throws SelfDependingJobException, CircularJobDependencyException {
        return Parser.extractJobs(input);
    }

}
