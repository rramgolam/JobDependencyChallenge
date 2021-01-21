package com.challenge.jobdependency;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JobTest {

    @Test
    public void createJobWithNoDependency() {
        Job singular = JobFactory.createJob("a");

        assertEquals("a", singular.getId());
    }

    @Test
    public void createJobWithADependency() {
        Job root = JobFactory.createJob("a");
        Job dependency = JobFactory.createJob("b");
        root.setDependency(dependency);

        assertEquals("b", root.getDependency().getId());
    }

    @Test
    public void createAChainedJobDependency() {
        Job a = JobFactory.createJob("a");
        Job b = JobFactory.createJob("b");
        Job c = JobFactory.createJob("c");
        a.setDependency(b);
        b.setDependency(c);

        assertEquals("b", a.getDependency().getId());
        assertEquals("c", b.getDependency().getId());
    }

    @Test
    public void convertSingletonJobStringToObject() {
        String a = "a =>";
        List<Job> aJob = JobFactory.extractJobs(a);

        assertEquals("a", aJob.get(0).getId());
    }

    @Test
    public void convertSingletonWithDependencyStringToObjects() {
        String ab = "a => b";
        List<Job> aJob = JobFactory.extractJobs(ab);

        assertEquals("a", aJob.get(0).getId());
        assertEquals("b", aJob.get(1).getId());
    }

    @Test
    public void convertListOfSingletonsWithoutDependenciesStringToObjects() {
        String abc = "a =>,b =>,c =>";

        List<Job> aJob = JobFactory.extractJobs(abc);

        assertEquals("a", aJob.get(0).getId());
        assertEquals("b", aJob.get(1).getId());
        assertEquals("c", aJob.get(2).getId());
    }

    @Test
    public void convertListOfSingletonsWithDependenciesToObjects() {
        String abcc = "a =>,b => c,c =>";

        List<Job> aJob = JobFactory.extractJobs(abcc);

        assertEquals("a", aJob.get(0).getId());
        assertEquals("b", aJob.get(1).getId());
        assertEquals("c", aJob.get(2).getId());

        assertTrue(aJob.get(1).hasDependency());
        assertSame(aJob.get(1).getDependency(), aJob.get(2));
    }


}
