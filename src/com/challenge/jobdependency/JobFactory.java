package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobFactory {

    public static Job createJob(String id) {
        return new Job(id);
    }

    public static List<Job> getJobs(String input) {
        return Parser.extractJobs(input);
    }


}
