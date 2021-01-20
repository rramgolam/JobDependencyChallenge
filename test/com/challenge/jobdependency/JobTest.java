package com.challenge.jobdependency;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void createJobWithNoDependency() {
        Job singular = JobFactory.createJob("a");

        assertEquals('a', singular.getId());
    }

    @Test
    public void createJobWithADependency() {
        Job root = JobFactory.createJob("a");
        Job dependency = JobFactory.createJob("b");
        root.setDependency(dependency);

        assertEquals('b', root.getDependency().getId());
    }

    @Test
    public void createAChainedJobDependency() {
        Job a = JobFactory.createJob("a");
        Job b = JobFactory.createJob("b");
        Job c = JobFactory.createJob("c");
        a.setDependency(b);
        b.setDependency(c);

        assertEquals('b', a.getDependency().getId());
        assertEquals('c', b.getDependency().getId());
    }

    @Test
    public void convertSingletonJobStringToObject() {
        String a = "a =>";
        List<Job> aJob = JobFactory.extractJobs(a);

        assertEquals("a", aJob.get(0).getId());
    }


}
