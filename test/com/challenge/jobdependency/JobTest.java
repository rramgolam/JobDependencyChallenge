package com.challenge.jobdependency;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JobTest {

    @Test
    public void createJobWithNoDependency() {
        Job singular = JobFactory.createJob('a');

        assertEquals('a', singular.getId());
    }

    @Test
    public void createJobWithADependency() {
        Job root = JobFactory.createJob('a');
        Job dependency = JobFactory.createJob('b');
        root.setDependency(dependency);

        assertEquals('b', root.getDependency().getId());
    }

    @Test
    public void createAChainedJobDependency() {
        Job a = JobFactory.createJob('a');
        Job b = JobFactory.createJob('b');
        Job c = JobFactory.createJob('c');
        a.setDependency(b);
        b.setDependency(c);

        assertEquals('b', a.getDependency().getId());
        assertEquals('c', b.getDependency().getId());

    }


}
