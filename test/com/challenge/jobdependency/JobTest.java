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


}
