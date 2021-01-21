package com.challenge.jobdependency;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SchedulerTest {

    @Test
    public void testSequenceConsistingOfASingleJob() throws Exception {
        List<Job> jobs = JobFactory.extractJobs("a =>");

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

        List<Job> jobs = JobFactory.extractJobs("a =>,b =>,c =>");
        List<Job> result = JobScheduler.getJobSequence(jobs);

        assertEquals(a, jobs.get(0));
        assertEquals(b, jobs.get(1));
        assertEquals(c, jobs.get(2));
    }

    @Test (expected = SelfDependingJobException.class)
    public void testJobsCannotDependOnThemselves()
            throws SelfDependingJobException, CircularJobDependencyException {
        String failingCase = "a =>,b =>,c => c";
        List<Job> jobs = JobFactory.extractJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }

    @Test (expected = CircularJobDependencyException.class)
    public void testJobsCannotContainCircularDependencies()
            throws CircularJobDependencyException, SelfDependingJobException {
        String failingCase = "a =>,b => c,c => f,d => a,e =>,f => b";
        List<Job> jobs = JobFactory.extractJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }

    @Test
    public void testJobsWithoutContainCircularDependencies()
            throws CircularJobDependencyException, SelfDependingJobException {
        String failingCase = "a =>,b => c,c => f,d => a,e => b,f =>";
        List<Job> jobs = JobFactory.extractJobs(failingCase);

        JobScheduler.getJobSequence(jobs);
    }
}
