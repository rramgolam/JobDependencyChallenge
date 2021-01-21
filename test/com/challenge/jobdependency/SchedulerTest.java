package com.challenge.jobdependency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SchedulerTest {

    @Test
    public void testSequenceConsistingOfASingleJob() {
        List<Job> jobs = JobFactory.extractJobs("a =>");

        List<Job> result = JobScheduler.getJobSequence(jobs);
        assertEquals(1, result.size());
        assertEquals(new Job("a"), result.get(0));
    }

    @Test
    public void testSequenceConsistingOfMultipleJobs() {
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");

        List<Job> jobs = JobFactory.extractJobs("a =>,b =>,c =>");
        List<Job> result = JobScheduler.getJobSequence(jobs);

        assertEquals(a, jobs.get(0));
        assertEquals(b, jobs.get(1));
        assertEquals(c, jobs.get(2));

    }
}
