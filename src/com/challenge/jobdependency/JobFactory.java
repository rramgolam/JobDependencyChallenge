package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobFactory {

    public static Job createJob(String id) {
        return new Job(id);
    }

    public static List<Job> extractJobs(String input) {
        List<Job> extractedJobs = new ArrayList<>();
        String[] lines = input.split(",");
        for (String line : lines) {
            //System.out.println(line);
            extractJobs(line, extractedJobs);
        }

        return extractedJobs;
    }

    private static void extractJobs(String input, List<Job> extractedJobs) {
        input = input.replaceAll(" ", "");

        String regEx = "(.*)=>(.*)";
        Pattern textPattern = Pattern.compile(regEx);
        Matcher textMatcher = textPattern.matcher(input);

        String first = null;
        String dependency = null;

        while(textMatcher.find()) {
            //System.out.println("Occurrence: " + textMatcher.group(0) + " , " + textMatcher.group(1) + textMatcher.group(2));
            first = textMatcher.group(1);
            extractedJobs.add(new Job(first));

            if (textMatcher.group(2).strip() != "") {
                dependency = textMatcher.group(2);
                extractedJobs.add(new Job(dependency));
            }
        }

    }
}
