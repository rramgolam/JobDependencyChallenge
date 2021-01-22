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
    public void convertSingletonJobStringToObject()
            throws SelfDependingJobException, CircularJobDependencyException {
        String a = "a =>";
        List<Job> job = JobFactory.getJobs(a);

        assertEquals("a", job.get(0).getId());
    }

    @Test
    public void convertSingletonWithDependencyStringToObjects()
            throws SelfDependingJobException, CircularJobDependencyException {
        String ab = "a => b";
        List<Job> jobs = JobFactory.getJobs(ab);

        assertEquals("a", jobs.get(0).getId());
        assertEquals("b", jobs.get(1).getId());
    }

    @Test
    public void convertListOfSingletonsWithoutDependenciesStringToObjects()
            throws SelfDependingJobException, CircularJobDependencyException {
        String abc = "a =>,b =>,c =>";
        List<Job> jobs = JobFactory.getJobs(abc);

        assertEquals("a", jobs.get(0).getId());
        assertEquals("b", jobs.get(1).getId());
        assertEquals("c", jobs.get(2).getId());
    }

    @Test
    public void convertListOfSingletonsWithDependenciesToObjects()
            throws SelfDependingJobException, CircularJobDependencyException {
        String abcc = "a =>,b => c,c =>";
        List<Job> jobs = JobFactory.getJobs(abcc);

        assertEquals("a", jobs.get(0).getId());
        assertEquals("b", jobs.get(1).getId());
        assertEquals("c", jobs.get(2).getId());

        assertTrue(jobs.get(1).hasDependency());
        assertSame(jobs.get(1).getDependency(), jobs.get(2));
    }

    @Test
    public void convertLongListOfSingletonsWithValidDependenciesToObjects()
            throws SelfDependingJobException, CircularJobDependencyException {
        String structure = "a =>,b => c,c => f,d => a,e => b,f =>";
        List<Job> jobs = JobFactory.getJobs(structure);

        assertEquals("c", jobs.get(1).getDependency().getId()); // b depends on c
        assertEquals("f", jobs.get(2).getDependency().getId()); // c depends on f
        assertEquals("a", jobs.get(4).getDependency().getId()); // d depends on a
        assertEquals("b", jobs.get(5).getDependency().getId()); // e depends on b
        assertFalse(jobs.get(0).hasDependency());                       // a
        assertFalse(jobs.get(3).hasDependency());                       // f
    }

}
