package com.challenge.jobdependency;

import java.util.ArrayList;
import java.util.List;

public class JobScheduler {

    public static List<Job> getJobSequence(List<Job> jobs) throws SelfDependingJobException {

        List<Job> jobSequence = new ArrayList<>();

        for (Job job : jobs) {
            if (job.getDependency() != null && job.getDependency().equals(job))
                throw new SelfDependingJobException("dfd");
            jobSequence.add(job);
        }

        return jobSequence;
    }

    protected static void cycleCheck(List<Job> jobs) throws CircularJobDependencyException {
        throw new CircularJobDependencyException("Stub");
    }

}
