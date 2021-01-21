package com.challenge.jobdependency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SchedulerTest {

    @Test
    public void testSequenceConsistingOfASingleJob() {
        List<Job> jobs = JobFactory.extractJobs("a =>");

        List<Job> result = JobScheduler.getJobSequence(jobs);
        assertEquals(1, result.size());
        assertEquals(new Job("a"), result.get(0));
    }
}
