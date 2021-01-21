package com.challenge.jobdependency;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
        return jobSequence;
    }


    private static boolean hasCycle(Job first) {
        Job fast = first;
        Job slow = first;

        while(fast != null && fast.getDependency() != null) {
            slow = slow.getDependency();                            // 1 hop ahead
            fast = fast.getDependency().getDependency();            // 2 hops ahead

            if(slow == fast)                                        // slow caught up to fast - loop
                return true;
        }
        return false;
    }

    public void printJobs(List<Job> jobs) {
        jobs.forEach(System.out::println);
    }

}
