package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.List;

public class JobScheduler {

    public static List<Job> getJobSequence(List<Job> jobs) {

        List<Job> result = new ArrayList<>();
        result.add(new Job("a"));

        return result;
    }

}
