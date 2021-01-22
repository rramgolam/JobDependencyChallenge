package com.challenge.jobdependency;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchedulerTest {

    @Test
    public void testSequenceConsistingOfASingleJob() throws Exception {
        List<Job> jobs = JobFactory.getJobs("a =>");
        List<Job> result = JobScheduler.getJobSequence(jobs);

        assertEquals(1, result.size());
        assertEquals(new Job("a"), result.get(0));
    }

    @Test
    public void testSequenceConsistingOfMultipleJobs()
            throws SelfDependingJobException, CircularJobDependencyException {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");

        List<Job> jobs = JobFactory.getJobs("a =>,b =>,c =>");

        assertEquals(a, jobs.get(0));
        assertEquals(b, jobs.get(1));
        assertEquals(c, jobs.get(2));
    }

    @Test (expected = SelfDependingJobException.class)
    public void testJobsCannotDependOnThemselves()
            throws SelfDependingJobException, CircularJobDependencyException {
        String failingCase = "a =>,b =>,c => c";
        List<Job> jobs = JobFactory.getJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }

    @Test (expected = CircularJobDependencyException.class)
    public void testJobsCannotContainCircularDependencies()
            throws CircularJobDependencyException, SelfDependingJobException {
        String failingCase = "a =>,b => c,c => f,d => a,e =>,f => b";
        List<Job> jobs = JobFactory.getJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }

    @Test
    public void testJobsWithoutContainCircularDependencies()
            throws CircularJobDependencyException, SelfDependingJobException {
        String failingCase = "a =>,b => c,c => f,d => a,e => b,f =>";
        List<Job> jobs = JobFactory.getJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }

    @Test
    public void testSortingJobsByDependency()
            throws SelfDependingJobException, CircularJobDependencyException {
        List<Job> expected = new ArrayList<>();
        expected.add(new Job("a"));
        expected.add(new Job("c"));
        expected.add(new Job("b"));

        String validCase = "a =>,b => c,c =>";
        List<Job> jobs = JobFactory.getJobs(validCase);
        List<Job> result = JobScheduler.getJobSequence(jobs);

        assertEquals(expected, result);
    }

    @Test
    public void testSortingMoreJobsByDependency()
            throws SelfDependingJobException, CircularJobDependencyException {

        String validCase = "a =>,b => c,c => f,d => a,e => b,f =>";
        List<Job> jobs = JobFactory.getJobs(validCase);
        List<Job> result = JobScheduler.getJobSequence(jobs);

        assertTrue(result.indexOf(new Job("f")) < result.indexOf(new Job("c")));
        assertTrue(result.indexOf(new Job("c")) < result.indexOf(new Job("b")));
        assertTrue(result.indexOf(new Job("b")) < result.indexOf(new Job("e")));
        assertTrue(result.indexOf(new Job("a")) < result.indexOf(new Job("d")));

        JobScheduler.printAllJobs(result);
    }

}
