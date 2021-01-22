package com.challenge.jobdependency;

import java.util.*;

public class JobScheduler {

    public static List<Job> getJobSequence(List<Job> jobs) {
        return sortJobs(jobs);
    }

    private static List<Job> sortJobs(List<Job> jobs) {
        List<Job> result = new ArrayList<>();

        for (Job job : jobs) {

            if (!result.contains(job)) {
                result.add(job);
            }
            if (job.hasDependency()) {
                if (result.contains(job.getDependency())) {
                    int indexOfDep = result.indexOf(job.getDependency());
                    if (indexOfDep > result.indexOf(job))           // check if already ordered
                    {
                        result.add(indexOfDep, job);                // prepend existing
                        result.remove(result.lastIndexOf(job));
                    }
                } else {
                    // add before current job
                    int index = result.indexOf(job);
                    result.add(index, job.getDependency());
                }
            }
        }

        return result;
    }

    public static void printJobsWithDependencies(List<Job> jobs) {
        jobs.forEach(System.out::println);
    }

    public static void printAllJobs(List<Job> jobs) {
        for (Job job : jobs) {
            System.out.println(job.getId());
        }
    }

}
