package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    protected static List<Job> extractJobs(String input) {
        List<Job> extractedJobs = new ArrayList<>();

        String[] lines = input.split(",");
        for (String line : lines) {
            getJobs(line, extractedJobs);
        }

        return extractedJobs;
    }

    private static void getJobs(String input, List<Job> extractedJobs) {
        input = input.replaceAll(" ", "");

        String regEx = "(.*)=>(.*)";
        Pattern textPattern = Pattern.compile(regEx);
        Matcher textMatcher = textPattern.matcher(input);

        String first = null;
        String dependency = null;

        while(textMatcher.find()) {
            first = textMatcher.group(1);               // first arg
            Job firstJob = new Job(first);

            if (!extractedJobs.contains(firstJob))
            {
                extractedJobs.add(firstJob);
            }

            if (textMatcher.group(2).strip() != "") {
                dependency = textMatcher.group(2);      // second arg
                Job dependant = new Job(dependency);
                if (!extractedJobs.contains(dependant)) {       // avoid dups
                    extractedJobs.add(dependant);
                }

                // set the dependency
                int primary = extractedJobs.indexOf(firstJob);
                int secondary = extractedJobs.indexOf(dependant);
                extractedJobs.get(primary).setDependency(extractedJobs.get(secondary));
            }
        }
    }
}
