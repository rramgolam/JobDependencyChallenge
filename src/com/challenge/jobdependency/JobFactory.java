package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.HashSet;
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
            extractJobs(line, extractedJobs);
        }

        //extractedJobs.forEach(System.out::println);

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
            Job firstJob = new Job(first);
            //check if already there
            if (!extractedJobs.contains(firstJob))
            {
                extractedJobs.add(firstJob);
            }

            if (textMatcher.group(2).strip() != "") {
                dependency = textMatcher.group(2);

                if (!extractedJobs.contains(new Job(dependency))) {
                    extractedJobs.add(new Job(dependency));
                }

                // set the dependency
                int primary = extractedJobs.indexOf(firstJob);
                int secondary = extractedJobs.indexOf(new Job(dependency));
                extractedJobs.get(primary).setDependency(extractedJobs.get(secondary));

            }
        }

    }
}
