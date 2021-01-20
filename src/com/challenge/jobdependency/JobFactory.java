package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobFactory {

    public static Job createJob(char id) {
        return new Job(id);
    }

    public static List<Job> extractJobs(String input) {
        return Arrays.asList(new Job('a'));
    }
}
