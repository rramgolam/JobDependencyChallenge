package com.challenge.jobdependency;

import java.util.*;

public class JobScheduler {

    public static List<Job> getJobSequence(List<Job> jobs)
            throws SelfDependingJobException, CircularJobDependencyException {

        List<Job> jobSequence = new ArrayList<>();

        for (Job job : jobs) {
            if (job.getDependency() != null && job.getDependency().equals(job))
                throw new SelfDependingJobException("Self dependence found.");
            if (job.hasDependency() && hasCycle(job))
                throw new CircularJobDependencyException("Cycle found.");
            jobSequence.add(job);
        }

        return sortJobs(jobSequence);
    }


    private static boolean hasCycle(Job head) {
        Job fast = head;
        Job slow = head;

        while(fast != null && fast.getDependency() != null) {
            slow = slow.getDependency();                            // 1 hop ahead
            fast = fast.getDependency().getDependency();            // 2 hops ahead

            if(slow == fast)                                        // slow caught up to fast - loop
                return true;
        }
        return false;
    }



    public static List<Job> sortJobs(List<Job> jobs) {
        List<Job> result = new ArrayList<>();
        // go through each job

        for (Job job : jobs) {

            if (!result.contains(job)) {
                result.add(job);
            }
            if (job.hasDependency()) {
                if (result.contains(job.getDependency())) {
                    int indexOfDep = result.indexOf(job.getDependency());
                    if (indexOfDep > result.indexOf(job))       // check if already ordered
                    {
                        result.add(indexOfDep, job);            //prepend existing
                        result.remove(result.lastIndexOf(job));
                    }

                } else {
                    // add before main
                    int index = result.indexOf(job);
                    result.add(index, job.getDependency());
                }
            }
        }

        return result;
    }

//    public static void printJobs(List<Job> jobs) {
//        jobs.forEach(System.out::println);
//    }

    public static void printAllJobs(List<Job> jobs) {
        for (Job job : jobs) {
            System.out.println(job.getId());
        }
    }

}
